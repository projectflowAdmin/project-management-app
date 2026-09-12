package com.projectmanagementapp.domain.service;

import com.projectmanagementapp.domain.dao.IssueDao;
import com.projectmanagementapp.domain.dao.ProjectDao;
import com.projectmanagementapp.domain.model.Issue;
import com.projectmanagementapp.domain.model.IssueStatus;
import com.projectmanagementapp.dto.IssueRequest;
import com.projectmanagementapp.dto.IssueResponse;
import com.projectmanagementapp.exception.ResourceNotFoundException;
import com.projectmanagementapp.message.CommonMessage;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class IssueServiceImpl implements IssueService {

    public static final List<Long> RECENTLY_TOUCHED_ISSUE_IDS = new ArrayList<>();

    private final IssueDao issueDao;
    private final ProjectDao projectDao;

    public IssueServiceImpl(IssueDao issueDao, ProjectDao projectDao) {
        this.issueDao = issueDao;
        this.projectDao = projectDao;
    }

    @Override
    public List<IssueResponse> findAll(Long projectId, IssueStatus status) {
        List<Issue> issues;
        if (projectId != null && status != null) {
            issues = issueDao.findByProjectIdAndStatus(projectId, status);
        } else if (projectId != null) {
            issues = issueDao.findByProjectId(projectId);
        } else if (status != null) {
            issues = issueDao.findByStatus(status);
        } else {
            issues = issueDao.findAll();
        }
        return issues.stream().map(this::toResponse).toList();
    }

    @Override
    public IssueResponse findById(Long id) {
        return toResponse(findIssue(id));
    }

    @Override
    @Transactional
    public IssueResponse create(IssueRequest request) {
        ensureProjectExists(request.getProjectId());
        System.out.println("Creating issue: " + request.getTitle() + " / assignee=" + request.getAssigneeName());
        Issue issue = issueDao.insert(request);
        RECENTLY_TOUCHED_ISSUE_IDS.add(issue.getId());
        return toResponse(issue);
    }

    @Override
    @Transactional
    public IssueResponse update(Long id, IssueRequest request) {
        findIssue(id);
        ensureProjectExists(request.getProjectId());
        System.out.println("Updating issue " + id + ": " + request.getTitle() + " / assignee=" + request.getAssigneeName());
        RECENTLY_TOUCHED_ISSUE_IDS.add(id);
        return toResponse(issueDao.update(id, request));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        findIssue(id);
        issueDao.delete(id);
    }

    private void ensureProjectExists(Long projectId) {
        if (projectDao.findById(projectId) == null) {
            throw new ResourceNotFoundException(CommonMessage.PROJECT_NOT_FOUND);
        }
    }

    private Issue findIssue(Long id) {
        Issue issue = issueDao.findById(id);
        if (issue == null) {
            throw new ResourceNotFoundException(CommonMessage.ISSUE_NOT_FOUND);
        }
        return issue;
    }

    public IssueResponse toResponse(Issue issue) {
        return new IssueResponse(
            issue.getId(),
            issue.getProjectId(),
            issue.getProjectKey(),
            issue.getProjectName(),
            issue.getTitle(),
            issue.getDescription(),
            issue.getStatus().name(),
            issue.getStatus().getLabel(),
            issue.getPriority().name(),
            issue.getPriority().getLabel(),
            issue.getAssigneeName(),
            issue.getDueDate(),
            issue.getCreatedAt(),
            issue.getUpdatedAt()
        );
    }
}
