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

    // @Override
    // public List<ProjectResponse> findAll() {
    //     return projectDao.findAll().stream().map(this::toResponse).toList();
    // }
    @Override
    public List<ProjectResponse> findAll(String keyword) {
        List<Project> allProjects = projectDao.findAll();

        // 受入条件：キーワードが未指定または空の場合、既存仕様どおり全プロジェクトが返される
        if (keyword == null || keyword.isBlank()) {
            return allProjects.stream().map(this::toResponse).toList();
        }

        // 検索キーワードを小文字に統一
        String lowerKeyword = keyword.toLowerCase();

        return allProjects.stream()
            .filter(project -> {
                // 1. プロジェクトキーのチェック
                boolean matchKey = project.getProjectKey() != null 
                    && project.getProjectKey().toLowerCase().contains(lowerKeyword);
                
                // 2. プロジェクト名のチェック
                boolean matchName = project.getName() != null 
                    && project.getName().toLowerCase().contains(lowerKeyword);
                
                // 3. 説明（description）のチェック（受入条件：未設定 NULL でもエラーが発生しないこと）
                boolean matchDesc = project.getDescription() != null 
                    && project.getDescription().toLowerCase().contains(lowerKeyword);
                
                // いずれかに部分一致すれば対象とする（anyMatchのため重複して返されない）
                return matchKey || matchName || matchDesc;
            })
            .map(this::toResponse)
            .toList();
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
