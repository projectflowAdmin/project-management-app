package com.projectmanagementapp.domain.dao.mapper;

import com.projectmanagementapp.domain.model.Issue;
import com.projectmanagementapp.dto.IssueRequest;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IssueMapper {

    List<Issue> findAll();

    Issue findById(Long id);

    Issue create(IssueRequest request);

    Issue update(@Param("id") Long id, @Param("request") IssueRequest request);

    int deleteById(Long id);
}
