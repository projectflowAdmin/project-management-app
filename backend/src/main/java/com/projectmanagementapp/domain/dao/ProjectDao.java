package com.projectmanagementapp.domain.dao;

import com.projectmanagementapp.domain.model.Project;
import com.projectmanagementapp.dto.ProjectRequest;
import java.util.List;

public interface ProjectDao {

    List<Project> findAll();

    Project findById(Long id);

    Project create(ProjectRequest request);

    Project update(Long id, ProjectRequest request);

    int deleteById(Long id);
}
