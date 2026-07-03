package com.projectmanagementapp.domain.dao;

import com.projectmanagementapp.domain.model.Issue;
import com.projectmanagementapp.dto.IssueRequest;
import java.util.List;

public interface IssueDao {

    List<Issue> findAll();

    Issue findById(Long id);

    Issue create(IssueRequest request);

    Issue update(Long id, IssueRequest request);

    int deleteById(Long id);
}
