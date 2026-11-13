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
        List<Project> allProjects = projectDao.findAll();
        
        boolean isDuplicated = allProjects.stream()
            .anyMatch(p -> p.getProjectKey().equalsIgnoreCase(request.getProjectKey()));
            
        if (isDuplicated) {
            throw new BusinessException(CommonMessage.PROJECT_KEY_ALREADY_USED);
        }
        
        return toResponse(projectDao.insert(request));
    }

    @Override
    @Transactional
    public ProjectResponse update(Long id, ProjectRequest request) {

        findProject(id);
        
        List<Project> allProjects = projectDao.findAll();
        
        boolean isDuplicated = allProjects.stream()
            .filter(p -> !p.getId().equals(id)) 
            .anyMatch(p -> p.getProjectKey().equalsIgnoreCase(request.getProjectKey()));
            
        if (isDuplicated) {
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
