package com.projectmanagementapp.controller;

import com.projectmanagementapp.dto.IssueDetailResponse;
import com.projectmanagementapp.dto.IssueRequest;
import com.projectmanagementapp.dto.IssueSummaryResponse;
import com.projectmanagementapp.domain.service.IssueService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/issues")
public class IssueController {

    private final IssueService issueService;

    public IssueController(IssueService issueService) {
        this.issueService = issueService;
    }

    @GetMapping
    public List<IssueSummaryResponse> findAll() {
        return issueService.findAll();
    }

    @GetMapping("/{issueId}")
    public IssueDetailResponse findById(@PathVariable Long issueId) {
        return issueService.findById(issueId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public IssueDetailResponse create(@Valid @RequestBody IssueRequest request) {
        return issueService.create(request);
    }

    @PutMapping("/{issueId}")
    public IssueDetailResponse update(@PathVariable Long issueId, @Valid @RequestBody IssueRequest request) {
        return issueService.update(issueId, request);
    }

    @DeleteMapping("/{issueId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long issueId) {
        issueService.delete(issueId);
    }
}

