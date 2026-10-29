package com.projectmanagementapp.controller;

import com.projectmanagementapp.domain.service.ProjectService;
import com.projectmanagementapp.dto.ProjectResponse;
import com.projectmanagementapp.exception.BusinessException;
import com.projectmanagementapp.exception.GlobalExceptionHandler;
import com.projectmanagementapp.exception.ResourceNotFoundException;
import com.projectmanagementapp.message.CommonMessage;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class ProjectControllerTest {

    private static final LocalDateTime NOW = LocalDateTime.of(2026, 7, 5, 10, 0);

    private MockMvc mockMvc;

    @Mock
    private ProjectService projectService;

    @BeforeEach
    void setUp() {
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        validator.afterPropertiesSet();
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter(
            Jackson2ObjectMapperBuilder.json().modulesToInstall(JavaTimeModule.class).build()
        );

        mockMvc = MockMvcBuilders.standaloneSetup(new ProjectController(projectService))
            .setControllerAdvice(new GlobalExceptionHandler())
            .setValidator(validator)
            .setMessageConverters(jsonConverter)
            .build();
    }

    // @Test
    // void findAllReturnsProjects() throws Exception {
    //     when(projectService.findAll()).thenReturn(List.of(projectResponse()));

    //     mockMvc.perform(get("/api/projects"))
    //         .andExpect(status().isOk())
    //         .andExpect(jsonPath("$[0].id").value(1))
    //         .andExpect(jsonPath("$[0].projectKey").value("APP"))
    //         .andExpect(jsonPath("$[0].name").value("アプリ開発"));
    // }

    @Test
    @org.junit.jupiter.api.DisplayName("findAll：キーワード未指定の場合に全件取得できること")
    void findAll_shouldReturnAllProjects_whenNoKeywordProvided() throws Exception {
        // パラメータが指定されない（null）場合の挙動をモック
        when(projectService.findAll(null)).thenReturn(List.of(projectResponse()));

        mockMvc.perform(get("/api/projects"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id").value(1))
            .andExpect(jsonPath("$[0].projectKey").value("APP"))
            .andExpect(jsonPath("$[0].name").value("アプリ開発"));

        verify(projectService).findAll(null);
    }

    @Test
    @org.junit.jupiter.api.DisplayName("findAll：キーワードを指定した場合にフィルタリングされたリストが取得できること")
    void findAll_shouldReturnFilteredProjects_whenKeywordIsProvided() throws Exception {
        // キーワード「app」が指定された場合の挙動をモック
        when(projectService.findAll("app")).thenReturn(List.of(projectResponse()));

        // ?keyword=app パラメータ付きで GET リクエストを実行
        mockMvc.perform(get("/api/projects").param("keyword", "app"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id").value(1))
            .andExpect(jsonPath("$[0].projectKey").value("APP"))
            .andExpect(jsonPath("$[0].name").value("アプリ開発"));

        verify(projectService).findAll("app");
    }


    @Test
    void findByIdReturnsNotFoundWhenProjectDoesNotExist() throws Exception {
        when(projectService.findById(99L)).thenThrow(new ResourceNotFoundException(CommonMessage.PROJECT_NOT_FOUND));

        mockMvc.perform(get("/api/projects/99"))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.message").value(CommonMessage.PROJECT_NOT_FOUND));
    }

    @Test
    void createReturnsCreated() throws Exception {
        when(projectService.create(any())).thenReturn(projectResponse());

        mockMvc.perform(post("/api/projects")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                      "projectKey": "APP",
                      "name": "アプリ開発",
                      "description": "ProjectFlow開発用"
                    }
                    """))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.projectKey").value("APP"));
    }

    @Test
    void createReturnsBadRequestWhenProjectKeyIsInvalid() throws Exception {
        mockMvc.perform(post("/api/projects")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                      "projectKey": "APP!",
                      "name": "アプリ開発",
                      "description": "ProjectFlow開発用"
                    }
                    """))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.details[0]").exists());

        verifyNoInteractions(projectService);
    }

    @Test
    void updateReturnsBusinessErrorWhenProjectKeyIsDuplicated() throws Exception {
        when(projectService.update(eq(1L), any())).thenThrow(new BusinessException(CommonMessage.PROJECT_KEY_ALREADY_USED));

        mockMvc.perform(put("/api/projects/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                      "projectKey": "APP",
                      "name": "アプリ開発",
                      "description": "ProjectFlow開発用"
                    }
                    """))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.message").value(CommonMessage.PROJECT_KEY_ALREADY_USED));
    }

    @Test
    void deleteReturnsNoContent() throws Exception {
        mockMvc.perform(delete("/api/projects/1"))
            .andExpect(status().isNoContent());

        verify(projectService).delete(1L);
    }

    @Test
    void deleteReturnsBadRequestWhenProjectHasIssues() throws Exception {
        doThrow(new BusinessException(CommonMessage.PROJECT_HAS_ISSUES))
            .when(projectService).delete(1L);

        mockMvc.perform(delete("/api/projects/1"))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.message").value(CommonMessage.PROJECT_HAS_ISSUES));
    }

    private ProjectResponse projectResponse() {
        return new ProjectResponse(1L, "APP", "アプリ開発", "ProjectFlow開発用", NOW, NOW);
    }
}
