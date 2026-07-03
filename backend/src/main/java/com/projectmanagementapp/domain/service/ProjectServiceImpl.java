package com.projectmanagementapp.domain.service;

import com.projectmanagementapp.domain.dao.ProjectDao;
import com.projectmanagementapp.domain.model.Project;
import com.projectmanagementapp.dto.ProjectRequest;
import com.projectmanagementapp.dto.ProjectResponse;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectDao projectDao;

    public ProjectServiceImpl(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    @Override
    public List<ProjectResponse> findAll() {
        return projectDao.findAll().stream()
            .map(this::toResponse)
            .toList();
    }

    @Override
    public ProjectResponse findById(Long id) {
        Project project = projectDao.findById(id);
        if (project == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found");
        }
        return toResponse(project);
    }

    @Override
    public ProjectResponse create(ProjectRequest request) {
        return toResponse(projectDao.create(request));
    }

    @Override
    public ProjectResponse update(Long id, ProjectRequest request) {
        Project project = projectDao.update(id, request);
        if (project == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found");
        }
        return toResponse(project);
    }

    @Override
    public void delete(Long id) {
        if (projectDao.deleteById(id) == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found");
        }
    }

    private ProjectResponse toResponse(Project project) {
        return new ProjectResponse(
            project.getId(),
            project.getCode(),
            project.getName(),
            project.getStatus(),
            project.getOwner(),
            project.getDescription()
        );
    }
}
