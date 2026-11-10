package com.projectmanagementapp.domain.dao.mapper;

import com.projectmanagementapp.domain.model.Issue;
import com.projectmanagementapp.domain.model.IssuePriority;
import com.projectmanagementapp.domain.model.IssueStatus;
import com.projectmanagementapp.dto.IssueRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
@Sql(value = "/sql/IssueMapperTest/issue-mapper-test-data.sql", config = @SqlConfig(encoding = "UTF-8"))
class IssueMapperTest {

    private static final long PROJECT_A = 90001L;
    private static final long PROJECT_B = 90002L;
    private static final long EMPTY_PROJECT = 90003L;
    private static final long MISSING_ID = -1L;

    @Autowired
    private IssueMapper issueMapper;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    @DisplayName("findAll: 更新日時の降順、同日時ではIDの降順で全課題を取得する")
    void findAllReturnsAllIssuesInUpdatedAtAndIdDescendingOrder() {
        assertThat(issueMapper.findAll()).extracting(Issue::getId)
            .containsExactly(900006L, 900009L, 900005L, 900003L, 900002L,
                900010L, 900008L, 900004L, 900007L, 900001L);
    }

    @Test
    @DisplayName("findById: JOINしたプロジェクト情報を含む全項目をマッピングする")
    void findByIdMapsEveryColumnIncludingProjectInformation() {
        Issue expected = new Issue(
            900001L, PROJECT_A, "TEST_A", "Mapperテスト用プロジェクトA",
            "未着手の課題", "課題の詳細説明", IssueStatus.TODO, IssuePriority.MEDIUM,
            "山田太郎", LocalDate.of(2025, 3, 31),
            LocalDateTime.of(2025, 1, 1, 9, 0), LocalDateTime.of(2025, 2, 1, 10, 0)
        );

        assertThat(issueMapper.findById(900001L)).isEqualTo(expected);
    }

    @Test
    @DisplayName("findById: 存在しないIDではnullを返す")
    void findByIdReturnsNullWhenMissing() {
        assertThat(issueMapper.findById(MISSING_ID)).isNull();
    }

    @Test
    @DisplayName("findByProjectId: 指定プロジェクトだけを所定の順序で取得する")
    void findByProjectIdFiltersAndOrdersIssues() {
        assertThat(issueMapper.findByProjectId(PROJECT_A)).extracting(Issue::getId)
            .containsExactly(900009L, 900003L, 900002L, 900004L, 900001L);
        assertThat(issueMapper.findByProjectId(PROJECT_B)).extracting(Issue::getId)
            .containsExactly(900006L, 900005L, 900010L, 900008L, 900007L);
    }

    @Test
    @DisplayName("findByProjectId: 課題のない／存在しないプロジェクトでは空リストを返す")
    void findByProjectIdReturnsEmptyWhenNoMatch() {
        assertThat(issueMapper.findByProjectId(EMPTY_PROJECT)).isEmpty();
        assertThat(issueMapper.findByProjectId(MISSING_ID)).isEmpty();
    }

    @Test
    @DisplayName("findByStatus: 指定ステータスだけを所定の順序で取得する")
    void findByStatusFiltersAndOrdersIssues() {
        var result = issueMapper.findByStatus(IssueStatus.TODO);

        assertThat(result).extracting(Issue::getId).containsExactly(900009L, 900005L, 900008L, 900001L);
        assertThat(result).extracting(Issue::getStatus).containsOnly(IssueStatus.TODO);
    }

    @Test
    @DisplayName("findByProjectIdAndStatus: 両条件に一致する課題だけを所定の順序で取得する")
    void findByProjectIdAndStatusRequiresBothConditions() {
        assertThat(issueMapper.findByProjectIdAndStatus(PROJECT_A, IssueStatus.TODO))
            .extracting(Issue::getId).containsExactly(900009L, 900001L);
    }

    @Test
    @DisplayName("findByProjectIdAndStatus: プロジェクトに対象ステータスの課題がなければ空リストを返す")
    void findByProjectIdAndStatusReturnsEmptyWhenNoMatch() {
        assertThat(issueMapper.findByProjectIdAndStatus(PROJECT_B, IssueStatus.DONE)).isEmpty();
    }

