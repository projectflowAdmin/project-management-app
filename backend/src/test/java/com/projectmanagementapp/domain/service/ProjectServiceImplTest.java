package com.projectmanagementapp.domain.service;

import com.projectmanagementapp.domain.dao.ProjectDao;
import com.projectmanagementapp.domain.model.Project;
import com.projectmanagementapp.dto.ProjectRequest;
import com.projectmanagementapp.exception.BusinessException;
import com.projectmanagementapp.exception.ResourceNotFoundException;
import com.projectmanagementapp.message.CommonMessage;
import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
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
    @DisplayName("プロジェクト登録時、大文字小文字を区別せずキーが重複している場合は例外をスロー")
    void createThrowsBusinessExceptionWhenProjectKeyIsDuplicatedIgnoreCase() {
        // 1. 準備：すでに大文字の「APP」が存在する状態をシミュレート
        // projectDao.findAll() が既存のプロジェクトリスト（"APP" を含む）を返すように設定
        java.util.List<com.projectmanagementapp.domain.model.Project> existingProjects = java.util.List.of(project());
        when(projectDao.findAll()).thenReturn(existingProjects);

        ProjectRequest request = request(); // デフォルトは "APP"（小文字「app」でテストしたい場合は new ProjectRequest("app", ...) にしてもOK）

        // 2. 検証：登録を実行したときに BusinessException がスローされること
        assertThatThrownBy(() -> projectService.create(request))
            .isInstanceOf(BusinessException.class)
            .hasMessage(CommonMessage.PROJECT_KEY_ALREADY_USED);

        // 3. 呼び出し検証
        verify(projectDao).findAll();
        verifyNoMoreInteractions(projectDao);
    }

    @Test
    @DisplayName("プロジェクト更新時、大文字小文字を区別せず他プロジェクトとキーが重複している場合は例外をスロー")
    void updateThrowsBusinessExceptionWhenProjectKeyIsUsedByOtherProjectIgnoreCase() {
        // 1. 準備：ID=1L のプロジェクト（APP）を更新しようとするが、
        // すでに別のプロジェクト（ID=2L, キー="APP"）が存在している状態をシミュレート
        var otherProject = new com.projectmanagementapp.domain.model.Project(2L, "APP", "他アプリ", "説明", NOW, NOW);
        java.util.List<com.projectmanagementapp.domain.model.Project> existingProjects = java.util.List.of(project(), otherProject);
        
        when(projectDao.findById(1L)).thenReturn(project());
        when(projectDao.findAll()).thenReturn(existingProjects);

        // 自分（ID=1L）のキーを小文字の「app」に更新しようとするが、他（ID=2L）が「APP」を握っているためエラーになるケース
        ProjectRequest request = new ProjectRequest("app", "アプリ開発", "ProjectFlow開発用");

        // 2. 検証：更新を実行したときに BusinessException がスローされること
        assertThatThrownBy(() -> projectService.update(1L, request))
            .isInstanceOf(BusinessException.class)
            .hasMessage(CommonMessage.PROJECT_KEY_ALREADY_USED);
    }
    
    private ProjectRequest request() {
        return new ProjectRequest("APP", "アプリ開発", "ProjectFlow開発用");
    }

    private Project project() {
        return new Project(1L, "APP", "アプリ開発", "ProjectFlow開発用", NOW, NOW);
    }
}
