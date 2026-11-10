package com.projectmanagementapp.domain.dao.mapper;

import com.projectmanagementapp.domain.model.Project;
import com.projectmanagementapp.dto.ProjectRequest;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjectMapper {
    List<Project> findAll(@Param("keyword") String keyword);
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
