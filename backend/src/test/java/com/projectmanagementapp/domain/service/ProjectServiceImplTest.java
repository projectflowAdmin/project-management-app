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

    private ProjectRequest request() {
        return new ProjectRequest("APP", "アプリ開発", "ProjectFlow開発用");
    }

    private Project project() {
        return new Project(1L, "APP", "アプリ開発", "ProjectFlow開発用", NOW, NOW);
    }
}
