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
