package com.projectmanagementapp.domain.service;

import com.projectmanagementapp.domain.dao.IssueDao;
import com.projectmanagementapp.domain.dao.ProjectDao;
import com.projectmanagementapp.domain.model.Issue;
import com.projectmanagementapp.domain.model.IssuePriority;
import com.projectmanagementapp.domain.model.IssueStatus;
import com.projectmanagementapp.domain.model.Project;
import com.projectmanagementapp.dto.IssueRequest;
import com.projectmanagementapp.exception.ResourceNotFoundException;
import com.projectmanagementapp.message.CommonMessage;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IssueServiceImplTest {

    private static final LocalDateTime NOW = LocalDateTime.of(2026, 7, 5, 10, 0);

    @Mock
    private IssueDao issueDao;

    @Mock
    private ProjectDao projectDao;

    @InjectMocks
    private IssueServiceImpl issueService;

    @Test
    void findAllUsesProjectAndStatusFilters() {
        // 【★この1行を追加！】 projectDao.findById(1L) が呼ばれたときにダミーのプロジェクトを返すように設定
        var mockProject = new com.projectmanagementapp.domain.model.Project(1L, "PROJ-A", "アプリ開発", "説明", NOW, NOW);
        when(projectDao.findById(1L)).thenReturn(mockProject);

        // 以下は既存のコードのままです
        when(issueDao.findByProjectIdAndStatus(1L, IssueStatus.TODO)).thenReturn(List.of(issue()));

        var responses = issueService.findAll(1L, IssueStatus.TODO);

        assertThat(responses).hasSize(1);
        assertThat(responses.getFirst().getStatusLabel()).isEqualTo("未着手");
        assertThat(responses.getFirst().getPriorityLabel()).isEqualTo("中");
        verify(issueDao).findByProjectIdAndStatus(1L, IssueStatus.TODO);
    }


    @Test
    void findByIdThrowsNotFoundWhenIssueDoesNotExist() {
        when(issueDao.findById(99L)).thenReturn(null);

        assertThatThrownBy(() -> issueService.findById(99L))
            .isInstanceOf(ResourceNotFoundException.class)
            .hasMessage(CommonMessage.ISSUE_NOT_FOUND);
    }

    @Test
    void createThrowsNotFoundWhenProjectDoesNotExist() {
        IssueRequest request = request();
        when(projectDao.findById(1L)).thenReturn(null);

        assertThatThrownBy(() -> issueService.create(request))
            .isInstanceOf(ResourceNotFoundException.class)
            .hasMessage(CommonMessage.PROJECT_NOT_FOUND);
    }

    @Test
    void createReturnsIssueWhenProjectExists() {
        IssueRequest request = request();
        when(projectDao.findById(1L)).thenReturn(project());
        when(issueDao.insert(request)).thenReturn(issue());

        var response = issueService.create(request);

        assertThat(response.getId()).isEqualTo(10L);
        assertThat(response.getProjectKey()).isEqualTo("APP");
        assertThat(response.getStatus()).isEqualTo("TODO");
    }

    @Test
    void updateChecksIssueAndProjectExistence() {
        IssueRequest request = request();
        when(issueDao.findById(10L)).thenReturn(issue());
        when(projectDao.findById(1L)).thenReturn(project());
        when(issueDao.update(10L, request)).thenReturn(issue());

        issueService.update(10L, request);

        verify(issueDao).findById(10L);
        verify(projectDao).findById(1L);
        verify(issueDao).update(10L, request);
    }

    @Test
    @org.junit.jupiter.api.DisplayName("findAll：存在しないプロジェクトIDが指定された場合、ResourceNotFoundExceptionをスローすること")
    void findAllShouldThrowExceptionWhenProjectDoesNotExist() {
        // 1. 準備：projectDao.findById(999L) が null（存在しない）を返すように設定
        Long nonExistentProjectId = 999L;
        when(projectDao.findById(nonExistentProjectId)).thenReturn(null);

        // 2. 実行 & 検証：Serviceを実行したときに適切な例外がスローされること
        com.projectmanagementapp.exception.ResourceNotFoundException exception = 
            org.junit.jupiter.api.Assertions.assertThrows(
                com.projectmanagementapp.exception.ResourceNotFoundException.class, 
                () -> issueService.findAll(nonExistentProjectId, null)
            );

        // エラーメッセージが「プロジェクトが見つかりません」の形式であることを確認
        org.assertj.core.api.Assertions.assertThat(exception.getMessage())
            .isEqualTo(com.projectmanagementapp.message.CommonMessage.PROJECT_NOT_FOUND);

        // 受入条件：プロジェクトが存在しない場合、課題検索処理（issueDao）が実行されないこと
        verifyNoInteractions(issueDao);
    }

    @Test
    @org.junit.jupiter.api.DisplayName("findAll：存在するが課題が0件のプロジェクトIDが指定された場合、空の配列が返されること")
    void findAllShouldReturnEmptyListWhenProjectExistsButHasNoIssues() {
        // 1. 準備：プロジェクトは存在するが、課題検索結果は空リストを返すように設定
        Long existentProjectId = 1L;
            var mockProject = new com.projectmanagementapp.domain.model.Project(
                existentProjectId, "PROJ-A", "アプリ開発", "説明", null, null
            );

        when(projectDao.findById(existentProjectId)).thenReturn(mockProject);
        when(issueDao.findByProjectId(existentProjectId)).thenReturn(java.util.List.of());

        // 2. 実行
        List<com.projectmanagementapp.dto.IssueResponse> response = issueService.findAll(existentProjectId, null);

        // 3. 検証：HTTP 200相当（空の配列）が返ってくること
        org.assertj.core.api.Assertions.assertThat(response).isEmpty();
        verify(issueDao).findByProjectId(existentProjectId);
    }


    private IssueRequest request() {
        return new IssueRequest(
            1L,
            "課題登録",
            "説明",
            IssueStatus.TODO,
            IssuePriority.MEDIUM,
            "佐藤",
            LocalDate.of(2026, 7, 31)
        );
    }

    private Project project() {
        return new Project(1L, "APP", "アプリ開発", "ProjectFlow開発用", NOW, NOW);
    }

    private Issue issue() {
        return new Issue(
            10L,
            1L,
            "APP",
            "アプリ開発",
            "課題登録",
            "説明",
            IssueStatus.TODO,
            IssuePriority.MEDIUM,
            "佐藤",
            LocalDate.of(2026, 7, 31),
            NOW,
            NOW
        );
    }
}
