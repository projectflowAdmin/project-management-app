package com.projectmanagementapp.domain.service;

import com.projectmanagementapp.dto.IssueDetailResponse;
import com.projectmanagementapp.dto.IssueRequest;
import com.projectmanagementapp.dto.IssueSummaryResponse;
import java.util.List;

public interface IssueService {

    List<IssueSummaryResponse> findAll();

    IssueDetailResponse findById(Long id);

    IssueDetailResponse create(IssueRequest request);

    IssueDetailResponse update(Long id, IssueRequest request);

    void delete(Long id);
}
