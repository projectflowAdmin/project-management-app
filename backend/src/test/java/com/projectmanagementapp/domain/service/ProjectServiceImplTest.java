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
        var p1 = project();
        var p2 = new Project(2L, "MGR", "管理ツール", "説明文", NOW, NOW);
        when(projectDao.findAll()).thenReturn(java.util.List.of(p1, p2));

        var responseNull = projectService.findAll(null);
        assertThat(responseNull).hasSize(2);

        var responseBlank = projectService.findAll(" ");
        assertThat(responseBlank).hasSize(2);
    }

    @Test
    @org.junit.jupiter.api.DisplayName("findAll：プロジェクトキー、名称、説明のいずれかに大文字小文字を区別せず部分一致するデータが取得できること")
    void findAllFiltersProjectsByKeywordIgnoreCase() {
        var p1 = new Project(1L, "PROJ-A", "アプリ開発", "概要テキスト", NOW, NOW);
        var p2 = new Project(2L, "PROJ-B", "システム設計", "APPの解説", NOW, NOW); 
        var p3 = new Project(3L, "PROJ-C", "WEB APP", null, NOW, NOW);         
        when(projectDao.findAll()).thenReturn(java.util.List.of(p1, p2, p3));

        var response = projectService.findAll("app");

        assertThat(response).hasSize(2);
        assertThat(response.get(0).getProjectKey()).isEqualTo("PROJ-B");
        assertThat(response.get(1).getProjectKey()).isEqualTo("PROJ-C");
    }

    @Test
    @org.junit.jupiter.api.DisplayName("findAll：一致するプロジェクトがない場合、空の配列が返されること")
    void findAllReturnsEmptyListWhenNoProjectMatches() {
        when(projectDao.findAll()).thenReturn(java.util.List.of(project()));

        var response = projectService.findAll("XYZ");

        assertThat(response).isEmpty();
    }


    private ProjectRequest request() {
        return new ProjectRequest("APP", "アプリ開発", "ProjectFlow開発用");
    }

    private Project project() {
        return new Project(1L, "APP", "アプリ開発", "ProjectFlow開発用", NOW, NOW);
    }
}
