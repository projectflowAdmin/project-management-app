package com.projectmanagementapp.domain.dao.mapper;

import com.projectmanagementapp.domain.model.Project;
import com.projectmanagementapp.dto.ProjectRequest;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjectMapper {

    List<Project> findAll();

    Project findById(Long id);

    Project create(ProjectRequest request);

    Project update(@Param("id") Long id, @Param("request") ProjectRequest request);

    int deleteById(Long id);
}
