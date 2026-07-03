package com.projectmanagementapp.dto;

import lombok.Value;

@Value
public class IssueDetailResponse {
    Long id;
    String issueKey;
    String projectName;
    String title;
    String status;
    String priority;
    String assignee;
    String dueDate;
    String description;
}
