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
        List<Project> filteredProjects = projectDao.findAll();
        return filteredProjects.stream()
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
        String uppercaseKey = request.getProjectKey().toUpperCase();
        if (projectDao.existsByProjectKey(uppercaseKey)) {
            throw new BusinessException(CommonMessage.PROJECT_KEY_ALREADY_USED);
        }
        
        // 确保存入数据库的也是大写
        request.setProjectKey(uppercaseKey);
        return toResponse(projectDao.insert(request));
    }


    @Override
    @Transactional
    public ProjectResponse update(Long id, ProjectRequest request) {
        findProject(id);
        String uppercaseKey = request.getProjectKey().toUpperCase();
        if (projectDao.existsByProjectKeyAndIdNot(uppercaseKey, id)) {
            throw new BusinessException(CommonMessage.PROJECT_KEY_ALREADY_USED);
        }
        
        request.setProjectKey(uppercaseKey);
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
