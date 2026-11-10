package com.projectmanagementapp.domain.service;

import com.projectmanagementapp.domain.dao.IssueDao;
import com.projectmanagementapp.domain.dao.ProjectDao;
import com.projectmanagementapp.domain.model.Issue;
import com.projectmanagementapp.domain.model.IssueStatus;
import com.projectmanagementapp.dto.IssueRequest;
import com.projectmanagementapp.dto.IssueResponse;
import com.projectmanagementapp.exception.ResourceNotFoundException;
import com.projectmanagementapp.message.CommonMessage;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class IssueServiceImpl implements IssueService {

    private final IssueDao issueDao;
    private final ProjectDao projectDao;

    public IssueServiceImpl(IssueDao issueDao, ProjectDao projectDao) {
        this.issueDao = issueDao;
        this.projectDao = projectDao;
    }

    @Override
    public List<IssueResponse> findAll(Long projectId, IssueStatus status) {
        // 受入条件：プロジェクトIDが指定されている場合、課題検索前に存在確認を行う
        if (projectId != null) {
            ensureProjectExists(projectId);
        }

        List<Issue> issues;
        // ステータス条件と同時に指定された場合も、上記の存在確認を通った後にここへ到達します
        if (projectId != null && status != null) {
            issues = issueDao.findByProjectIdAndStatus(projectId, status);
        } else if (projectId != null) {
            issues = issueDao.findByProjectId(projectId);
        } else if (status != null) {
            issues = issueDao.findByStatus(status);
        } else {
            // プロジェクトIDが未指定の場合は、既存の全件取得処理を維持
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
        return toResponse(issueDao.insert(request));
    }

    @Override
    @Transactional
    public IssueResponse update(Long id, IssueRequest request) {
        findIssue(id);
        ensureProjectExists(request.getProjectId());
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
