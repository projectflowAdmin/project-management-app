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
    public Project create(ProjectRequest request) {
        return projectMapper.create(request);
    }

    @Override
    public Project update(Long id, ProjectRequest request) {
        return projectMapper.update(id, request);
    }

    @Override
    public int deleteById(Long id) {
        return projectMapper.deleteById(id);
    }
}
