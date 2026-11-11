package com.projectmanagementapp.domain.service;

import com.projectmanagementapp.domain.dao.ProjectDao;
import com.projectmanagementapp.domain.model.Project;
import com.projectmanagementapp.dto.ProjectRequest;
import com.projectmanagementapp.exception.BusinessException;
import com.projectmanagementapp.exception.ResourceNotFoundException;
import com.projectmanagementapp.message.CommonMessage;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProjectServiceImplTest {

    private static final LocalDateTime NOW = LocalDateTime.of(2026, 7, 5, 10, 0);

    @Mock
    private ProjectDao projectDao;

    @InjectMocks
    private ProjectServiceImpl projectService;

    @Test
    void findByIdReturnsProject() {
        when(projectDao.findById(1L)).thenReturn(project());

        var response = projectService.findById(1L);

        assertThat(response.getProjectKey()).isEqualTo("APP");
        assertThat(response.getName()).isEqualTo("アプリ開発");
    }

    @Test
    void findByIdThrowsNotFoundWhenProjectDoesNotExist() {
        when(projectDao.findById(99L)).thenReturn(null);

        assertThatThrownBy(() -> projectService.findById(99L))
            .isInstanceOf(ResourceNotFoundException.class)
            .hasMessage(CommonMessage.PROJECT_NOT_FOUND);
    }

    @Test
    void createThrowsBusinessExceptionWhenProjectKeyIsDuplicated() {
        ProjectRequest request = request();
        when(projectDao.existsByProjectKey("APP")).thenReturn(true);

        assertThatThrownBy(() -> projectService.create(request))
            .isInstanceOf(BusinessException.class)
            .hasMessage(CommonMessage.PROJECT_KEY_ALREADY_USED);

        verify(projectDao).existsByProjectKey("APP");
        verifyNoMoreInteractions(projectDao);
    }

    @Test
    void updateThrowsBusinessExceptionWhenProjectKeyIsUsedByOtherProject() {
        ProjectRequest request = request();
        when(projectDao.findById(1L)).thenReturn(project());
        when(projectDao.existsByProjectKeyAndIdNot("APP", 1L)).thenReturn(true);

        assertThatThrownBy(() -> projectService.update(1L, request))
            .isInstanceOf(BusinessException.class)
            .hasMessage(CommonMessage.PROJECT_KEY_ALREADY_USED);
    }

    @Test
    void deleteThrowsBusinessExceptionWhenProjectHasIssues() {
        when(projectDao.findById(1L)).thenReturn(project());
        when(projectDao.countIssuesByProjectId(1L)).thenReturn(1L);

        assertThatThrownBy(() -> projectService.delete(1L))
            .isInstanceOf(BusinessException.class)
            .hasMessage(CommonMessage.PROJECT_HAS_ISSUES);
    }

    @Test
    @org.junit.jupiter.api.DisplayName("findAll：キーワードが未指定または空の場合は全件取得できること")
    void findAllReturnsAllProjectsWhenKeywordIsEmpty() {
        // 1. テスト用のデータ準備
        var p1 = project();
        var p2 = new Project(2L, "MGR", "管理ツール", "説明文", NOW, NOW);
        
        // 2. 引数に何（nullや空文字）が渡されても、全件データを返すようにモックを設定
        // ※ もし引数を厳密に分ける場合は when(projectDao.findAll(null)) と when(projectDao.findAll(" ")) を定義してもOKです
        when(projectDao.findAll(org.mockito.ArgumentMatchers.any())).thenReturn(java.util.List.of(p1, p2));

        // 3. キーワードが null の場合の検証
        var responseNull = projectService.findAll(null);
        assertThat(responseNull).hasSize(2);

        // 4. キーワードが空文字（スペース）の場合の検証
        var responseBlank = projectService.findAll(" ");
        assertThat(responseBlank).hasSize(2);
        
        // DAOのfindAllが計2回呼ばれたことを検証
        verify(projectDao, times(2)).findAll(org.mockito.ArgumentMatchers.any());
    }

    @Test
    @org.junit.jupiter.api.DisplayName("findAll：キーワード指定時、大文字小文字に関わらずそのままDAOに条件が渡されること")
    void findAllPassesKeywordToDao() {
        // 1. データベースから「app」に部分一致したデータだけが返ってくる想定のモックデータを用意
        var p2 = new Project(2L, "PROJ-B", "システム設計", "APPの解説", NOW, NOW); 
        var p3 = new Project(3L, "PROJ-C", "WEB APP", null, NOW, NOW);         
        
        // 2. 引数に "app" が渡されたときに、上記の一致データのみを返すようモックを設定
        when(projectDao.findAll("app")).thenReturn(java.util.List.of(p2, p3));

        // 3. Service を実行
        var response = projectService.findAll("app");

        // 4. 検証：正しくDTOに変換されていることと、DAOへの引数伝播を確認
        assertThat(response).hasSize(2);
        assertThat(response.get(0).getProjectKey()).isEqualTo("PROJ-B");
        assertThat(response.get(1).getProjectKey()).isEqualTo("PROJ-C");
        
        // ServiceがDAOのfindAll("app")を正しく呼んだか検証
        verify(projectDao).findAll("app");
    }

    @Test
    @org.junit.jupiter.api.DisplayName("findAll：一致するプロジェクトがない（DAOが空配列を返す）場合、空の配列が返されること")
    void findAllReturnsEmptyListWhenNoProjectMatches() {
        // 1. データベースでヒットしなかった想定で空リストを返すようモックを設定
        when(projectDao.findAll("XYZ")).thenReturn(java.util.List.of());

        // 2. Service を执行
        var response = projectService.findAll("XYZ");

        // 3. 検証
        assertThat(response).isEmpty();
        verify(projectDao).findAll("XYZ");
    }


    private ProjectRequest request() {
        return new ProjectRequest("APP", "アプリ開発", "ProjectFlow開発用");
    }

    private Project project() {
        return new Project(1L, "APP", "アプリ開発", "ProjectFlow開発用", NOW, NOW);
    }
}
