package com.projectmanagementapp.domain.model;

import lombok.Value;

@Value
public class Issue {
    Long id;
    String issueKey;
    Long projectId;
    String projectName;
    String title;
    String status;
    String priority;
    String assignee;
    String dueDate;
    String description;
}
