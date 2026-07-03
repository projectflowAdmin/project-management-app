package com.projectmanagementapp.domain.service;

import com.projectmanagementapp.domain.dao.IssueDao;
import com.projectmanagementapp.domain.model.Issue;
import com.projectmanagementapp.dto.IssueDetailResponse;
import com.projectmanagementapp.dto.IssueRequest;
import com.projectmanagementapp.dto.IssueSummaryResponse;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class IssueServiceImpl implements IssueService {

    private final IssueDao issueDao;

    public IssueServiceImpl(IssueDao issueDao) {
        this.issueDao = issueDao;
    }

    @Override
    public List<IssueSummaryResponse> findAll() {
        return issueDao.findAll().stream()
            .map(issue -> new IssueSummaryResponse(
                issue.getId(),
                issue.getIssueKey(),
                issue.getProjectName(),
                issue.getTitle(),
                issue.getStatus(),
                issue.getPriority(),
                issue.getAssignee()
            ))
            .toList();
    }

    @Override
    public IssueDetailResponse findById(Long id) {
        Issue issue = issueDao.findById(id);
        if (issue == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Issue not found");
        }
        return toDetailResponse(issue);
    }

    @Override
    public IssueDetailResponse create(IssueRequest request) {
        return toDetailResponse(issueDao.create(request));
    }

    @Override
    public IssueDetailResponse update(Long id, IssueRequest request) {
        Issue issue = issueDao.update(id, request);
        if (issue == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Issue not found");
        }
        return toDetailResponse(issue);
    }

    @Override
    public void delete(Long id) {
        if (issueDao.deleteById(id) == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Issue not found");
        }
    }

    private IssueDetailResponse toDetailResponse(Issue issue) {
        return new IssueDetailResponse(
            issue.getId(),
            issue.getIssueKey(),
            issue.getProjectName(),
            issue.getTitle(),
            issue.getStatus(),
            issue.getPriority(),
            issue.getAssignee(),
            issue.getDueDate(),
            issue.getDescription()
        );
    }
}
