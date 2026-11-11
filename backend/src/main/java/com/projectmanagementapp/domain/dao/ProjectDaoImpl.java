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
    public List<Project> findAll(String keyword) {
        // 受け取ったキーワードをそのままMapper（XML側）に流し込む
        return projectMapper.findAll(keyword);
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
