package com.projectmanagementapp.domain.dao;

import com.projectmanagementapp.domain.model.Project;
import com.projectmanagementapp.dto.ProjectRequest;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ProjectDao {
    List<Project> findAll(@Param("keyword") String keyword);
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
