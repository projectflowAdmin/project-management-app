This file is a merged representation of a subset of the codebase, containing specifically included files and files not matching ignore patterns, combined into a single document by Repomix.
The content has been processed where content has been formatted for parsing in markdown style.

# File Summary

## Purpose
This file contains a packed representation of a subset of the repository's contents that is considered the most important context.
It is designed to be easily consumable by AI systems for analysis, code review,
or other automated processes.

## File Format
The content is organized as follows:
1. This summary section
2. Repository information
3. Directory structure
4. Repository files (if enabled)
5. Multiple file entries, each consisting of:
  a. A header with the file path (## File: path/to/file)
  b. The full contents of the file in a code block

## Usage Guidelines
- This file should be treated as read-only. Any changes should be made to the
  original repository files, not this packed version.
- When processing this file, use the file path to distinguish
  between different files in the repository.
- Be aware that this file may contain sensitive information. Handle it with
  the same level of security as you would the original repository.
- Pay special attention to the Repository Description. These contain important context and guidelines specific to this project.

## Notes
- Some files may have been excluded based on .gitignore rules and Repomix's configuration
- Binary files are not included in this packed representation. Please refer to the Repository Structure section for a complete list of file paths, including binary files
- Only files matching these patterns are included: docs/01_システム仕様書.md, docs/02_画面一覧.md, docs/03_API一覧.md, docs/04_DB設計.md, docs/05_開発ルール.md, docs/06_レビュー観点.md, .github/ISSUE_TEMPLATE/feature_request.md, frontend/package.json, frontend/tsconfig.json, frontend/tsconfig.node.json, frontend/vite.config.ts, frontend/src/**/*.vue, frontend/src/**/*.ts, backend/pom.xml, backend/src/main/java/**/*.java, backend/src/main/resources/application.yml, backend/src/main/resources/mapper/**/*.xml, backend/src/main/resources/sql/**/*.sql, backend/src/main/resources/sql/**/*.md
- Files matching these patterns are excluded: dify/projectflow-dify-context.md, **/.git/**, **/node_modules/**, **/target/**, **/dist/**, **/build/**, **/coverage/**, **/.idea/**, **/.vscode/**, **/.cache/**, **/.npm/**, **/.yarn/**, **/.pnpm-store/**, **/*.log, **/.DS_Store, **/Thumbs.db, **/.env, **/.env.*, **/*credential*, **/*secret*, **/*.pem, **/*.key, **/*.p12, **/*.pfx, **/application-local.yml, **/application-local.yaml, **/*-credentials.yml, **/*-credentials.yaml, **/*.lock, **/package-lock.json, **/yarn.lock, **/pnpm-lock.yaml, **/test-results/**, **/__tests__/**, **/fixtures/**, **/__fixtures__/**, **/snapshots/**, **/__snapshots__/**, **/generated/**, **/*.generated.*, **/*.png, **/*.jpg, **/*.jpeg, **/*.gif, **/*.webp, **/*.ico, **/*.svg, **/*.woff, **/*.woff2, **/*.ttf, **/*.otf, **/*.pdf, **/*.zip, **/*.jar, **/*.class, **/*.exe, **/*.dll
- Files matching patterns in .gitignore are excluded
- Files matching default ignore patterns are excluded
- Content has been formatted for parsing in markdown style
- Long base64 data strings (e.g., data:image/png;base64,...) have been truncated to reduce token count

# User Provided Header
このファイルはProjectFlowの追加開発Issue生成を支援するため、GitHubリポジトリの現在の仕様・Frontend・Backend・DB・IssueテンプレートをRepomixで自動収集したDify入力用コンテキストです。

このファイルは正式なシステム仕様書ではありません。手動編集しないでください。developブランチ更新時にGitHub Actionsによって自動更新されます。

追加開発Issueを生成する場合は、既に実装されている機能を新規要件として提案しないでください。Frontend課題の場合は、既存Backend APIのみで完遂可能か確認してください。Backend課題の場合は、Frontend変更なしで完遂可能か確認してください。

# Directory Structure
````
.github/
  ISSUE_TEMPLATE/
    feature_request.md
backend/
  src/
    main/
      java/
        com/
          projectmanagementapp/
            config/
              CorsProperties.java
              WebConfig.java
            controller/
              DashboardController.java
              HealthController.java
              IssueController.java
              ProjectController.java
            domain/
              dao/
                mapper/
                  IssueMapper.java
                  ProjectMapper.java
                IssueDao.java
                IssueDaoImpl.java
                ProjectDao.java
                ProjectDaoImpl.java
              model/
                Issue.java
                IssuePriority.java
                IssueStatus.java
                Project.java
              service/
                DashboardService.java
                DashboardServiceImpl.java
                IssueService.java
                IssueServiceImpl.java
                ProjectService.java
                ProjectServiceImpl.java
            dto/
              DashboardResponse.java
              DashboardSummaryResponse.java
              ErrorResponse.java
              IssueDetailResponse.java
              IssueRequest.java
              IssueResponse.java
              IssueSummaryResponse.java
              ProjectRequest.java
              ProjectResponse.java
            exception/
              BusinessException.java
              ErrorResponse.java
              GlobalExceptionHandler.java
              ResourceNotFoundException.java
            message/
              CommonMessage.java
            ProjectFlowApplication.java
      resources/
        mapper/
          IssueMapper.xml
          ProjectMapper.xml
        sql/
          changes/
            README.md
          init/
            001_create_schema.sql
            002_create_projects_table.sql
            003_create_issues_table.sql
            004_create_indexes.sql
            005_create_sql_history.sql
            006_insert_initial_data.sql
          README.md
        application.yml
  pom.xml
docs/
  01_システム仕様書.md
  02_画面一覧.md
  03_API一覧.md
  04_DB設計.md
  05_開発ルール.md
  06_レビュー観点.md
frontend/
  src/
    api/
      dashboardApi.ts
      healthApi.ts
      httpClient.ts
      issueApi.ts
      projectApi.ts
    components/
      PageSection.vue
    router/
      index.ts
    types/
      dashboard.ts
      error.ts
      issue.ts
      project.ts
    views/
      Dashboard/
        DashboardView.vue
      IssueDetail/
        IssueDetailView.vue
      IssueForm/
        IssueFormView.vue
      IssueList/
        IssueListView.vue
      Issues/
        IssuesView.vue
      Login/
        LoginView.vue
      ProjectForm/
        ProjectFormView.vue
      ProjectList/
        ProjectListView.vue
      Projects/
        ProjectsView.vue
    App.vue
    main.ts
    vite-env.d.ts
  package.json
  tsconfig.json
  tsconfig.node.json
  vite.config.ts
````

# Files

## File: .github/ISSUE_TEMPLATE/feature_request.md
````markdown
---
name: 機能追加
about: ProjectFlowへ新しい機能や仕様変更を追加するためのテンプレート
title: "[フロントエンド/バックエンド] "
labels: ["enhancement"]
assignees: ""
---

# 概要

追加する機能を一言で記載してください。

---

## 背景

なぜ必要なのか

---

## 目的

このIssueで実現したいこと

---

## 対応内容

実装内容

- 

- 

- 

---

## 受入条件

- 

---


## 画面影響

- なし

- あり

対象画面：

---

## API影響

- なし

- あり

対象API：

---

## DB影響

- なし

- あり

対象テーブル：

---

## Backend影響

- 

---

## Frontend影響

- 

---

## docs更新対象

- [ ] 01_システム仕様書.md
- [ ] 02_画面一覧.md
- [ ] 03_API一覧.md
- [ ] 04_DB設計.md
- [ ] 05_コーディング規約.md
- [ ] 06_レビュー観点.md
- [ ] 07_環境構築手順書.md

---

### テスト観点

- 

---

## 備考

自由記述

---
````

## File: backend/src/main/java/com/projectmanagementapp/config/CorsProperties.java
````java
package com.projectmanagementapp.config;

import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "projectmanagementapp.cors")
public class CorsProperties {

    private List<String> allowedOrigins = new ArrayList<>();

    public List<String> getAllowedOrigins() {
        return allowedOrigins;
    }

    public void setAllowedOrigins(List<String> allowedOrigins) {
        this.allowedOrigins = allowedOrigins;
    }
}
````

## File: backend/src/main/java/com/projectmanagementapp/config/WebConfig.java
````java
package com.projectmanagementapp.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableConfigurationProperties(CorsProperties.class)
public class WebConfig implements WebMvcConfigurer {

    private final CorsProperties corsProperties;

    public WebConfig(CorsProperties corsProperties) {
        this.corsProperties = corsProperties;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
            .allowedOrigins(corsProperties.getAllowedOrigins().toArray(String[]::new))
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
            .allowedHeaders("*");
    }
}
````

## File: backend/src/main/java/com/projectmanagementapp/controller/DashboardController.java
````java
package com.projectmanagementapp.controller;

import com.projectmanagementapp.domain.service.DashboardService;
import com.projectmanagementapp.dto.DashboardResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping
    public DashboardResponse getDashboard() {
        return dashboardService.getDashboard();
    }
}
````

## File: backend/src/main/java/com/projectmanagementapp/controller/HealthController.java
````java
package com.projectmanagementapp.controller;

import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/api/health")
    public Map<String, String> health() {
        return Map.of("status", "UP");
    }
}
````

## File: backend/src/main/java/com/projectmanagementapp/controller/IssueController.java
````java
package com.projectmanagementapp.controller;

import com.projectmanagementapp.domain.model.IssueStatus;
import com.projectmanagementapp.domain.service.IssueService;
import com.projectmanagementapp.dto.IssueRequest;
import com.projectmanagementapp.dto.IssueResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/issues")
public class IssueController {

    private final IssueService issueService;

    public IssueController(IssueService issueService) {
        this.issueService = issueService;
    }

    @GetMapping
    public List<IssueResponse> findAll(
        @RequestParam(required = false) Long projectId,
        @RequestParam(required = false) IssueStatus status
    ) {
        return issueService.findAll(projectId, status);
    }

    @GetMapping("/{id}")
    public IssueResponse findById(@PathVariable Long id) {
        return issueService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public IssueResponse create(@Valid @RequestBody IssueRequest request) {
        return issueService.create(request);
    }

    @PutMapping("/{id}")
    public IssueResponse update(@PathVariable Long id, @Valid @RequestBody IssueRequest request) {
        return issueService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        issueService.delete(id);
    }
}
````

## File: backend/src/main/java/com/projectmanagementapp/controller/ProjectController.java
````java
package com.projectmanagementapp.controller;

import com.projectmanagementapp.domain.service.ProjectService;
import com.projectmanagementapp.dto.ProjectRequest;
import com.projectmanagementapp.dto.ProjectResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public List<ProjectResponse> findAll() {
        return projectService.findAll();
    }

    @GetMapping("/{id}")
    public ProjectResponse findById(@PathVariable Long id) {
        return projectService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProjectResponse create(@Valid @RequestBody ProjectRequest request) {
        return projectService.create(request);
    }

    @PutMapping("/{id}")
    public ProjectResponse update(@PathVariable Long id, @Valid @RequestBody ProjectRequest request) {
        return projectService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        projectService.delete(id);
    }
}
````

## File: backend/src/main/java/com/projectmanagementapp/domain/dao/mapper/IssueMapper.java
````java
package com.projectmanagementapp.domain.dao.mapper;

import com.projectmanagementapp.domain.model.Issue;
import com.projectmanagementapp.domain.model.IssueStatus;
import com.projectmanagementapp.dto.IssueRequest;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IssueMapper {
    List<Issue> findAll();
    Issue findById(Long id);
    List<Issue> findByProjectId(Long projectId);
    List<Issue> findByStatus(IssueStatus status);
    List<Issue> findByProjectIdAndStatus(@Param("projectId") Long projectId, @Param("status") IssueStatus status);
    List<Issue> findRecent(int limit);
    Issue insert(IssueRequest request);
    Issue update(@Param("id") Long id, @Param("request") IssueRequest request);
    int delete(Long id);
    long countAll();
    long countByStatus(IssueStatus status);
}
````

## File: backend/src/main/java/com/projectmanagementapp/domain/dao/mapper/ProjectMapper.java
````java
package com.projectmanagementapp.domain.dao.mapper;

import com.projectmanagementapp.domain.model.Project;
import com.projectmanagementapp.dto.ProjectRequest;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjectMapper {
    List<Project> findAll();
    Project findById(Long id);
    Project findByProjectKey(String projectKey);
    boolean existsByProjectKey(String projectKey);
    boolean existsByProjectKeyAndIdNot(@Param("projectKey") String projectKey, @Param("id") Long id);
    Project insert(ProjectRequest request);
    Project update(@Param("id") Long id, @Param("request") ProjectRequest request);
    int delete(Long id);
    long countAll();
    long countIssuesByProjectId(Long projectId);
}
````

## File: backend/src/main/java/com/projectmanagementapp/domain/dao/IssueDao.java
````java
package com.projectmanagementapp.domain.dao;

import com.projectmanagementapp.domain.model.Issue;
import com.projectmanagementapp.domain.model.IssueStatus;
import com.projectmanagementapp.dto.IssueRequest;
import java.util.List;

public interface IssueDao {
    List<Issue> findAll();
    Issue findById(Long id);
    List<Issue> findByProjectId(Long projectId);
    List<Issue> findByStatus(IssueStatus status);
    List<Issue> findByProjectIdAndStatus(Long projectId, IssueStatus status);
    List<Issue> findRecent(int limit);
    Issue insert(IssueRequest request);
    Issue update(Long id, IssueRequest request);
    int delete(Long id);
    long countAll();
    long countByStatus(IssueStatus status);
}
````

## File: backend/src/main/java/com/projectmanagementapp/domain/dao/IssueDaoImpl.java
````java
package com.projectmanagementapp.domain.dao;

import com.projectmanagementapp.domain.dao.mapper.IssueMapper;
import com.projectmanagementapp.domain.model.Issue;
import com.projectmanagementapp.domain.model.IssueStatus;
import com.projectmanagementapp.dto.IssueRequest;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class IssueDaoImpl implements IssueDao {

    private final IssueMapper issueMapper;

    public IssueDaoImpl(IssueMapper issueMapper) {
        this.issueMapper = issueMapper;
    }

    @Override
    public List<Issue> findAll() {
        return issueMapper.findAll();
    }

    @Override
    public Issue findById(Long id) {
        return issueMapper.findById(id);
    }

    @Override
    public List<Issue> findByProjectId(Long projectId) {
        return issueMapper.findByProjectId(projectId);
    }

    @Override
    public List<Issue> findByStatus(IssueStatus status) {
        return issueMapper.findByStatus(status);
    }

    @Override
    public List<Issue> findByProjectIdAndStatus(Long projectId, IssueStatus status) {
        return issueMapper.findByProjectIdAndStatus(projectId, status);
    }

    @Override
    public List<Issue> findRecent(int limit) {
        return issueMapper.findRecent(limit);
    }

    @Override
    public Issue insert(IssueRequest request) {
        return issueMapper.insert(request);
    }

    @Override
    public Issue update(Long id, IssueRequest request) {
        return issueMapper.update(id, request);
    }

    @Override
    public int delete(Long id) {
        return issueMapper.delete(id);
    }

    @Override
    public long countAll() {
        return issueMapper.countAll();
    }

    @Override
    public long countByStatus(IssueStatus status) {
        return issueMapper.countByStatus(status);
    }
}
````

## File: backend/src/main/java/com/projectmanagementapp/domain/dao/ProjectDao.java
````java
package com.projectmanagementapp.domain.dao;

import com.projectmanagementapp.domain.model.Project;
import com.projectmanagementapp.dto.ProjectRequest;
import java.util.List;

public interface ProjectDao {
    List<Project> findAll();
    Project findById(Long id);
    Project findByProjectKey(String projectKey);
    boolean existsByProjectKey(String projectKey);
    boolean existsByProjectKeyAndIdNot(String projectKey, Long id);
    Project insert(ProjectRequest request);
    Project update(Long id, ProjectRequest request);
    int delete(Long id);
    long countAll();
    long countIssuesByProjectId(Long projectId);
}
````

## File: backend/src/main/java/com/projectmanagementapp/domain/dao/ProjectDaoImpl.java
````java
package com.projectmanagementapp.domain.dao;

import com.projectmanagementapp.domain.dao.mapper.ProjectMapper;
import com.projectmanagementapp.domain.model.Project;
import com.projectmanagementapp.dto.ProjectRequest;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class ProjectDaoImpl implements ProjectDao {

    private final ProjectMapper projectMapper;

    public ProjectDaoImpl(ProjectMapper projectMapper) {
        this.projectMapper = projectMapper;
    }

    @Override
    public List<Project> findAll() {
        return projectMapper.findAll();
    }

    @Override
    public Project findById(Long id) {
        return projectMapper.findById(id);
    }

    @Override
    public Project findByProjectKey(String projectKey) {
        return projectMapper.findByProjectKey(projectKey);
    }

    @Override
    public boolean existsByProjectKey(String projectKey) {
        return projectMapper.existsByProjectKey(projectKey);
    }

    @Override
    public boolean existsByProjectKeyAndIdNot(String projectKey, Long id) {
        return projectMapper.existsByProjectKeyAndIdNot(projectKey, id);
    }

    @Override
    public Project insert(ProjectRequest request) {
        return projectMapper.insert(request);
    }

    @Override
    public Project update(Long id, ProjectRequest request) {
        return projectMapper.update(id, request);
    }

    @Override
    public int delete(Long id) {
        return projectMapper.delete(id);
    }

    @Override
    public long countAll() {
        return projectMapper.countAll();
    }

    @Override
    public long countIssuesByProjectId(Long projectId) {
        return projectMapper.countIssuesByProjectId(projectId);
    }
}
````

## File: backend/src/main/java/com/projectmanagementapp/domain/model/Issue.java
````java
package com.projectmanagementapp.domain.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Issue {
    private Long id;
    private Long projectId;
    private String projectKey;
    private String projectName;
    private String title;
    private String description;
    private IssueStatus status;
    private IssuePriority priority;
    private String assigneeName;
    private LocalDate dueDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
````

## File: backend/src/main/java/com/projectmanagementapp/domain/model/IssuePriority.java
````java
package com.projectmanagementapp.domain.model;

public enum IssuePriority {
    LOW("低"),
    MEDIUM("中"),
    HIGH("高");

    private final String label;

    IssuePriority(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
````

## File: backend/src/main/java/com/projectmanagementapp/domain/model/IssueStatus.java
````java
package com.projectmanagementapp.domain.model;

public enum IssueStatus {
    TODO("未着手"),
    IN_PROGRESS("対応中"),
    REVIEW("レビュー中"),
    DONE("完了");

    private final String label;

    IssueStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
````

## File: backend/src/main/java/com/projectmanagementapp/domain/model/Project.java
````java
package com.projectmanagementapp.domain.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Project {
    private Long id;
    private String projectKey;
    private String name;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
````

## File: backend/src/main/java/com/projectmanagementapp/domain/service/DashboardService.java
````java
package com.projectmanagementapp.domain.service;

import com.projectmanagementapp.dto.DashboardResponse;

public interface DashboardService {
    DashboardResponse getDashboard();
}
````

## File: backend/src/main/java/com/projectmanagementapp/domain/service/DashboardServiceImpl.java
````java
package com.projectmanagementapp.domain.service;

import com.projectmanagementapp.domain.dao.IssueDao;
import com.projectmanagementapp.domain.dao.ProjectDao;
import com.projectmanagementapp.domain.model.IssueStatus;
import com.projectmanagementapp.dto.DashboardResponse;
import org.springframework.stereotype.Service;

@Service
public class DashboardServiceImpl implements DashboardService {

    private final ProjectDao projectDao;
    private final IssueDao issueDao;
    private final IssueServiceImpl issueService;

    public DashboardServiceImpl(ProjectDao projectDao, IssueDao issueDao, IssueServiceImpl issueService) {
        this.projectDao = projectDao;
        this.issueDao = issueDao;
        this.issueService = issueService;
    }

    @Override
    public DashboardResponse getDashboard() {
        return new DashboardResponse(
            projectDao.countAll(),
            issueDao.countAll(),
            issueDao.countByStatus(IssueStatus.TODO),
            issueDao.countByStatus(IssueStatus.IN_PROGRESS),
            issueDao.countByStatus(IssueStatus.REVIEW),
            issueDao.countByStatus(IssueStatus.DONE),
            issueDao.findRecent(5).stream().map(issueService::toResponse).toList()
        );
    }
}
````

## File: backend/src/main/java/com/projectmanagementapp/domain/service/IssueService.java
````java
package com.projectmanagementapp.domain.service;

import com.projectmanagementapp.domain.model.IssueStatus;
import com.projectmanagementapp.dto.IssueRequest;
import com.projectmanagementapp.dto.IssueResponse;
import java.util.List;

public interface IssueService {
    List<IssueResponse> findAll(Long projectId, IssueStatus status);
    IssueResponse findById(Long id);
    IssueResponse create(IssueRequest request);
    IssueResponse update(Long id, IssueRequest request);
    void delete(Long id);
}
````

## File: backend/src/main/java/com/projectmanagementapp/domain/service/IssueServiceImpl.java
````java
package com.projectmanagementapp.domain.service;

import com.projectmanagementapp.domain.dao.IssueDao;
import com.projectmanagementapp.domain.dao.ProjectDao;
import com.projectmanagementapp.domain.model.Issue;
import com.projectmanagementapp.domain.model.IssueStatus;
import com.projectmanagementapp.dto.IssueRequest;
import com.projectmanagementapp.dto.IssueResponse;
import com.projectmanagementapp.exception.ResourceNotFoundException;
import com.projectmanagementapp.message.CommonMessage;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class IssueServiceImpl implements IssueService {

    private final IssueDao issueDao;
    private final ProjectDao projectDao;

    public IssueServiceImpl(IssueDao issueDao, ProjectDao projectDao) {
        this.issueDao = issueDao;
        this.projectDao = projectDao;
    }

    @Override
    public List<IssueResponse> findAll(Long projectId, IssueStatus status) {
        List<Issue> issues;
        if (projectId != null && status != null) {
            issues = issueDao.findByProjectIdAndStatus(projectId, status);
        } else if (projectId != null) {
            issues = issueDao.findByProjectId(projectId);
        } else if (status != null) {
            issues = issueDao.findByStatus(status);
        } else {
            issues = issueDao.findAll();
        }
        return issues.stream().map(this::toResponse).toList();
    }

    @Override
    public IssueResponse findById(Long id) {
        return toResponse(findIssue(id));
    }

    @Override
    @Transactional
    public IssueResponse create(IssueRequest request) {
        ensureProjectExists(request.getProjectId());
        return toResponse(issueDao.insert(request));
    }

    @Override
    @Transactional
    public IssueResponse update(Long id, IssueRequest request) {
        findIssue(id);
        ensureProjectExists(request.getProjectId());
        return toResponse(issueDao.update(id, request));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        findIssue(id);
        issueDao.delete(id);
    }

    private void ensureProjectExists(Long projectId) {
        if (projectDao.findById(projectId) == null) {
            throw new ResourceNotFoundException(CommonMessage.PROJECT_NOT_FOUND);
        }
    }

    private Issue findIssue(Long id) {
        Issue issue = issueDao.findById(id);
        if (issue == null) {
            throw new ResourceNotFoundException(CommonMessage.ISSUE_NOT_FOUND);
        }
        return issue;
    }

    public IssueResponse toResponse(Issue issue) {
        return new IssueResponse(
            issue.getId(),
            issue.getProjectId(),
            issue.getProjectKey(),
            issue.getProjectName(),
            issue.getTitle(),
            issue.getDescription(),
            issue.getStatus().name(),
            issue.getStatus().getLabel(),
            issue.getPriority().name(),
            issue.getPriority().getLabel(),
            issue.getAssigneeName(),
            issue.getDueDate(),
            issue.getCreatedAt(),
            issue.getUpdatedAt()
        );
    }
}
````

## File: backend/src/main/java/com/projectmanagementapp/domain/service/ProjectService.java
````java
package com.projectmanagementapp.domain.service;

import com.projectmanagementapp.dto.ProjectRequest;
import com.projectmanagementapp.dto.ProjectResponse;
import java.util.List;

public interface ProjectService {
    List<ProjectResponse> findAll();
    ProjectResponse findById(Long id);
    ProjectResponse create(ProjectRequest request);
    ProjectResponse update(Long id, ProjectRequest request);
    void delete(Long id);
}
````

## File: backend/src/main/java/com/projectmanagementapp/domain/service/ProjectServiceImpl.java
````java
package com.projectmanagementapp.domain.service;

import com.projectmanagementapp.domain.dao.ProjectDao;
import com.projectmanagementapp.domain.model.Project;
import com.projectmanagementapp.dto.ProjectRequest;
import com.projectmanagementapp.dto.ProjectResponse;
import com.projectmanagementapp.exception.BusinessException;
import com.projectmanagementapp.exception.ResourceNotFoundException;
import com.projectmanagementapp.message.CommonMessage;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectDao projectDao;

    public ProjectServiceImpl(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    @Override
    public List<ProjectResponse> findAll() {
        return projectDao.findAll().stream().map(this::toResponse).toList();
    }

    @Override
    public ProjectResponse findById(Long id) {
        return toResponse(findProject(id));
    }

    @Override
    @Transactional
    public ProjectResponse create(ProjectRequest request) {
        if (projectDao.existsByProjectKey(request.getProjectKey())) {
            throw new BusinessException(CommonMessage.PROJECT_KEY_ALREADY_USED);
        }
        return toResponse(projectDao.insert(request));
    }

    @Override
    @Transactional
    public ProjectResponse update(Long id, ProjectRequest request) {
        findProject(id);
        if (projectDao.existsByProjectKeyAndIdNot(request.getProjectKey(), id)) {
            throw new BusinessException(CommonMessage.PROJECT_KEY_ALREADY_USED);
        }
        return toResponse(projectDao.update(id, request));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        findProject(id);
        if (projectDao.countIssuesByProjectId(id) > 0) {
            throw new BusinessException(CommonMessage.PROJECT_HAS_ISSUES);
        }
        projectDao.delete(id);
    }

    private Project findProject(Long id) {
        Project project = projectDao.findById(id);
        if (project == null) {
            throw new ResourceNotFoundException(CommonMessage.PROJECT_NOT_FOUND);
        }
        return project;
    }

    private ProjectResponse toResponse(Project project) {
        return new ProjectResponse(
            project.getId(),
            project.getProjectKey(),
            project.getName(),
            project.getDescription(),
            project.getCreatedAt(),
            project.getUpdatedAt()
        );
    }
}
````

## File: backend/src/main/java/com/projectmanagementapp/dto/DashboardResponse.java
````java
package com.projectmanagementapp.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboardResponse {

    private long projectCount;
    private long issueCount;
    private long todoCount;
    private long inProgressCount;
    private long reviewCount;
    private long doneCount;
    private List<IssueResponse> recentIssues;
}
````

## File: backend/src/main/java/com/projectmanagementapp/dto/DashboardSummaryResponse.java
````java
package com.projectmanagementapp.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DashboardSummaryResponse {
    private int activeProjects;
    private int openIssues;
    private int dueToday;
    private List<String> recentUpdates;
}
````

## File: backend/src/main/java/com/projectmanagementapp/dto/ErrorResponse.java
````java
package com.projectmanagementapp.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    private String message;
    private List<String> details;
}
````

## File: backend/src/main/java/com/projectmanagementapp/dto/IssueDetailResponse.java
````java
package com.projectmanagementapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IssueDetailResponse {
    private Long id;
    private String issueKey;
    private String projectName;
    private String title;
    private String status;
    private String priority;
    private String assignee;
    private String dueDate;
    private String description;
}
````

## File: backend/src/main/java/com/projectmanagementapp/dto/IssueRequest.java
````java
package com.projectmanagementapp.dto;

import com.projectmanagementapp.domain.model.IssuePriority;
import com.projectmanagementapp.domain.model.IssueStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IssueRequest {

    @NotNull
    private Long projectId;

    @NotBlank
    @Size(max = 200)
    private String title;

    @Size(max = 5000)
    private String description;

    @NotNull
    private IssueStatus status;

    @NotNull
    private IssuePriority priority;

    @Size(max = 100)
    private String assigneeName;

    private LocalDate dueDate;
}
````

## File: backend/src/main/java/com/projectmanagementapp/dto/IssueResponse.java
````java
package com.projectmanagementapp.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IssueResponse {

    private Long id;
    private Long projectId;
    private String projectKey;
    private String projectName;
    private String title;
    private String description;
    private String status;
    private String statusLabel;
    private String priority;
    private String priorityLabel;
    private String assigneeName;
    private LocalDate dueDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
````

## File: backend/src/main/java/com/projectmanagementapp/dto/IssueSummaryResponse.java
````java
package com.projectmanagementapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IssueSummaryResponse {
    private Long id;
    private String issueKey;
    private String projectName;
    private String title;
    private String status;
    private String priority;
    private String assignee;
}
````

## File: backend/src/main/java/com/projectmanagementapp/dto/ProjectRequest.java
````java
package com.projectmanagementapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectRequest {

    @NotBlank
    @Size(max = 20)
    @Pattern(regexp = "^[A-Za-z0-9_-]+$")
    private String projectKey;

    @NotBlank
    @Size(max = 100)
    private String name;

    @Size(max = 2000)
    private String description;
}
````

## File: backend/src/main/java/com/projectmanagementapp/dto/ProjectResponse.java
````java
package com.projectmanagementapp.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectResponse {

    private Long id;
    private String projectKey;
    private String name;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
````

## File: backend/src/main/java/com/projectmanagementapp/exception/BusinessException.java
````java
package com.projectmanagementapp.exception;

public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
````

## File: backend/src/main/java/com/projectmanagementapp/exception/ErrorResponse.java
````java
package com.projectmanagementapp.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private String code;
}
````

## File: backend/src/main/java/com/projectmanagementapp/exception/GlobalExceptionHandler.java
````java
package com.projectmanagementapp.exception;

import com.projectmanagementapp.dto.ErrorResponse;
import com.projectmanagementapp.message.CommonMessage;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleResourceNotFoundException(ResourceNotFoundException exception) {
        return new ErrorResponse(exception.getMessage(), List.of());
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleBusinessException(BusinessException exception) {
        return new ErrorResponse(exception.getMessage(), List.of());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidationException(MethodArgumentNotValidException exception) {
        List<String> details = exception.getBindingResult().getFieldErrors().stream()
            .map(this::formatFieldError)
            .toList();
        return new ErrorResponse(CommonMessage.VALIDATION_ERROR, details);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleNotReadableException() {
        return new ErrorResponse(CommonMessage.REQUEST_BODY_INVALID, List.of());
    }

    private String formatFieldError(FieldError error) {
        return error.getField() + ": " + error.getDefaultMessage();
    }
}
````

## File: backend/src/main/java/com/projectmanagementapp/exception/ResourceNotFoundException.java
````java
package com.projectmanagementapp.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
````

## File: backend/src/main/java/com/projectmanagementapp/message/CommonMessage.java
````java
package com.projectmanagementapp.message;

public final class CommonMessage {

    public static final String PROJECT_NOT_FOUND = "プロジェクトが見つかりません。";
    public static final String ISSUE_NOT_FOUND = "課題が見つかりません。";
    public static final String PROJECT_KEY_ALREADY_USED = "プロジェクトキーは既に使用されています。";
    public static final String PROJECT_HAS_ISSUES = "課題が存在するプロジェクトは削除できません。";
    public static final String VALIDATION_ERROR = "入力内容を確認してください。";
    public static final String REQUEST_BODY_INVALID = "リクエスト本文を確認してください。";

    private CommonMessage() {
    }
}
````

## File: backend/src/main/java/com/projectmanagementapp/ProjectFlowApplication.java
````java
package com.projectmanagementapp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.projectmanagementapp.domain.dao.mapper")
public class ProjectFlowApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectFlowApplication.class, args);
    }
}
````

## File: backend/src/main/resources/mapper/IssueMapper.xml
````xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
  PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
  "https://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.projectmanagementapp.domain.dao.mapper.IssueMapper">

  <resultMap id="issueResultMap" type="com.projectmanagementapp.domain.model.Issue">
    <constructor>
      <idArg column="id" javaType="java.lang.Long"/>
      <arg column="project_id" javaType="java.lang.Long"/>
      <arg column="project_key" javaType="java.lang.String"/>
      <arg column="project_name" javaType="java.lang.String"/>
      <arg column="title" javaType="java.lang.String"/>
      <arg column="description" javaType="java.lang.String"/>
      <arg column="status" javaType="com.projectmanagementapp.domain.model.IssueStatus"/>
      <arg column="priority" javaType="com.projectmanagementapp.domain.model.IssuePriority"/>
      <arg column="assignee_name" javaType="java.lang.String"/>
      <arg column="due_date" javaType="java.time.LocalDate"/>
      <arg column="created_at" javaType="java.time.LocalDateTime"/>
      <arg column="updated_at" javaType="java.time.LocalDateTime"/>
    </constructor>
  </resultMap>

  <sql id="issueColumns">
    i.id,
    i.project_id,
    p.project_key,
    p.name AS project_name,
    i.title,
    i.description,
    i.status,
    i.priority,
    i.assignee_name,
    i.due_date,
    i.created_at,
    i.updated_at
  </sql>

  <select id="findAll" resultMap="issueResultMap">
    SELECT <include refid="issueColumns"/>
    FROM management_app.issues i
    JOIN management_app.projects p ON p.id = i.project_id
    ORDER BY i.updated_at DESC, i.id DESC
  </select>

  <select id="findById" parameterType="long" resultMap="issueResultMap">
    SELECT <include refid="issueColumns"/>
    FROM management_app.issues i
    JOIN management_app.projects p ON p.id = i.project_id
    WHERE i.id = #{id}
  </select>

  <select id="findByProjectId" parameterType="long" resultMap="issueResultMap">
    SELECT <include refid="issueColumns"/>
    FROM management_app.issues i
    JOIN management_app.projects p ON p.id = i.project_id
    WHERE i.project_id = #{projectId}
    ORDER BY i.updated_at DESC, i.id DESC
  </select>

  <select id="findByStatus" parameterType="com.projectmanagementapp.domain.model.IssueStatus" resultMap="issueResultMap">
    SELECT <include refid="issueColumns"/>
    FROM management_app.issues i
    JOIN management_app.projects p ON p.id = i.project_id
    WHERE i.status = #{status}
    ORDER BY i.updated_at DESC, i.id DESC
  </select>

  <select id="findByProjectIdAndStatus" resultMap="issueResultMap">
    SELECT <include refid="issueColumns"/>
    FROM management_app.issues i
    JOIN management_app.projects p ON p.id = i.project_id
    WHERE i.project_id = #{projectId}
      AND i.status = #{status}
    ORDER BY i.updated_at DESC, i.id DESC
  </select>

  <select id="findRecent" parameterType="int" resultMap="issueResultMap">
    SELECT <include refid="issueColumns"/>
    FROM management_app.issues i
    JOIN management_app.projects p ON p.id = i.project_id
    ORDER BY i.updated_at DESC, i.id DESC
    LIMIT #{limit}
  </select>

  <select id="insert" parameterType="com.projectmanagementapp.dto.IssueRequest" resultMap="issueResultMap">
    WITH inserted AS (
      INSERT INTO management_app.issues (project_id, title, description, status, priority, assignee_name, due_date, created_at, updated_at)
      VALUES (#{projectId}, #{title}, #{description}, #{status}, #{priority}, #{assigneeName}, #{dueDate}, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
      RETURNING id, project_id, title, description, status, priority, assignee_name, due_date, created_at, updated_at
    )
    SELECT i.id,
           i.project_id,
           p.project_key,
           p.name AS project_name,
           i.title,
           i.description,
           i.status,
           i.priority,
           i.assignee_name,
           i.due_date,
           i.created_at,
           i.updated_at
    FROM inserted i
    JOIN management_app.projects p ON p.id = i.project_id
  </select>

  <select id="update" resultMap="issueResultMap">
    WITH updated AS (
      UPDATE management_app.issues
      SET project_id = #{request.projectId},
          title = #{request.title},
          description = #{request.description},
          status = #{request.status},
          priority = #{request.priority},
          assignee_name = #{request.assigneeName},
          due_date = #{request.dueDate},
          updated_at = CURRENT_TIMESTAMP
      WHERE id = #{id}
      RETURNING id, project_id, title, description, status, priority, assignee_name, due_date, created_at, updated_at
    )
    SELECT i.id,
           i.project_id,
           p.project_key,
           p.name AS project_name,
           i.title,
           i.description,
           i.status,
           i.priority,
           i.assignee_name,
           i.due_date,
           i.created_at,
           i.updated_at
    FROM updated i
    JOIN management_app.projects p ON p.id = i.project_id
  </select>

  <delete id="delete" parameterType="long">
    DELETE FROM management_app.issues
    WHERE id = #{id}
  </delete>

  <select id="countAll" resultType="long">
    SELECT COUNT(*)
    FROM management_app.issues
  </select>

  <select id="countByStatus" parameterType="com.projectmanagementapp.domain.model.IssueStatus" resultType="long">
    SELECT COUNT(*)
    FROM management_app.issues
    WHERE status = #{status}
  </select>
</mapper>
````

## File: backend/src/main/resources/mapper/ProjectMapper.xml
````xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
  PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
  "https://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.projectmanagementapp.domain.dao.mapper.ProjectMapper">

  <resultMap id="projectResultMap" type="com.projectmanagementapp.domain.model.Project">
    <constructor>
      <idArg column="id" javaType="java.lang.Long"/>
      <arg column="project_key" javaType="java.lang.String"/>
      <arg column="name" javaType="java.lang.String"/>
      <arg column="description" javaType="java.lang.String"/>
      <arg column="created_at" javaType="java.time.LocalDateTime"/>
      <arg column="updated_at" javaType="java.time.LocalDateTime"/>
    </constructor>
  </resultMap>

  <select id="findAll" resultMap="projectResultMap">
    SELECT id, project_key, name, description, created_at, updated_at
    FROM management_app.projects
    ORDER BY id
  </select>

  <select id="findById" parameterType="long" resultMap="projectResultMap">
    SELECT id, project_key, name, description, created_at, updated_at
    FROM management_app.projects
    WHERE id = #{id}
  </select>

  <select id="findByProjectKey" parameterType="string" resultMap="projectResultMap">
    SELECT id, project_key, name, description, created_at, updated_at
    FROM management_app.projects
    WHERE project_key = #{projectKey}
  </select>

  <select id="existsByProjectKey" parameterType="string" resultType="boolean">
    SELECT EXISTS (
      SELECT 1
      FROM management_app.projects
      WHERE project_key = #{projectKey}
    )
  </select>

  <select id="existsByProjectKeyAndIdNot" resultType="boolean">
    SELECT EXISTS (
      SELECT 1
      FROM management_app.projects
      WHERE project_key = #{projectKey}
        AND id != #{id}
    )
  </select>

  <select id="insert" parameterType="com.projectmanagementapp.dto.ProjectRequest" resultMap="projectResultMap">
    INSERT INTO management_app.projects (project_key, name, description, created_at, updated_at)
    VALUES (#{projectKey}, #{name}, #{description}, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
    RETURNING id, project_key, name, description, created_at, updated_at
  </select>

  <select id="update" resultMap="projectResultMap">
    UPDATE management_app.projects
    SET project_key = #{request.projectKey},
        name = #{request.name},
        description = #{request.description},
        updated_at = CURRENT_TIMESTAMP
    WHERE id = #{id}
    RETURNING id, project_key, name, description, created_at, updated_at
  </select>

  <delete id="delete" parameterType="long">
    DELETE FROM management_app.projects
    WHERE id = #{id}
  </delete>

  <select id="countAll" resultType="long">
    SELECT COUNT(*)
    FROM management_app.projects
  </select>

  <select id="countIssuesByProjectId" parameterType="long" resultType="long">
    SELECT COUNT(*)
    FROM management_app.issues
    WHERE project_id = #{projectId}
  </select>
</mapper>
````

## File: backend/src/main/resources/sql/changes/README.md
````markdown
# 追加変更用SQL

追加変更用SQLは `backend/src/main/resources/sql/changes` に配置します。

## テンプレート

```sql
-- ==================================================
-- Script No : 101
-- File Name : 101_example_change.sql
-- Purpose   : 変更目的を記載
-- Related Issue : #xxx
-- Created By:
-- Created At:
-- ==================================================

BEGIN;

-- SQLを記載

INSERT INTO management_app.sql_history (
    script_no,
    script_name,
    description,
    executed_by
)
VALUES (
    '101',
    '101_example_change.sql',
    '変更内容の説明',
    CURRENT_USER
);

COMMIT;
```

## 注意事項

- DDLが途中で失敗した場合に履歴だけ登録されないよう、同一トランザクション内で実行する。
- PostgreSQLでトランザクション不可の操作を使う場合は、個別に注意事項を書く。
- 実行前に `management_app.sql_history` を確認する。
- 同じ `script_no` が登録済みの場合は実行しない。
- 実行後にテーブル、カラム、制約、データを確認する。
````

## File: backend/src/main/resources/sql/init/001_create_schema.sql
````sql
CREATE SCHEMA IF NOT EXISTS management_app AUTHORIZATION postgres;
````

## File: backend/src/main/resources/sql/init/002_create_projects_table.sql
````sql
CREATE TABLE IF NOT EXISTS management_app.projects (
    id BIGSERIAL PRIMARY KEY,
    project_key VARCHAR(20) NOT NULL UNIQUE,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
````

## File: backend/src/main/resources/sql/init/003_create_issues_table.sql
````sql
CREATE TABLE IF NOT EXISTS management_app.issues (
    id BIGSERIAL PRIMARY KEY,
    project_id BIGINT NOT NULL REFERENCES management_app.projects(id),
    title VARCHAR(200) NOT NULL,
    description TEXT,
    status VARCHAR(30) NOT NULL,
    priority VARCHAR(30) NOT NULL,
    assignee_name VARCHAR(100),
    due_date DATE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
````

## File: backend/src/main/resources/sql/init/004_create_indexes.sql
````sql
-- projects.project_key has a UNIQUE constraint in 002_create_projects_table.sql.
-- PostgreSQL creates an index for the UNIQUE constraint, so no duplicate index is created here.

CREATE INDEX IF NOT EXISTS idx_issues_project_id
    ON management_app.issues(project_id);

CREATE INDEX IF NOT EXISTS idx_issues_status
    ON management_app.issues(status);

CREATE INDEX IF NOT EXISTS idx_issues_updated_at
    ON management_app.issues(updated_at);
````

## File: backend/src/main/resources/sql/init/005_create_sql_history.sql
````sql
CREATE TABLE IF NOT EXISTS management_app.sql_history (
    script_no VARCHAR(10) PRIMARY KEY,
    script_name VARCHAR(255) NOT NULL,
    description VARCHAR(500),
    executed_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    executed_by VARCHAR(100)
);
````

## File: backend/src/main/resources/sql/init/006_insert_initial_data.sql
````sql
INSERT INTO management_app.projects (project_key, name, description, created_at, updated_at)
VALUES
    ('SAMPLE', 'Sample Project', 'Sample project for learning ProjectFlow.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('APP', 'App Development', 'Project for ProjectFlow application development.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (project_key) DO NOTHING;

INSERT INTO management_app.issues (
    project_id,
    title,
    description,
    status,
    priority,
    assignee_name,
    due_date,
    created_at,
    updated_at
)
SELECT
    p.id,
    seed.title,
    seed.description,
    seed.status,
    seed.priority,
    seed.assignee_name,
    seed.due_date,
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
FROM (
    VALUES
        ('SAMPLE', 'Check project list', 'Confirm that the sample project is shown in the list.', 'TODO', 'LOW', 'Sato', CURRENT_DATE + 3),
        ('SAMPLE', 'Check issue detail', 'Confirm that required fields are shown on the detail page.', 'IN_PROGRESS', 'MEDIUM', 'Tanaka', CURRENT_DATE + 7),
        ('APP', 'Implement backend API', 'Implement CRUD APIs with Spring Boot and MyBatis.', 'REVIEW', 'HIGH', 'Suzuki', CURRENT_DATE + 5),
        ('APP', 'Implement frontend pages', 'Call APIs from Vue 3 and support create, update, and delete operations.', 'IN_PROGRESS', 'HIGH', 'Takahashi', CURRENT_DATE + 10),
        ('APP', 'Update documents', 'Update design documents to match the MVP implementation.', 'DONE', 'MEDIUM', 'Ito', CURRENT_DATE + 1)
) AS seed(project_key, title, description, status, priority, assignee_name, due_date)
JOIN management_app.projects p
    ON p.project_key = seed.project_key
WHERE NOT EXISTS (
    SELECT 1
    FROM management_app.issues i
    WHERE i.project_id = p.id
      AND i.title = seed.title
);

SELECT setval(
    pg_get_serial_sequence('management_app.projects', 'id'),
    COALESCE((SELECT MAX(id) FROM management_app.projects), 1),
    true
);

SELECT setval(
    pg_get_serial_sequence('management_app.issues', 'id'),
    COALESCE((SELECT MAX(id) FROM management_app.issues), 1),
    true
);

INSERT INTO management_app.sql_history (
    script_no,
    script_name,
    description,
    executed_by
)
VALUES
    ('001', '001_create_schema.sql', 'Create management_app schema.', CURRENT_USER),
    ('002', '002_create_projects_table.sql', 'Create projects table.', CURRENT_USER),
    ('003', '003_create_issues_table.sql', 'Create issues table.', CURRENT_USER),
    ('004', '004_create_indexes.sql', 'Create indexes.', CURRENT_USER),
    ('005', '005_create_sql_history.sql', 'Create sql_history table.', CURRENT_USER),
    ('006', '006_insert_initial_data.sql', 'Insert initial sample data.', CURRENT_USER)
ON CONFLICT (script_no) DO NOTHING;
````

## File: backend/src/main/resources/sql/README.md
````markdown
# SQL管理ルール

ProjectFlowでは、Flyway / Liquibase / 独自Java migration runner は使用しません。
SQLは手動で番号順に実行し、適用履歴は `management_app.sql_history` で確認します。

## 初期構築用SQL

- 配置先: `backend/src/main/resources/sql/init`
- 採番範囲: `001`〜`099`
- 新規環境構築時のみ番号順に実行する。
- 既存環境へ再実行する場合は、内容と影響を確認する。
- `DROP TABLE` や既存データ削除を原則禁止する。

## 追加変更用SQL

- 配置先: `backend/src/main/resources/sql/changes`
- 採番範囲: `101`〜`999`
- ファイル名形式: `{3桁連番}_{変更内容を英語のsnake_case}.sql`
- 例:
  - `101_add_issue_priority_index.sql`
  - `102_create_issue_comments_table.sql`
  - `103_add_issue_status_history.sql`
- 最大番号の次を採番する。
- 一度使用した番号は再利用しない。
- 削除したSQLの番号は欠番のままにする。
- 1ファイルには原則1つの変更目的だけを記載する。
- 適用済みSQLを後から書き換えない。
- 修正が必要な場合は新しい番号のSQLを追加する。

## 初期構築の実行方法

リポジトリルートで、Windowsのコマンドプロンプトから以下を番号順に実行します。

```cmd
psql -h localhost -p 5432 -U postgres -d postgres -f backend\src\main\resources\sql\init\001_create_schema.sql
psql -h localhost -p 5432 -U postgres -d postgres -f backend\src\main\resources\sql\init\002_create_projects_table.sql
psql -h localhost -p 5432 -U postgres -d postgres -f backend\src\main\resources\sql\init\003_create_issues_table.sql
psql -h localhost -p 5432 -U postgres -d postgres -f backend\src\main\resources\sql\init\004_create_indexes.sql
psql -h localhost -p 5432 -U postgres -d postgres -f backend\src\main\resources\sql\init\005_create_sql_history.sql
psql -h localhost -p 5432 -U postgres -d postgres -f backend\src\main\resources\sql\init\006_insert_initial_data.sql
```

## 追加SQLの実行方法

例:

```cmd
psql -h localhost -p 5432 -U postgres -d postgres -f backend\src\main\resources\sql\changes\101_add_issue_priority_index.sql
```

## 適用履歴確認

```sql
SELECT
    script_no,
    script_name,
    description,
    executed_at,
    executed_by
FROM management_app.sql_history
ORDER BY script_no;
```
````

## File: backend/src/main/resources/application.yml
````yaml
spring:
  application:
    name: project-management-app
  profiles:
    default: local
  datasource:
    driver-class-name: org.postgresql.Driver
  sql:
    init:
      mode: never

server:
  port: 8080

mybatis:
  mapper-locations: classpath:mapper/*.xml
  type-aliases-package: com.projectmanagementapp.domain.model
  configuration:
    map-underscore-to-camel-case: true

projectmanagementapp:
  cors:
    allowed-origins:
      - http://localhost:5173
````

## File: backend/pom.xml
````xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.4.1</version>
        <relativePath/>
    </parent>

    <groupId>com.projectmanagementapp</groupId>
    <artifactId>project-management-app</artifactId>
    <version>0.1.0</version>
    <name>project-management-app</name>
    <description>ProjectFlow backend application</description>

    <properties>
        <java.version>21</java.version>
    </properties>

    <dependencies>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>3.0.4</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
        <dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>
````

## File: docs/01_システム仕様書.md
````markdown
# ProjectFlow システム仕様書

## 1. このドキュメントについて

このドキュメントは ProjectFlow のシステム仕様書です。

開発開始時の要件定義書ではなく、現在のシステム仕様・実装済み機能・今後の追加開発候補を管理するための運用ドキュメントです。

追加開発の詳細は GitHub Issue で管理し、このドキュメントには現在のシステム状態だけを反映してください。

Difyへ追加要件を依頼する際も、このドキュメントを入力してください。

画面、API、DBの構成は各概要資料を参照し、このファイルには人間とAIが全体像を理解するために必要な概要だけを記載します。

## 2. アプリ概要

- **アプリ名**: ProjectFlow
- **リポジトリ名**: project-management-app
- **目的**: プロジェクト、課題、ダッシュボードを中心にした学習用のプロジェクト管理Webアプリケーションを構築する。
- **背景**: 要件定義、設計、実装、レビュー、リリースに近い開発プロセスを経験できる疑似プロジェクトとして利用する。
- **方針**: Backlog や Jira のようなプロジェクト管理ツールを参考にするが、完全コピーではなく、ProjectFlow 独自の表現にする。
- **ゴール**: ローカル環境で `frontend` から `backend` のREST APIを呼び出し、プロジェクト管理、課題管理、ダッシュボードの基本操作を完了できる状態にする。

## 3. 対象ユーザー

- 小規模な開発チーム
- 学習者
- プロジェクト管理ツールの基本構造を学ぶ開発者
- 要件定義から実装、レビューまでの流れを練習したいエンジニア

MVPでは管理者専用機能は設けません。ローカル開発者がDB、アプリケーション設定、初期データを管理します。

## 4. 技術構成

| 分類 | 技術 |
|---|---|
| Frontend | Vue 3 + TypeScript + Vite + Vue Router |
| Backend | Java 21 + Spring Boot + Maven |
| DB | PostgreSQL |
| DBアクセス | MyBatis |
| SQL管理 | Mapper XML、`backend/src/main/resources/sql/init`、`backend/src/main/resources/sql/changes` |
| タスク管理 | GitHub Issues / GitHub Projects |
| AI支援 | Dify |

## 5. 開発方針

- 社内情報、顧客情報、個人情報、機密情報は含めない。
- MVPでは認証・認可を実装しない。
- REST API のパスは `/api` 配下に統一する。
- Spring Data JPA は使用せず、MyBatis を使用する。
- SQLはMapper interfaceへ直接書かず、Mapper XMLに記載する。
- DB初期構築SQLは `backend/src/main/resources/sql/init` を番号順に手動実行する。
- 追加変更SQLは `backend/src/main/resources/sql/changes` に採番して追加する。
- Flyway / Liquibase / 独自Java migration runner は導入しない。
- コード変更と関連docs更新は原則として同じPull Requestで行う。
- docs更新対象の判断は `docs/08_ドキュメント更新ルール.md` を参照する。

## 6. 現在実装済み機能

### Project管理

- 一覧
- 詳細
- 登録
- 編集
- 削除
- プロジェクトキーの重複チェック
- 課題が紐づくプロジェクトの削除防止

### Issue管理

- 一覧
- 詳細
- 登録
- 編集
- 削除
- プロジェクトによる絞り込み
- ステータスによる絞り込み
- 画面内キーワード検索（課題名、プロジェクト、担当者、ステータス、優先度）
- 課題一覧の表示切替（一覧、ボード、ガント風タイムライン）
- ステータス表示ラベル
- 優先度表示ラベル

### Dashboard

- Backend API接続状態表示
- プロジェクト数表示
- 課題数表示
- ステータス別件数表示
- 最近更新された課題表示
- ステータス別KPIカードの色分けとアイコン表示

### 共通機能

- ログイン画面
- Health API
- CORS設定
- Request DTOのBean Validationによる入力チェック
- 共通例外処理
- 統一形式のエラーレスポンス
- Vue画面からBackend REST APIを呼び出す構成

### 追加開発対応済み

GitHub Issueを通じて追加開発し、現在利用できる機能を記載します。
CHASSU が実装済みの追加機能を判断できるように、以下に対応したGitHub Issueの「概要」の先頭1文を記載してください。

-  

## 7. 現在の画面一覧

| 画面名 | パス | 概要 |
|---|---|---|
| ログイン | `/login` | メールアドレス、パスワード入力とログイン操作を提供する |
| ダッシュボード | `/dashboard` | 接続状態、件数サマリー、ステータス別KPI、最近更新された課題を表示する |
| プロジェクト一覧 | `/projects` | プロジェクト一覧、検索、編集、削除、新規作成導線を表示する |
| プロジェクト登録 | `/projects/new` | プロジェクトキー、名称、説明を登録する |
| プロジェクト編集 | `/projects/:id/edit` | 既存プロジェクトを編集する |
| 課題一覧 | `/issues` | 課題一覧、検索、絞り込み、一覧/ボード/ガント風表示切替、詳細、編集、削除、新規作成導線を表示する |
| 課題登録 | `/issues/new` | 課題を登録する |
| 課題詳細 | `/issues/:id` | 課題の詳細項目を表示する |
| 課題編集 | `/issues/:id/edit` | 既存課題を編集する |

`/` は `/login` へリダイレクトする。

現在の画面構成は概要資料として `docs/02_画面一覧.md` を参照してください。

## 8. API概要

現在提供しているAPI構成は概要資料として `docs/03_API一覧.md` を参照してください。

### Project

- GET `/api/projects`
- GET `/api/projects/{id}`
- POST `/api/projects`
- PUT `/api/projects/{id}`
- DELETE `/api/projects/{id}`

### Issue

- GET `/api/issues`
- GET `/api/issues/{id}`
- POST `/api/issues`
- PUT `/api/issues/{id}`
- DELETE `/api/issues/{id}`

### Dashboard

- GET `/api/dashboard`

### Health

- GET `/api/health`

## 9. DB概要

詳細なテーブル、カラム、制約、インデックスは `docs/04_DB設計.md` を参照してください。

### projects

- **用途**: プロジェクト管理
- **主な情報**: プロジェクトキー、名称、説明、作成日時、更新日時

### issues

- **用途**: 課題管理
- **主な情報**: 紐づくプロジェクト、件名、説明、ステータス、優先度、担当者、期限日、作成日時、更新日時

### 主な関係

- 1つのプロジェクトは複数の課題を持つ。
- 課題が存在するプロジェクトは削除できない。

## 10. 追加開発バックログ

この章では、今後の追加開発候補を簡潔に管理します。詳細な背景、対応内容、影響範囲はGitHub Issueで管理します。

- ログイン認証API連携
- 認可・権限管理
- ユーザー管理
- コメント機能
- タグ機能
- CSV出力
- 帳票出力
- API/DBと連動した本格的なカンバンボード
- API/DBと連動した本格的なガントチャート
- 通知
- 添付ファイル機能
- Wiki機能
- 課題変更履歴
- メール送信
- 外部サービス連携
---
````

## File: docs/02_画面一覧.md
````markdown
# 02 画面一覧

## 画面一覧

| 画面ID | 画面名 | パス | 実装状況 | 概要 |
| --- | --- | --- | --- | --- |
| SCR-000 | ログイン | `/login` | 実装済み | メールアドレス、パスワード入力とログイン操作を提供する |
| SCR-001 | ダッシュボード | `/dashboard` | 実装済み | 接続状態、件数サマリー、ステータス別KPI、最近更新された課題を表示 |
| SCR-002 | プロジェクト一覧 | `/projects` | 実装済み | プロジェクト一覧、検索、編集、削除、新規作成導線を表示 |
| SCR-003 | プロジェクト登録 | `/projects/new` | 実装済み | プロジェクトキー、名称、説明を登録 |
| SCR-004 | プロジェクト編集 | `/projects/:id/edit` | 実装済み | 既存プロジェクトを編集 |
| SCR-005 | 課題一覧 | `/issues` | 実装済み | 課題一覧、検索、フィルター、表示切替、詳細、編集、削除、新規作成導線を表示 |
| SCR-006 | 課題登録 | `/issues/new` | 実装済み | 課題を登録 |
| SCR-007 | 課題詳細 | `/issues/:id` | 実装済み | 課題の詳細項目を表示 |
| SCR-008 | 課題編集 | `/issues/:id/edit` | 実装済み | 既存課題を編集 |

---

## 運用方針

本ドキュメントは、ProjectFlowに存在する画面の一覧を管理します。

各画面の詳細仕様は管理しません。

画面追加・画面変更時は一覧表のみ更新してください。

詳細な画面仕様、入力項目、受入条件、画面遷移、UI仕様はGitHub Issueで管理します。

実装内容の正はソースコードです。
````

## File: docs/03_API一覧.md
````markdown
# 03 API一覧

## API一覧

| API ID | Method | Path | 概要 | 実装状況 |
| --- | --- | --- | --- | --- |
| API-001 | GET | `/api/health` | ヘルスチェック | 実装済み |
| API-002 | GET | `/api/dashboard` | ダッシュボード集計取得 | 実装済み |
| API-003 | GET | `/api/projects` | プロジェクト一覧取得 | 実装済み |
| API-004 | GET | `/api/projects/{id}` | プロジェクト詳細取得 | 実装済み |
| API-005 | POST | `/api/projects` | プロジェクト登録 | 実装済み |
| API-006 | PUT | `/api/projects/{id}` | プロジェクト更新 | 実装済み |
| API-007 | DELETE | `/api/projects/{id}` | プロジェクト削除 | 実装済み |
| API-008 | GET | `/api/issues` | 課題一覧取得 | 実装済み |
| API-009 | GET | `/api/issues/{id}` | 課題詳細取得 | 実装済み |
| API-010 | POST | `/api/issues` | 課題登録 | 実装済み |
| API-011 | PUT | `/api/issues/{id}` | 課題更新 | 実装済み |
| API-012 | DELETE | `/api/issues/{id}` | 課題削除 | 実装済み |

---

## 運用方針

本ドキュメントは、ProjectFlowで提供するAPIの一覧を管理します。

各APIの詳細仕様は管理しません。

API追加・変更時は一覧表のみ更新してください。

詳細なRequest、Response、エラー仕様はGitHub Issueとソースコードを正とします。
````

## File: docs/04_DB設計.md
````markdown
# 04 DB設計

## 概要

DBスキーマは `management_app` とする。

初期構築SQLは `backend/src/main/resources/sql/init`、追加変更SQLは `backend/src/main/resources/sql/changes` で管理する。
SQLはSpring Boot起動時に自動実行せず、環境構築手順に従って手動実行する。

## projects

| カラム | 型 | 制約 | 説明 |
| --- | --- | --- | --- |
| id | BIGSERIAL | PRIMARY KEY | プロジェクトID |
| project_key | VARCHAR(20) | NOT NULL, UNIQUE | プロジェクトキー |
| name | VARCHAR(100) | NOT NULL | プロジェクト名 |
| description | TEXT | NULL | 説明 |
| created_at | TIMESTAMP | NOT NULL | 作成日時 |
| updated_at | TIMESTAMP | NOT NULL | 更新日時 |

## issues

| カラム | 型 | 制約 | 説明 |
| --- | --- | --- | --- |
| id | BIGSERIAL | PRIMARY KEY | 課題ID |
| project_id | BIGINT | NOT NULL, FOREIGN KEY | プロジェクトID |
| title | VARCHAR(200) | NOT NULL | 件名 |
| description | TEXT | NULL | 説明 |
| status | VARCHAR(30) | NOT NULL | ステータス |
| priority | VARCHAR(30) | NOT NULL | 優先度 |
| assignee_name | VARCHAR(100) | NULL | 担当者名 |
| due_date | DATE | NULL | 期限日 |
| created_at | TIMESTAMP | NOT NULL | 作成日時 |
| updated_at | TIMESTAMP | NOT NULL | 更新日時 |

### status の値

| 値 | 表示ラベル | 説明 |
| --- | --- | --- |
| `TODO` | 未着手 | まだ着手していない |
| `IN_PROGRESS` | 対応中 | 作業中 |
| `REVIEW` | レビュー中 | 確認待ち |
| `DONE` | 完了 | 完了済み |

### priority の値

| 値 | 表示ラベル | 説明 |
| --- | --- | --- |
| `LOW` | 低 | 低優先度 |
| `MEDIUM` | 中 | 標準優先度 |
| `HIGH` | 高 | 高優先度 |

## インデックス

- `projects.project_key`: UNIQUE制約によりPostgreSQLが自動作成するため、明示的な重複インデックスは作成しない
- `issues.project_id`
- `issues.status`
- `issues.updated_at`

## sql_history

手動実行した初期構築SQLと追加変更SQLの適用履歴を確認するための簡易テーブル。
FlywayやLiquibaseの代替として自動実行するものではない。

| カラム | 型 | 制約 | 説明 |
| --- | --- | --- | --- |
| script_no | VARCHAR(10) | PRIMARY KEY | SQLの採番 |
| script_name | VARCHAR(255) | NOT NULL | SQLファイル名 |
| description | VARCHAR(500) | NULL | 変更内容 |
| executed_at | TIMESTAMP | NOT NULL, DEFAULT CURRENT_TIMESTAMP | 実行日時 |
| executed_by | VARCHAR(100) | NULL | 実行ユーザー |

## SQL配置場所

| 種別 | 配置場所 | 採番範囲 | 用途 |
| --- | --- | --- | --- |
| 初期構築用SQL | `backend/src/main/resources/sql/init` | `001`〜`099` | 新規環境の初期構築 |
| 追加変更用SQL | `backend/src/main/resources/sql/changes` | `101`〜`999` | テーブル追加、カラム追加、インデックス追加などの継続的なDB変更 |

## SQL採番ルール

- 追加変更SQLは `{3桁連番}_{変更内容を英語のsnake_case}.sql` で作成する。
- 最大番号の次を採番する。
- 一度使用した番号は再利用しない。
- 削除したSQLの番号は欠番のままにする。
- 1ファイルには原則1つの変更目的だけを記載する。
- 適用済みSQLは後から修正しない。
- 修正が必要な場合は、新しい番号のSQLを `changes` 配下へ追加する。
- `DROP TABLE` や既存データ削除を伴うSQLは原則禁止する。

## 削除制約

課題が存在するプロジェクトは削除できない。APIは 400 エラーを返す。
````

## File: docs/05_開発ルール.md
````markdown
# 05 開発ルール

## Git運用

ProjectFlowでは以下のブランチ構成を採用する。

### main

- 安定版ブランチ。
- 動作確認済みのコードのみマージする。

### develop

- 開発ブランチ。
- featureブランチ、bugfixブランチのマージ先とする。

### feature/*

- 機能開発用ブランチ。

### bugfix/*

- 不具合修正用ブランチ。

## ブランチ作成

- featureブランチ、bugfixブランチは必ずdevelopブランチから作成する。
- mainからfeatureブランチ、bugfixブランチを直接作成しない。

## ブランチ命名規則

- 機能開発: `feature/issue-{Issue番号}-{機能名}`
- 不具合修正: `bugfix/{Issue番号}-{概要}`
- 機能名、概要は英小文字のkebab-caseで命名する。

例:

- `feature/issue-12-login`
- `feature/issue-25-project-search`
- `bugfix/issue-31-null-pointer`

## 開発フロー

```text
Issue作成
        ↓
developからfeature/bugfixブランチを作成
        ↓
実装
        ↓
Pull Request作成
        ↓
レビュー
        ↓
feature/bugfix → developへマージ
        ↓
developで動作確認
        ↓
問題がなければdevelop → mainへマージ
```

## 注意事項

- mainへ直接コミット・直接マージは禁止する。
- Pull Requestを経由する。
- レビュー完了後にdevelopへマージする。
- mainへのマージはdevelopで動作確認後に実施する。

## backend

- Controller は Service interface のみを呼び出す。
- Service は interface と Impl を分ける。
- ServiceImpl は業務ルール、存在チェック、レスポンス変換を担当する。
- Dao は interface と Impl を分ける。
- DaoImpl は Mapper interface の呼び出しだけを担当する。
- Spring Data JPA は使用せず、MyBatis を使用する。
- Mapper interface にSQLを直接書かない。
- SQLは `src/main/resources/mapper/*Mapper.xml` に書く。
- DB初期構築SQLは `src/main/resources/sql/init`、追加変更SQLは `src/main/resources/sql/changes` で管理する。
- Flyway / Liquibase / 独自Java migration runner は導入しない。
- DB変更時は適用済みSQLを修正せず、新しい採番SQLを追加する。
- REST API のパスは `/api` 配下に統一する。
- DTO と Model を分ける。
- リクエスト、レスポンスではDTOを使用する。
- 画面表示用ラベルは backend で返す。
- 共通例外処理は `GlobalExceptionHandler` に集約する。
- 入力チェックは request DTO の Bean Validation で行う。

## frontend

- API呼び出しは `src/api` に集約する。
- APIレスポンス型は `src/types` に定義する。
- 画面は `src/views` に配置する。
- 画面はモック固定値ではなくAPIレスポンスを表示する。
- 業務ツールとして、一覧・フォーム・詳細を読みやすく保つ。

## docs

- 実装変更に合わせて、影響するdocsを更新する。
- docs更新対象の判断は `docs/08_ドキュメント更新ルール.md` を参照する。
- 更新不要の場合は、IssueまたはPull Requestに理由を記載する。
- 社内情報、顧客情報、個人情報を含めない。
````

## File: docs/06_レビュー観点.md
````markdown
# 06 レビュー観点

## 1. アーキテクチャ・レイヤー分離

以下を確認する。

- Controller に業務ロジックが書かれていないか。
- Controller が Dao / Mapper を直接呼んでいないか。
- Service に業務ルールが集約されているか。
- Service interface と ServiceImpl の責務が明確か。
- Dao interface と DaoImpl の責務が明確か。
- Mapper はDBアクセスのみに責務が限定されているか。
- DTO / Model / Request / Response の使い分けができているか。
- DBの都合がControllerや画面向けDTOに漏れすぎていないか。
- 1つのクラス・メソッドに責務が集中しすぎていないか。
- 将来的な追加機能に耐えられる構成になっているか。

## 2. Controller レビュー観点

以下を確認する。

- REST API のURL設計が自然か。
- HTTPメソッドの使い方が適切か。
- `@RequestBody`, `@PathVariable`, `@RequestParam` の使い分けが適切か。
- 入力チェックに `@Valid` が使われているか。
- Controller 内で業務判断やDBアクセスをしていないか。
- 正常系のHTTPステータスが適切か。
- 作成時は `201 Created` を返しているか。
- 削除時は `204 No Content` を返しているか。
- エラー時のレスポンス形式が統一されているか。
- API利用者にとって分かりやすいレスポンスになっているか。

## 3. Service レビュー観点

以下を確認する。

- 業務ルールがServiceに集約されているか。
- `projectKey` 重複チェックなどの業務制約が適切に実装されているか。
- 存在チェックが適切に行われているか。
- 404 と 400 の使い分けが妥当か。
- 更新処理で既存データの存在確認をしているか。
- 削除不可条件が正しく実装されているか。
- トランザクション境界が適切か。
- 更新系処理に `@Transactional` が付いているか。
- 参照系処理に `@Transactional(readOnly = true)` を付ける余地がないか。
- Service が肥大化していないか。
- DTO変換処理が散らばりすぎていないか。
- `null` や空文字の扱いが不自然でないか。

## 4. Dao / Mapper レビュー観点

以下を確認する。

- Dao がMapper呼び出しの窓口として機能しているか。
- Service から Mapper を直接呼んでいないか。
- Mapper interface にSQLを直接書いていないか。
- SQL は XML に分離されているか。
- XML の `namespace` が Mapper interface と一致しているか。
- `resultMap` が適切に定義されているか。
- カラム名と Java プロパティ名のマッピングが正しいか。
- 動的SQLの条件分岐が正しく書かれているか。
- 検索条件が未指定の場合の挙動が明確か。
- N+1問題を起こすようなSQLになっていないか。
- 不要に複数回DBアクセスしていないか。
- 更新日時順など、並び順が明示されているか。
- 件数取得SQLが正しくステータス別に集計できているか。

## 5. SQL / DB設計レビュー観点

以下を確認する。

- テーブル名・カラム名が分かりやすいか。
- 主キー・外部キー・一意制約が適切か。
- `project_key` の `UNIQUE` 制約があるか。
- `issues.project_id` に外部キー制約があるか。
- `NOT NULL` 制約が業務仕様と一致しているか。
- `created_at`, `updated_at` が適切に扱われているか。
- ステータス・優先度の値がアプリ側Enumと整合しているか。
- インデックスが検索条件に対して妥当か。
- プロジェクト削除時の制約が仕様通りか。
- 初期データが画面確認しやすい内容になっているか。
- SQLがPostgreSQL向けとして正しく動作するか。
- スキーマ `management_app` 前提と接続設定が矛盾していないか。

## 6. DTO / Validation レビュー観点

以下を確認する。

- Request DTO と Response DTO が分かれているか。
- Model を直接 API レスポンスに返していないか。
- 入力必須項目に適切なバリデーションがあるか。
- 文字数制限がDB定義と一致しているか。
- `projectKey` の形式チェックがあるか。
- Enum項目の不正値に対するエラーが適切か。
- 日付項目の扱いが適切か。
- 画面表示に必要なラベルが Response に含まれているか。
- 不要な内部項目を API レスポンスに含めていないか。

## 7. 例外・エラーハンドリング

以下を確認する。

- 共通例外ハンドラがあるか。
- `ResourceNotFoundException` が適切に 404 へ変換されているか。
- `BusinessException` が適切に 400 へ変換されているか。
- Validation エラーが分かりやすい形式で返るか。
- 予期しない例外のレスポンスが統一されているか。
- スタックトレースや内部情報をレスポンスに出していないか。
- エラーメッセージが利用者に分かりやすいか。
- ログ出力が必要な箇所にあるか。
- 例外を握りつぶしていないか。

## 8. トランザクション・整合性

以下を確認する。

- 登録・更新・削除処理にトランザクションが設定されているか。
- 複数DB操作がある処理で整合性が保たれるか。
- 途中で例外が発生した場合にロールバックされるか。
- 削除前チェックと削除処理の整合性に問題がないか。
- 同時更新時に大きな問題が起きないか。
- 現時点で楽観ロックが不要か、将来追加候補として残すべきか。

## 9. セキュリティ・公開リポジトリ観点

以下を確認する。

- 社内情報・顧客情報・個人情報が含まれていないか。
- DBパスワードなどの秘密情報がコミットされていないか。
- `application.yml` に直接機密値を書いていないか。
- 環境変数で上書き可能な構成になっているか。
- CORS許可範囲が広すぎないか。
- SQLインジェクションの危険がないか。
- MyBatis の `${}` を安易に使っていないか。
- ユーザー入力をログに出しすぎていないか。
- エラー時に内部実装情報が漏れていないか。

## 10. Javaコード品質

以下を確認する。

- クラス名・メソッド名・変数名が役割を表しているか。
- メソッドが長すぎないか。
- 条件分岐が複雑すぎないか。
- 重複コードがないか。
- Optional の使い方が適切か。
- null チェックが必要な箇所で行われているか。
- 定数化すべき文字列がベタ書きされていないか。
- Enumを使うべき箇所で文字列比較していないか。
- Stream API を無理に使って読みにくくしていないか。
- コメントが不足しすぎていないか。
- 逆に、コードを読めば分かるコメントが多すぎないか。
- 将来の保守者が読みやすい実装になっているか。

## 11. API設計・フロント連携観点

以下を確認する。

- frontend が使いやすいレスポンス形式になっているか。
- 課題一覧で `projectKey` / `projectName` など必要な情報が返っているか。
- `statusLabel` / `priorityLabel` の扱いが一貫しているか。
- 一覧・詳細・登録・更新・削除のAPI粒度が妥当か。
- 絞り込み条件 `projectId` / `status` が正しく使えるか。
- 日時フォーマットが frontend で扱いやすいか。
- 空データ時のレスポンスが自然か。
- 削除失敗時のエラーが画面に表示しやすいか。

## 12. テスト観点

以下を確認する。

- Service の単体テストが書きやすい構成か。
- Controller のテストが書きやすい構成か。
- 正常系だけでなく異常系もテストできるか。
- `projectKey` 重複時のテストがあるか。
- 存在しない `projectId` の課題登録テストがあるか。
- 課題が紐づくプロジェクト削除不可のテストがあるか。
- `status` / `priority` の不正値テストがあるか。
- Dashboard の件数集計テストができるか。
- Mapper XML のSQLが実DBで確認できるか。
- 今回テスト未実装の場合、今後追加すべきテスト観点が明記されているか。

## 13. ドキュメント整合性

以下を確認する。

- 実装内容と `docs/01_システム仕様書.md` が一致しているか。
- API一覧と実装済みAPIの構成が一致しているか。
- DB定義と `docs/04_DB設計.md` が一致しているか。
- コーディング規約と実装構成が一致しているか。
- 環境構築手順と実装上の起動方法が矛盾していないか。
- ER図は不要方針のため、ER図に依存した記述が増えていないか。
- READMEの起動手順が実際に使える内容か。
- 今後の追加機能候補が分かりやすく整理されているか。
````

## File: frontend/src/api/dashboardApi.ts
````typescript
import { request } from "./httpClient";
import type { Dashboard } from "../types/dashboard";

export const dashboardApi = {
  get: () => request<Dashboard>("/dashboard")
};
````

## File: frontend/src/api/healthApi.ts
````typescript
import { request } from "./httpClient";

export const healthApi = {
  get: () => request<{ status: string }>("/health")
};
````

## File: frontend/src/api/httpClient.ts
````typescript
import type { ApiError } from "../types/error";

const baseURL = "http://localhost:8080/api";

export async function request<T>(path: string, options: RequestInit = {}): Promise<T> {
  const response = await fetch(`${baseURL}${path}`, {
    headers: {
      "Content-Type": "application/json",
      ...options.headers
    },
    ...options
  });

  if (!response.ok) {
    let error: ApiError = { message: "APIリクエストに失敗しました。", details: [] };
    try {
      error = await response.json();
    } catch {
      error.message = `${response.status} ${response.statusText}`;
    }
    throw error;
  }

  if (response.status === 204) {
    return undefined as T;
  }
  return response.json();
}
````

## File: frontend/src/api/issueApi.ts
````typescript
import { request } from "./httpClient";
import type { Issue, IssueRequest, IssueStatus } from "../types/issue";

export const issueApi = {
  findAll: (params: { projectId?: number; status?: IssueStatus } = {}) => {
    const query = new URLSearchParams();
    if (params.projectId) query.set("projectId", String(params.projectId));
    if (params.status) query.set("status", params.status);
    const suffix = query.toString() ? `?${query}` : "";
    return request<Issue[]>(`/issues${suffix}`);
  },
  findById: (id: number) => request<Issue>(`/issues/${id}`),
  create: (body: IssueRequest) => request<Issue>("/issues", { method: "POST", body: JSON.stringify(body) }),
  update: (id: number, body: IssueRequest) => request<Issue>(`/issues/${id}`, { method: "PUT", body: JSON.stringify(body) }),
  delete: (id: number) => request<void>(`/issues/${id}`, { method: "DELETE" })
};
````

## File: frontend/src/api/projectApi.ts
````typescript
import { request } from "./httpClient";
import type { Project, ProjectRequest } from "../types/project";

export const projectApi = {
  findAll: () => request<Project[]>("/projects"),
  findById: (id: number) => request<Project>(`/projects/${id}`),
  create: (body: ProjectRequest) => request<Project>("/projects", { method: "POST", body: JSON.stringify(body) }),
  update: (id: number, body: ProjectRequest) => request<Project>(`/projects/${id}`, { method: "PUT", body: JSON.stringify(body) }),
  delete: (id: number) => request<void>(`/projects/${id}`, { method: "DELETE" })
};
````

## File: frontend/src/components/PageSection.vue
````vue
<template>
  <section class="page-section">
    <div class="section-heading">
      <p v-if="kicker" class="section-kicker">{{ kicker }}</p>
      <h2>{{ title }}</h2>
      <p v-if="description" class="section-description">{{ description }}</p>
    </div>
    <div class="section-body">
      <slot />
    </div>
  </section>
</template>

<script setup lang="ts">
defineProps<{
  title: string;
  kicker?: string;
  description?: string;
}>();
</script>
````

## File: frontend/src/router/index.ts
````typescript
import { createRouter, createWebHistory } from "vue-router";
import LoginView from "../views/Login/LoginView.vue";
import DashboardView from "../views/Dashboard/DashboardView.vue";
import ProjectListView from "../views/ProjectList/ProjectListView.vue";
import ProjectFormView from "../views/ProjectForm/ProjectFormView.vue";
import IssueListView from "../views/IssueList/IssueListView.vue";
import IssueFormView from "../views/IssueForm/IssueFormView.vue";
import IssueDetailView from "../views/IssueDetail/IssueDetailView.vue";

const router = createRouter({
  history: createWebHistory(),
  routes: [
    { path: "/", redirect: "/login" },
    { path: "/login", name: "login", component: LoginView, meta: { publicLayout: true } },
    { path: "/dashboard", name: "dashboard", component: DashboardView },
    { path: "/projects", name: "projects", component: ProjectListView },
    { path: "/projects/new", name: "project-new", component: ProjectFormView },
    { path: "/projects/:id/edit", name: "project-edit", component: ProjectFormView, props: true },
    { path: "/issues", name: "issues", component: IssueListView },
    { path: "/issues/new", name: "issue-new", component: IssueFormView },
    { path: "/issues/:id", name: "issue-detail", component: IssueDetailView, props: true },
    { path: "/issues/:id/edit", name: "issue-edit", component: IssueFormView, props: true }
  ]
});

export default router;
````

## File: frontend/src/types/dashboard.ts
````typescript
import type { Issue } from "./issue";

export interface Dashboard {
  projectCount: number;
  issueCount: number;
  todoCount: number;
  inProgressCount: number;
  reviewCount: number;
  doneCount: number;
  recentIssues: Issue[];
}
````

## File: frontend/src/types/error.ts
````typescript
export interface ApiError {
  message: string;
  details: string[];
}
````

## File: frontend/src/types/issue.ts
````typescript
export type IssueStatus = "TODO" | "IN_PROGRESS" | "REVIEW" | "DONE";
export type IssuePriority = "LOW" | "MEDIUM" | "HIGH";

export interface Issue {
  id: number;
  projectId: number;
  projectKey: string;
  projectName: string;
  title: string;
  description: string | null;
  status: IssueStatus;
  statusLabel: string;
  priority: IssuePriority;
  priorityLabel: string;
  assigneeName: string | null;
  dueDate: string | null;
  createdAt: string;
  updatedAt: string;
}

export interface IssueRequest {
  projectId: number;
  title: string;
  description: string;
  status: IssueStatus;
  priority: IssuePriority;
  assigneeName: string;
  dueDate: string | null;
}
````

## File: frontend/src/types/project.ts
````typescript
export interface Project {
  id: number;
  projectKey: string;
  name: string;
  description: string | null;
  createdAt: string;
  updatedAt: string;
}

export interface ProjectRequest {
  projectKey: string;
  name: string;
  description: string;
}
````

## File: frontend/src/views/Dashboard/DashboardView.vue
````vue
<template>
  <PageSection title="ダッシュボード" kicker="Dashboard" description="プロジェクト全体の状態と直近の更新を確認します。">
    <p v-if="error" class="alert">{{ error }}</p>

    <div class="summary-bar">
      <div>
        <p class="section-kicker">Workspace status</p>
        <div class="summary-meta">
          <span class="health-row">
            <span class="status-dot" :class="{ ok: health === 'UP' }"></span>
            API {{ health || "確認中" }}
          </span>
          <span>{{ dashboard?.issueCount ?? "-" }} 件の課題</span>
          <span>{{ dashboard?.projectCount ?? "-" }} 件のプロジェクト</span>
        </div>
      </div>
      <div class="actions">
        <RouterLink class="button secondary" to="/projects">プロジェクト一覧</RouterLink>
        <RouterLink class="button" to="/issues/new">課題を作成</RouterLink>
      </div>
    </div>

    <div class="stats-grid dashboard-stats">
      <article class="stat-card stat-project">
        <span class="stat-icon project-stat-icon" aria-hidden="true">
          <img :src="projectDashboardIcon" alt="" />
        </span>
        <div class="stat-copy">
          <span class="stat-label">プロジェクト</span>
          <strong>{{ dashboard?.projectCount ?? "-" }}</strong>
          <small>管理中のワークスペース</small>
        </div>
      </article>
      <article class="stat-card stat-issue">
        <span class="stat-icon issue-stat-icon" aria-hidden="true">
          <img :src="issueDashboardIcon" alt="" />
        </span>
        <div class="stat-copy">
          <span class="stat-label">課題</span>
          <strong>{{ dashboard?.issueCount ?? "-" }}</strong>
          <small>登録されている課題</small>
        </div>
      </article>
      <article class="stat-card stat-todo">
        <span class="stat-icon todo-stat-icon" aria-hidden="true">
          <img :src="todoDashboardIcon" alt="" />
        </span>
        <div class="stat-copy">
          <span class="stat-label">未着手</span>
          <strong>{{ dashboard?.todoCount ?? "-" }}</strong>
          <small>着手待ち</small>
        </div>
      </article>
      <article class="stat-card stat-progress">
        <span class="stat-icon progress-stat-icon" aria-hidden="true">
          <img :src="progressDashboardIcon" alt="" />
        </span>
        <div class="stat-copy">
          <span class="stat-label">対応中</span>
          <strong>{{ dashboard?.inProgressCount ?? "-" }}</strong>
          <small>進行中の作業</small>
        </div>
      </article>
      <article class="stat-card stat-review">
        <span class="stat-icon review-stat-icon" aria-hidden="true">
          <img :src="reviewDashboardIcon" alt="" />
        </span>
        <div class="stat-copy">
          <span class="stat-label">レビュー中</span>
          <strong>{{ dashboard?.reviewCount ?? "-" }}</strong>
          <small>確認待ち</small>
        </div>
      </article>
      <article class="stat-card stat-done">
        <span class="stat-icon complete-stat-icon" aria-hidden="true">
          <img :src="completeDashboardIcon" alt="" />
        </span>
        <div class="stat-copy">
          <span class="stat-label">完了</span>
          <strong>{{ dashboard?.doneCount ?? "-" }}</strong>
          <small>クローズ済み</small>
        </div>
      </article>
    </div>

    <div class="page-tabs">
      <button class="tab-button active" type="button">最近の更新</button>
      <button class="tab-button" type="button">担当中</button>
      <button class="tab-button" type="button">期限あり</button>
    </div>

    <section class="panel">
      <div class="section-line">
        <div>
          <p class="section-kicker">Recent activity</p>
          <h3>最近更新された課題</h3>
        </div>
        <RouterLink class="button secondary" to="/issues">一覧を開く</RouterLink>
      </div>
      <div class="table-card">
        <table>
          <thead>
            <tr>
              <th>プロジェクト</th>
              <th>件名</th>
              <th>ステータス</th>
              <th>更新日時</th>
            </tr>
          </thead>
          <tbody>
            <tr
              v-for="issue in dashboard?.recentIssues ?? []"
              :key="issue.id"
              :class="['status-line', statusClass(issue.status)]"
              @click="router.push(`/issues/${issue.id}`)"
            >
              <td>{{ issue.projectKey }}</td>
              <td>
                <div class="row-title">
                  <span class="text-link">{{ issue.title }}</span>
                  <small>#{{ issue.id }}</small>
                </div>
              </td>
              <td><span :class="['badge', statusClass(issue.status)]">{{ issue.statusLabel }}</span></td>
              <td>{{ formatDate(issue.updatedAt) }}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </section>
  </PageSection>
</template>

<script setup lang="ts">
import { onMounted, ref } from "vue";
import { RouterLink, useRouter } from "vue-router";
import PageSection from "../../components/PageSection.vue";
import { dashboardApi } from "../../api/dashboardApi";
import { healthApi } from "../../api/healthApi";
import completeDashboardIcon from "../../assets/complete-dashboard-icon.png";
import issueDashboardIcon from "../../assets/issue-dashboard-icon.svg";
import progressDashboardIcon from "../../assets/progress-dashboard-icon.png";
import projectDashboardIcon from "../../assets/project-dashboard-icon.png";
import reviewDashboardIcon from "../../assets/review-dashboard-icon.png";
import todoDashboardIcon from "../../assets/todo-dashboard-icon.png";
import type { Dashboard } from "../../types/dashboard";
import type { IssueStatus } from "../../types/issue";

const router = useRouter();
const dashboard = ref<Dashboard>();
const health = ref("");
const error = ref("");

function formatDate(value: string) {
  return new Date(value).toLocaleString("ja-JP");
}

function statusClass(status: IssueStatus) {
  return {
    TODO: "status-todo",
    IN_PROGRESS: "status-progress",
    REVIEW: "status-review",
    DONE: "status-done"
  }[status];
}

onMounted(async () => {
  try {
    const [healthResponse, dashboardResponse] = await Promise.all([healthApi.get(), dashboardApi.get()]);
    health.value = healthResponse.status;
    dashboard.value = dashboardResponse;
  } catch (e) {
    error.value = e instanceof Error ? e.message : "データの取得に失敗しました。";
  }
});
</script>
````

## File: frontend/src/views/IssueDetail/IssueDetailView.vue
````vue
<template>
  <PageSection title="課題詳細" kicker="Issue Detail">
    <p v-if="error" class="alert">{{ error }}</p>
    <article v-if="issue" class="detail-card">
      <div class="section-line">
        <div>
          <p class="section-kicker">#{{ issue.id }} / {{ issue.projectKey }}</p>
          <h3>{{ issue.title }}</h3>
        </div>
        <RouterLink class="button" :to="`/issues/${issue.id}/edit`">編集</RouterLink>
      </div>
      <dl class="detail-grid">
        <div><dt>プロジェクト名</dt><dd>{{ issue.projectName }}</dd></div>
        <div><dt>プロジェクトキー</dt><dd>{{ issue.projectKey }}</dd></div>
        <div><dt>ステータス</dt><dd>{{ issue.statusLabel }}</dd></div>
        <div><dt>優先度</dt><dd>{{ issue.priorityLabel }}</dd></div>
        <div><dt>担当者名</dt><dd>{{ issue.assigneeName }}</dd></div>
        <div><dt>期限日</dt><dd>{{ issue.dueDate }}</dd></div>
        <div><dt>作成日時</dt><dd>{{ formatDate(issue.createdAt) }}</dd></div>
        <div><dt>更新日時</dt><dd>{{ formatDate(issue.updatedAt) }}</dd></div>
      </dl>
      <p class="detail-text">{{ issue.description }}</p>
    </article>
  </PageSection>
</template>

<script setup lang="ts">
import { onMounted, ref } from "vue";
import { RouterLink } from "vue-router";
import PageSection from "../../components/PageSection.vue";
import { issueApi } from "../../api/issueApi";
import type { Issue } from "../../types/issue";

const props = defineProps<{ id: string }>();
const issue = ref<Issue>();
const error = ref("");

function formatDate(value: string) {
  return new Date(value).toLocaleString("ja-JP");
}

onMounted(async () => {
  try {
    issue.value = await issueApi.findById(Number(props.id));
  } catch (e: any) {
    error.value = e.message ?? "課題の取得に失敗しました。";
  }
});
</script>
````

## File: frontend/src/views/IssueForm/IssueFormView.vue
````vue
<template>
  <PageSection :title="isEdit ? '課題編集' : '課題登録'" kicker="Issue Form">
    <form class="form-panel" @submit.prevent="save">
      <p v-if="error" class="alert">{{ error }}</p>
      <label>
        プロジェクト
        <select v-model.number="form.projectId" required>
          <option disabled :value="0">選択してください</option>
          <option v-for="project in projects" :key="project.id" :value="project.id">{{ project.projectKey }} / {{ project.name }}</option>
        </select>
      </label>
      <label>
        件名
        <input v-model.trim="form.title" required maxlength="200" />
      </label>
      <label>
        説明
        <textarea v-model.trim="form.description" maxlength="5000" rows="6"></textarea>
      </label>
      <div class="form-grid">
        <label>
          ステータス
          <select v-model="form.status">
            <option value="TODO">未着手</option>
            <option value="IN_PROGRESS">対応中</option>
            <option value="REVIEW">レビュー中</option>
            <option value="DONE">完了</option>
          </select>
        </label>
        <label>
          優先度
          <select v-model="form.priority">
            <option value="LOW">低</option>
            <option value="MEDIUM">中</option>
            <option value="HIGH">高</option>
          </select>
        </label>
      </div>
      <div class="form-grid">
        <label>
          担当者名
          <input v-model.trim="form.assigneeName" maxlength="100" />
        </label>
        <label>
          期限日
          <input v-model="form.dueDate" type="date" />
        </label>
      </div>
      <div class="actions">
        <button class="button" type="submit">保存</button>
        <RouterLink class="button secondary" to="/issues">戻る</RouterLink>
      </div>
    </form>
  </PageSection>
</template>

<script setup lang="ts">
import { computed, onMounted, reactive, ref } from "vue";
import { RouterLink, useRouter } from "vue-router";
import PageSection from "../../components/PageSection.vue";
import { issueApi } from "../../api/issueApi";
import { projectApi } from "../../api/projectApi";
import type { IssueRequest } from "../../types/issue";
import type { Project } from "../../types/project";

const props = defineProps<{ id?: string }>();
const router = useRouter();
const isEdit = computed(() => Boolean(props.id));
const projects = ref<Project[]>([]);
const error = ref("");
const form = reactive<IssueRequest>({
  projectId: 0,
  title: "",
  description: "",
  status: "TODO",
  priority: "MEDIUM",
  assigneeName: "",
  dueDate: null
});

onMounted(async () => {
  projects.value = await projectApi.findAll();
  if (!props.id) return;
  const issue = await issueApi.findById(Number(props.id));
  form.projectId = issue.projectId;
  form.title = issue.title;
  form.description = issue.description ?? "";
  form.status = issue.status;
  form.priority = issue.priority;
  form.assigneeName = issue.assigneeName ?? "";
  form.dueDate = issue.dueDate;
});

async function save() {
  try {
    const body = { ...form, dueDate: form.dueDate || null };
    if (props.id) {
      await issueApi.update(Number(props.id), body);
    } else {
      await issueApi.create(body);
    }
    await router.push("/issues");
  } catch (e: any) {
    error.value = e.message ?? "保存に失敗しました。";
  }
}
</script>
````

## File: frontend/src/views/IssueList/IssueListView.vue
````vue
<template>
  <PageSection
    title="課題一覧"
    kicker="Issues"
    description="プロジェクトやステータスで絞り込みながら、課題の進捗を確認します。"
  >
    <p v-if="error" class="alert">{{ error }}</p>

    <div class="summary-bar">
      <div>
        <p class="section-kicker">Current view</p>
        <div class="summary-meta">
          <span>{{ filteredIssues.length }} 件を表示</span>
          <span>{{ issues.length }} 件中</span>
          <span>未完了 {{ openIssueCount }} 件</span>
        </div>
      </div>
      <div class="actions">
        <button class="button secondary" type="button" @click="resetFilters">リセット</button>
        <RouterLink class="button" to="/issues/new">新規作成</RouterLink>
      </div>
    </div>

    <div class="page-tabs">
      <button class="tab-button active" type="button">課題</button>
      <button class="tab-button" type="button">担当中</button>
      <button class="tab-button" type="button">期限あり</button>
      <button class="tab-button" type="button">フォロー中</button>
    </div>

    <div class="toolbar">
      <div class="toolbar-group">
        <input v-model="query" class="search-input" type="search" placeholder="課題名・担当者・プロジェクトで検索" />
        <select v-model="filters.projectId" @change="load">
          <option value="">すべてのプロジェクト</option>
          <option v-for="project in projects" :key="project.id" :value="project.id">{{ project.projectKey }}</option>
        </select>
        <select v-model="filters.status" @change="load">
          <option value="">すべてのステータス</option>
          <option value="TODO">未着手</option>
          <option value="IN_PROGRESS">対応中</option>
          <option value="REVIEW">レビュー中</option>
          <option value="DONE">完了</option>
        </select>
      </div>
      <div class="view-switcher" aria-label="View switcher">
        <button class="view-button" :class="{ active: viewMode === 'list' }" type="button" @click="viewMode = 'list'">一覧</button>
        <button class="view-button" :class="{ active: viewMode === 'board' }" type="button" @click="viewMode = 'board'">ボード</button>
        <button class="view-button" :class="{ active: viewMode === 'timeline' }" type="button" @click="viewMode = 'timeline'">ガント</button>
      </div>
    </div>

    <div v-if="viewMode === 'list'" class="table-card">
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>プロジェクト</th>
            <th>件名</th>
            <th>ステータス</th>
            <th>優先度</th>
            <th>担当者</th>
            <th>期限日</th>
            <th>更新日時</th>
            <th></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="issue in filteredIssues" :key="issue.id" :class="['status-line', statusClass(issue.status)]">
            <td>#{{ issue.id }}</td>
            <td>{{ issue.projectKey }}</td>
            <td>
              <div class="row-title">
                <RouterLink class="text-link" :to="`/issues/${issue.id}`">{{ issue.title }}</RouterLink>
                <small>{{ issue.projectName }}</small>
              </div>
            </td>
            <td><span :class="['badge', statusClass(issue.status)]">{{ issue.statusLabel }}</span></td>
            <td><span :class="['badge', priorityClass(issue.priority)]">{{ issue.priorityLabel }}</span></td>
            <td>{{ issue.assigneeName || "未設定" }}</td>
            <td>{{ issue.dueDate || "-" }}</td>
            <td>{{ formatDate(issue.updatedAt) }}</td>
            <td class="actions">
              <RouterLink class="button secondary" :to="`/issues/${issue.id}/edit`">編集</RouterLink>
              <button class="button danger" type="button" @click="remove(issue.id)">削除</button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>

    <div v-else-if="viewMode === 'board'" class="kanban-board">
      <section v-for="column in statusColumns" :key="column.status" class="kanban-column">
        <div class="kanban-heading">
          <span>{{ column.label }}</span>
          <span>{{ column.items.length }}</span>
        </div>
        <RouterLink
          v-for="issue in column.items"
          :key="issue.id"
          :to="`/issues/${issue.id}`"
          :class="['task-card', statusClass(issue.status)]"
        >
          <strong>{{ issue.title }}</strong>
          <div class="task-card-meta">
            <span>#{{ issue.id }} {{ issue.projectKey }}</span>
            <span>{{ issue.assigneeName || "未設定" }}</span>
          </div>
          <div class="actions">
            <span :class="['badge', priorityClass(issue.priority)]">{{ issue.priorityLabel }}</span>
            <span class="muted">{{ issue.dueDate || "期限なし" }}</span>
          </div>
        </RouterLink>
      </section>
    </div>

    <div v-else class="timeline-board">
      <div class="timeline-header">
        <div>課題</div>
        <div>開始日</div>
        <div>期限日</div>
        <div>タイムライン</div>
      </div>
      <div v-for="(issue, index) in filteredIssues" :key="issue.id" class="timeline-row">
        <div class="row-title">
          <RouterLink class="text-link" :to="`/issues/${issue.id}`">#{{ issue.id }} {{ issue.title }}</RouterLink>
          <small>{{ issue.projectKey }} / {{ issue.assigneeName || "未設定" }}</small>
        </div>
        <div>{{ formatDateOnly(issue.createdAt) }}</div>
        <div>{{ issue.dueDate || "-" }}</div>
        <div class="timeline-track">
          <span
            class="timeline-bar"
            :class="timelineClass(issue.status)"
            :style="{ left: `${(index % 5) * 58}px`, width: `${150 + (index % 4) * 42}px` }"
          ></span>
        </div>
      </div>
    </div>
  </PageSection>
</template>

<script setup lang="ts">
import { computed, onMounted, reactive, ref } from "vue";
import { RouterLink } from "vue-router";
import PageSection from "../../components/PageSection.vue";
import { issueApi } from "../../api/issueApi";
import { projectApi } from "../../api/projectApi";
import type { Issue, IssuePriority, IssueStatus } from "../../types/issue";
import type { Project } from "../../types/project";

type ViewMode = "list" | "board" | "timeline";

const issues = ref<Issue[]>([]);
const projects = ref<Project[]>([]);
const error = ref("");
const query = ref("");
const viewMode = ref<ViewMode>("list");
const filters = reactive<{ projectId: string; status: "" | IssueStatus }>({ projectId: "", status: "" });

const filteredIssues = computed(() => {
  const keyword = query.value.trim().toLowerCase();
  if (!keyword) return issues.value;

  return issues.value.filter((issue) =>
    [issue.title, issue.projectKey, issue.projectName, issue.assigneeName, issue.statusLabel, issue.priorityLabel]
      .filter(Boolean)
      .some((value) => String(value).toLowerCase().includes(keyword))
  );
});

const openIssueCount = computed(() => filteredIssues.value.filter((issue) => issue.status !== "DONE").length);

const statusColumns = computed(() =>
  [
    { status: "TODO" as IssueStatus, label: "未着手" },
    { status: "IN_PROGRESS" as IssueStatus, label: "対応中" },
    { status: "REVIEW" as IssueStatus, label: "レビュー中" },
    { status: "DONE" as IssueStatus, label: "完了" }
  ].map((column) => ({
    ...column,
    items: filteredIssues.value.filter((issue) => issue.status === column.status)
  }))
);

function formatDate(value: string) {
  return new Date(value).toLocaleString("ja-JP");
}

function formatDateOnly(value: string) {
  return new Date(value).toLocaleDateString("ja-JP");
}

function statusClass(status: IssueStatus) {
  return {
    TODO: "status-todo",
    IN_PROGRESS: "status-progress",
    REVIEW: "status-review",
    DONE: "status-done"
  }[status];
}

function priorityClass(priority: IssuePriority) {
  return {
    LOW: "priority-low",
    MEDIUM: "priority-medium",
    HIGH: "priority-high"
  }[priority];
}

function timelineClass(status: IssueStatus) {
  return {
    TODO: "",
    IN_PROGRESS: "progress",
    REVIEW: "review",
    DONE: "done"
  }[status];
}

async function load() {
  issues.value = await issueApi.findAll({
    projectId: filters.projectId ? Number(filters.projectId) : undefined,
    status: filters.status || undefined
  });
}

async function resetFilters() {
  query.value = "";
  filters.projectId = "";
  filters.status = "";
  await load();
}

async function remove(id: number) {
  if (!confirm("この課題を削除しますか？")) return;
  try {
    await issueApi.delete(id);
    await load();
  } catch (e: any) {
    error.value = e.message ?? "削除に失敗しました。";
  }
}

onMounted(async () => {
  projects.value = await projectApi.findAll();
  await load();
});
</script>
````

## File: frontend/src/views/Issues/IssuesView.vue
````vue
<template>
  <PageSection
    title="課題一覧"
    kicker="Issues"
    description="優先度と進捗に応じて課題を一覧で確認できます。"
  >
    <div class="issue-list">
      <RouterLink
        v-for="issue in issues"
        :key="issue.id"
        class="issue-card"
        :to="`/issues/${issue.id}`"
      >
        <div class="issue-card-row">
          <strong>{{ issue.key }}</strong>
          <span class="pill">{{ issue.status }}</span>
        </div>
        <h3>{{ issue.title }}</h3>
        <p>{{ issue.project }} / {{ issue.priority }} / {{ issue.assignee }}</p>
      </RouterLink>
    </div>
  </PageSection>
</template>

<script setup lang="ts">
import { RouterLink } from "vue-router";
import PageSection from "../../components/PageSection.vue";

const issues = [
  { id: 1, key: "ISS-101", title: "ログイン画面の入力検証を追加", status: "対応中", project: "Alpha Lane", priority: "High", assignee: "Airi Kato" },
  { id: 2, key: "ISS-102", title: "課題詳細 API の仕様確認", status: "未着手", project: "Nova Hub", priority: "Medium", assignee: "Ren Ito" },
  { id: 3, key: "ISS-103", title: "ダッシュボード指標の表示改善", status: "レビュー中", project: "Sprint Canvas", priority: "Low", assignee: "Mio Sato" }
];
</script>
````

## File: frontend/src/views/Login/LoginView.vue
````vue
<template>
  <div class="login-screen">
    <div class="login-card">
      <p class="section-kicker">Access Portal</p>
      <h2>ProjectFlow にサインイン</h2>
      <p class="muted">プロジェクト管理画面のデモ用ログインページです。</p>

      <form class="login-form" @submit.prevent="login">
        <label>
          <span>メールアドレス</span>
          <input type="email" placeholder="demo@projectflow.local" />
        </label>
        <label>
          <span>パスワード</span>
          <input type="password" placeholder="********" />
        </label>
        <button type="submit">ログイン</button>
      </form>
    </div>
  </div>
</template>

<script setup lang="ts">
import { useRouter } from "vue-router";

const router = useRouter();

function login() {
  router.push("/dashboard");
}
</script>
````

## File: frontend/src/views/ProjectForm/ProjectFormView.vue
````vue
<template>
  <PageSection :title="isEdit ? 'プロジェクト編集' : 'プロジェクト登録'" kicker="Project Form">
    <form class="form-panel" @submit.prevent="save">
      <p v-if="error" class="alert">{{ error }}</p>
      <label>
        プロジェクトキー
        <input v-model.trim="form.projectKey" required maxlength="20" pattern="[A-Za-z0-9_-]+" />
      </label>
      <label>
        プロジェクト名
        <input v-model.trim="form.name" required maxlength="100" />
      </label>
      <label>
        説明
        <textarea v-model.trim="form.description" maxlength="2000" rows="6"></textarea>
      </label>
      <div class="actions">
        <button class="button" type="submit">保存</button>
        <RouterLink class="button secondary" to="/projects">戻る</RouterLink>
      </div>
    </form>
  </PageSection>
</template>

<script setup lang="ts">
import { computed, onMounted, reactive, ref } from "vue";
import { RouterLink, useRouter } from "vue-router";
import PageSection from "../../components/PageSection.vue";
import { projectApi } from "../../api/projectApi";
import type { ProjectRequest } from "../../types/project";

const props = defineProps<{ id?: string }>();
const router = useRouter();
const isEdit = computed(() => Boolean(props.id));
const error = ref("");
const form = reactive<ProjectRequest>({ projectKey: "", name: "", description: "" });

onMounted(async () => {
  if (!props.id) return;
  const project = await projectApi.findById(Number(props.id));
  form.projectKey = project.projectKey;
  form.name = project.name;
  form.description = project.description ?? "";
});

async function save() {
  try {
    if (props.id) {
      await projectApi.update(Number(props.id), form);
    } else {
      await projectApi.create(form);
    }
    await router.push("/projects");
  } catch (e: any) {
    error.value = e.message ?? "保存に失敗しました。";
  }
}
</script>
````

## File: frontend/src/views/ProjectList/ProjectListView.vue
````vue
<template>
  <PageSection
    title="プロジェクト一覧"
    kicker="Projects"
    description="進行中のプロジェクトを確認し、基本情報を管理します。"
  >
    <p v-if="error" class="alert">{{ error }}</p>

    <div class="summary-bar">
      <div>
        <p class="section-kicker">Current view</p>
        <div class="summary-meta">
          <span>{{ filteredProjects.length }} 件を表示</span>
          <span>{{ projects.length }} 件中</span>
        </div>
      </div>
      <div class="actions">
        <button class="button secondary" type="button" @click="query = ''">リセット</button>
        <RouterLink class="button" to="/projects/new">新規作成</RouterLink>
      </div>
    </div>

    <div class="page-tabs">
      <button class="tab-button active" type="button">すべて</button>
      <button class="tab-button" type="button">進行中</button>
      <button class="tab-button" type="button">更新順</button>
    </div>

    <div class="toolbar">
      <div class="toolbar-group">
        <input v-model="query" class="search-input" type="search" placeholder="キー・名称・説明で検索" />
      </div>
      <div class="view-switcher">
        <button class="view-button active" type="button">一覧</button>
        <button class="view-button" type="button">ボード</button>
      </div>
    </div>

    <div class="table-card">
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>キー</th>
            <th>名称</th>
            <th>説明</th>
            <th>更新日時</th>
            <th></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="project in filteredProjects" :key="project.id" class="status-line status-progress">
            <td>#{{ project.id }}</td>
            <td><span class="badge status-review">{{ project.projectKey }}</span></td>
            <td>
              <div class="row-title">
                <strong>{{ project.name }}</strong>
                <small>Project workspace</small>
              </div>
            </td>
            <td>{{ project.description || "-" }}</td>
            <td>{{ formatDate(project.updatedAt) }}</td>
            <td class="actions">
              <RouterLink class="button secondary" :to="`/projects/${project.id}/edit`">編集</RouterLink>
              <button class="button danger" type="button" @click="remove(project.id)">削除</button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </PageSection>
</template>

<script setup lang="ts">
import { computed, onMounted, ref } from "vue";
import { RouterLink } from "vue-router";
import PageSection from "../../components/PageSection.vue";
import { projectApi } from "../../api/projectApi";
import type { Project } from "../../types/project";

const projects = ref<Project[]>([]);
const error = ref("");
const query = ref("");

const filteredProjects = computed(() => {
  const keyword = query.value.trim().toLowerCase();
  if (!keyword) return projects.value;

  return projects.value.filter((project) =>
    [project.projectKey, project.name, project.description]
      .filter(Boolean)
      .some((value) => String(value).toLowerCase().includes(keyword))
  );
});

function formatDate(value: string) {
  return new Date(value).toLocaleString("ja-JP");
}

async function load() {
  projects.value = await projectApi.findAll();
}

async function remove(id: number) {
  if (!confirm("このプロジェクトを削除しますか？")) return;
  try {
    await projectApi.delete(id);
    await load();
  } catch (e: any) {
    error.value = e.message ?? "削除に失敗しました。";
  }
}

onMounted(load);
</script>
````

## File: frontend/src/views/Projects/ProjectsView.vue
````vue
<template>
  <PageSection
    title="プロジェクト一覧"
    kicker="Projects"
    description="サンプルプロジェクトの状態と担当者を確認できます。"
  >
    <div class="table-card">
      <table>
        <thead>
          <tr>
            <th>コード</th>
            <th>名称</th>
            <th>状態</th>
            <th>担当</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="project in projects" :key="project.code">
            <td>{{ project.code }}</td>
            <td>{{ project.name }}</td>
            <td><span class="pill">{{ project.status }}</span></td>
            <td>{{ project.owner }}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </PageSection>
</template>

<script setup lang="ts">
import PageSection from "../../components/PageSection.vue";

const projects = [
  { code: "PJF-101", name: "Alpha Lane", status: "進行中", owner: "Airi Kato" },
  { code: "PJF-102", name: "Nova Hub", status: "計画中", owner: "Ren Ito" },
  { code: "PJF-103", name: "Sprint Canvas", status: "進行中", owner: "Mio Sato" }
];
</script>
````

## File: frontend/src/App.vue
````vue
<template>
  <RouterView v-if="isPublicLayout" />

  <div v-else class="shell">
    <aside class="sidebar">
      <RouterLink class="brand" to="/dashboard">
        <span class="brand-mark">PF</span>
        <span class="brand-copy">
          <strong>ProjectFlow</strong>
          <small>Project workspace</small>
        </span>
      </RouterLink>

      <div class="sidebar-action">
        <RouterLink class="sidebar-create" to="/projects/new">新規プロジェクト</RouterLink>
      </div>

      <nav class="menu" aria-label="Main navigation">
        <p class="sidebar-label">メニュー</p>
        <RouterLink to="/dashboard">ダッシュボード</RouterLink>
        <RouterLink to="/projects">プロジェクト</RouterLink>
        <RouterLink to="/issues">課題</RouterLink>
      </nav>

      <div class="project-nav">
        <div class="sidebar-label-row">
          <p class="sidebar-label">プロジェクト</p>
          <span>3</span>
        </div>
        <RouterLink class="project-nav-item active" to="/issues">
          <span class="project-icon">PF</span>
          <span>業務進捗管理</span>
        </RouterLink>
        <RouterLink class="project-nav-item" to="/projects">
          <span class="project-icon muted">PJ</span>
          <span>社内改善</span>
        </RouterLink>
        <RouterLink class="project-nav-item" to="/projects">
          <span class="project-icon muted">QA</span>
          <span>品質管理</span>
        </RouterLink>
      </div>

      <div class="sidebar-footer">
        <div class="user-info-block">
          <div class="user-avatar">PF</div>
          <div class="user-meta">
            <span class="user-name">ProjectFlow</span>
            <span class="user-role">v0.1.0</span>
          </div>
        </div>
      </div>
    </aside>

    <div class="workspace">
      <header class="topbar">
        <div class="topbar-left">
          <span class="breadcrumb">Workspace / ProjectFlow</span>
        </div>
        <div class="topbar-tools">
          <input class="global-search" type="search" placeholder="キーワードで検索" />
          <button class="icon-button" type="button" aria-label="通知">通知</button>
          <button class="icon-button" type="button" aria-label="ヘルプ">ヘルプ</button>
          <div class="topbar-user">
            <span class="user-avatar small">PF</span>
            <span>管理者</span>
          </div>
        </div>
      </header>

      <main class="content">
        <RouterView />
      </main>
    </div>
  </div>
</template>

<script setup lang="ts">
import { computed } from "vue";
import { RouterLink, RouterView, useRoute } from "vue-router";

const route = useRoute();
const isPublicLayout = computed(() => route.meta.publicLayout === true);
</script>
````

## File: frontend/src/main.ts
````typescript
import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import "./styles/main.css";

createApp(App).use(router).mount("#app");
````

## File: frontend/src/vite-env.d.ts
````typescript
/// <reference types="vite/client" />
````

## File: frontend/package.json
````json
{
  "name": "project-management-app",
  "private": true,
  "version": "0.1.0",
  "type": "module",
  "scripts": {
    "dev": "vite",
    "build": "vue-tsc --noEmit && vite build",
    "preview": "vite preview"
  },
  "dependencies": {
    "vue": "^3.5.13",
    "vue-router": "^4.5.1"
  },
  "devDependencies": {
    "@types/node": "^22.10.7",
    "@vitejs/plugin-vue": "^5.2.1",
    "typescript": "^5.7.2",
    "vite": "^6.0.5",
    "vue-tsc": "^2.2.0"
  }
}
````

## File: frontend/tsconfig.json
````json
{
  "compilerOptions": {
    "target": "ES2020",
    "useDefineForClassFields": true,
    "module": "ESNext",
    "moduleResolution": "Bundler",
    "strict": true,
    "jsx": "preserve",
    "resolveJsonModule": true,
    "isolatedModules": true,
    "esModuleInterop": true,
    "lib": ["ES2020", "DOM", "DOM.Iterable"],
    "skipLibCheck": true,
    "types": ["node"]
  },
  "include": ["src/**/*.ts", "src/**/*.d.ts", "src/**/*.tsx", "src/**/*.vue"]
}
````

## File: frontend/tsconfig.node.json
````json
{
  "compilerOptions": {
    "composite": true,
    "module": "ESNext",
    "moduleResolution": "Bundler",
    "allowSyntheticDefaultImports": true
  },
  "include": ["vite.config.ts"]
}
````

## File: frontend/vite.config.ts
````typescript
import { defineConfig } from "vite";
import vue from "@vitejs/plugin-vue";

export default defineConfig({
  plugins: [vue()],
  server: {
    port: 5173
  }
});
````
