package com.projectmanagementapp.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Value
public class IssueRequest {
    @NotBlank
    String issueKey;

    @NotNull
    Long projectId;

    @NotBlank
    String title;

    @NotBlank
    String status;

    @NotBlank
    String priority;

    @NotBlank
    String assignee;

    String dueDate;
    String description;

    @JsonCreator
    public IssueRequest(
        @JsonProperty("issueKey") String issueKey,
        @JsonProperty("projectId") Long projectId,
        @JsonProperty("title") String title,
        @JsonProperty("status") String status,
        @JsonProperty("priority") String priority,
        @JsonProperty("assignee") String assignee,
        @JsonProperty("dueDate") String dueDate,
        @JsonProperty("description") String description
    ) {
        this.issueKey = issueKey;
        this.projectId = projectId;
        this.title = title;
        this.status = status;
        this.priority = priority;
        this.assignee = assignee;
        this.dueDate = dueDate;
        this.description = description;
    }
}