    @Test
    @DisplayName("findRecent: 更新日時、IDの降順で指定件数に制限する")
    void findRecentLimitsIssuesInUpdatedAtAndIdDescendingOrder() {
        assertThat(issueMapper.findRecent(3)).extracting(Issue::getId)
            .containsExactly(900006L, 900009L, 900005L);
    }

    @Test
    @DisplayName("findRecent: 取得件数0では空リストを返す")
    void findRecentReturnsEmptyForZeroLimit() {
        assertThat(issueMapper.findRecent(0)).isEmpty();
    }

    @Test
    @DisplayName("insert: 全項目を保存し、自動採番・日時・JOIN情報を返す")
    void insertPersistsAllColumnsAndReturnsGeneratedValues() {
        IssueRequest request = request();
        LocalDateTime transactionTime = transactionTime();

        Issue inserted = issueMapper.insert(request);

        assertThat(inserted).isNotNull();
        assertThat(inserted.getId()).isPositive().isNotIn(900001L, 900002L, 900003L, 900004L,
            900005L, 900006L, 900007L, 900008L, 900009L, 900010L);
        assertMatchesRequest(inserted, request, "TEST_A", "Mapperテスト用プロジェクトA");
        assertThat(inserted.getCreatedAt()).isEqualTo(transactionTime);
        assertThat(inserted.getUpdatedAt()).isEqualTo(transactionTime);
        assertPersistedRow(inserted);
        assertThat(issueMapper.countAll()).isEqualTo(11);
    }

    @Test
    @DisplayName("insert: 任意項目がすべてNULLでも登録できる")
    void insertPersistsNullOptionalColumns() {
        IssueRequest request = new IssueRequest(PROJECT_B, "任意項目なし", null,
            IssueStatus.TODO, IssuePriority.LOW, null, null);

        Issue inserted = issueMapper.insert(request);

        assertMatchesRequest(inserted, request, "TEST_B", "Mapperテスト用プロジェクトB");
        assertPersistedRow(inserted);
    }

    @Test
    @DisplayName("update: 全項目と所属プロジェクトを更新し、作成日時を保持して更新日時を再設定する")
    void updatePersistsAllColumnsAndReturnsNewProjectInformation() {
        Issue original = issueMapper.findById(900001L);
        Issue untouched = issueMapper.findById(900002L);
        IssueRequest request = request();
        request.setProjectId(PROJECT_B);
        LocalDateTime transactionTime = transactionTime();

        Issue updated = issueMapper.update(900001L, request);

        assertThat(updated).isNotNull();
        assertThat(updated.getId()).isEqualTo(900001L);
        assertMatchesRequest(updated, request, "TEST_B", "Mapperテスト用プロジェクトB");
        assertThat(updated.getCreatedAt()).isEqualTo(original.getCreatedAt());
        assertThat(updated.getUpdatedAt()).isEqualTo(transactionTime).isAfter(original.getUpdatedAt());
        assertPersistedRow(updated);
        assertPersistedRow(untouched);
        assertThat(issueMapper.countAll()).isEqualTo(10);
    }

    @Test
    @DisplayName("update: 任意項目の既存値をNULLに更新できる")
    void updateClearsOptionalColumns() {
        IssueRequest request = new IssueRequest(PROJECT_A, "任意項目を削除", null,
            IssueStatus.DONE, IssuePriority.LOW, null, null);

        Issue updated = issueMapper.update(900001L, request);

        assertMatchesRequest(updated, request, "TEST_A", "Mapperテスト用プロジェクトA");
        assertPersistedRow(updated);
    }

    @Test
    @DisplayName("update: 存在しないIDではnullを返し、他の行を変更しない")
    void updateReturnsNullAndLeavesRowsUnchangedWhenMissing() {
        var before = jdbcTemplate.queryForList("SELECT * FROM management_app.issues ORDER BY id");

        assertThat(issueMapper.update(MISSING_ID, request())).isNull();

        assertThat(jdbcTemplate.queryForList("SELECT * FROM management_app.issues ORDER BY id")).isEqualTo(before);
    }

    @Test
    @DisplayName("delete: 対象行だけを削除し、削除件数1と削除後の検索結果を返す")
    void deleteRemovesOnlyTargetIssue() {
        assertThat(issueMapper.findById(900001L)).isNotNull();
        assertThat(issueMapper.countAll()).isEqualTo(10);
        assertThat(issueMapper.countByStatus(IssueStatus.TODO)).isEqualTo(4);

        assertThat(issueMapper.delete(900001L)).isEqualTo(1);

        assertThat(issueMapper.findById(900001L)).isNull();
        assertThat(issueMapper.countAll()).isEqualTo(9);
        assertThat(issueMapper.countByStatus(IssueStatus.TODO)).isEqualTo(3);
        assertThat(jdbcTemplate.queryForList("SELECT id FROM management_app.issues ORDER BY id", Long.class))
            .containsExactly(900002L, 900003L, 900004L, 900005L, 900006L, 900007L, 900008L, 900009L, 900010L);
        assertThat(jdbcTemplate.queryForObject("SELECT COUNT(*) FROM management_app.projects", Long.class)).isEqualTo(3);
    }

    @Test
    @DisplayName("delete: 存在しないIDでは0件を返し、他の行を削除しない")
    void deleteReturnsZeroWhenMissing() {
        var before = jdbcTemplate.queryForList("SELECT * FROM management_app.issues ORDER BY id");

        assertThat(issueMapper.delete(MISSING_ID)).isZero();

        assertThat(jdbcTemplate.queryForList("SELECT * FROM management_app.issues ORDER BY id")).isEqualTo(before);
    }

    @Test
    @DisplayName("countAll: プロジェクト・ステータスを問わず全課題を数える")
    void countAllCountsEveryIssue() {
        assertThat(issueMapper.countAll()).isEqualTo(10);
    }

    @Test
    @DisplayName("countByStatus: 各ステータスの課題だけを数える")
    void countByStatusCountsOnlyMatchingIssues() {
        assertThat(issueMapper.countByStatus(IssueStatus.TODO)).isEqualTo(4);
        assertThat(issueMapper.countByStatus(IssueStatus.IN_PROGRESS)).isEqualTo(3);
        assertThat(issueMapper.countByStatus(IssueStatus.REVIEW)).isEqualTo(2);
        assertThat(issueMapper.countByStatus(IssueStatus.DONE)).isEqualTo(1);
    }

    private IssueRequest request() {
        return new IssueRequest(PROJECT_A, "登録・更新用の課題", "登録・更新用の説明",
            IssueStatus.REVIEW, IssuePriority.HIGH, "テスト担当者", LocalDate.of(2027, 1, 31));
    }

    // CURRENT_TIMESTAMPはPostgreSQLのトランザクション開始時刻。Javaの現在時刻との比較は行わない。
    private LocalDateTime transactionTime() {
        return jdbcTemplate.queryForObject("SELECT CURRENT_TIMESTAMP::timestamp", LocalDateTime.class);
    }

    private void assertMatchesRequest(Issue issue, IssueRequest request, String projectKey, String projectName) {
        assertThat(issue).isNotNull();
        assertThat(issue.getProjectId()).isEqualTo(request.getProjectId());
        assertThat(issue.getProjectKey()).isEqualTo(projectKey);
        assertThat(issue.getProjectName()).isEqualTo(projectName);
        assertThat(issue.getTitle()).isEqualTo(request.getTitle());
        assertThat(issue.getDescription()).isEqualTo(request.getDescription());
        assertThat(issue.getStatus()).isEqualTo(request.getStatus());
        assertThat(issue.getPriority()).isEqualTo(request.getPriority());
        assertThat(issue.getAssigneeName()).isEqualTo(request.getAssigneeName());
        assertThat(issue.getDueDate()).isEqualTo(request.getDueDate());
    }

    // Mapperの戻り値だけでは保存を保証できないため、別経路のJDBCで実際の行を検証する。
    private void assertPersistedRow(Issue issue) {
        var row = jdbcTemplate.queryForMap("SELECT * FROM management_app.issues WHERE id = ?", issue.getId());
        assertThat(row)
            .containsEntry("id", issue.getId())
            .containsEntry("project_id", issue.getProjectId())
            .containsEntry("title", issue.getTitle())
            .containsEntry("description", issue.getDescription())
            .containsEntry("status", issue.getStatus().name())
            .containsEntry("priority", issue.getPriority().name())
            .containsEntry("assignee_name", issue.getAssigneeName())
            .containsEntry("due_date", issue.getDueDate() == null ? null : java.sql.Date.valueOf(issue.getDueDate()))
            .containsEntry("created_at", java.sql.Timestamp.valueOf(issue.getCreatedAt()))
            .containsEntry("updated_at", java.sql.Timestamp.valueOf(issue.getUpdatedAt()));
    }
}
