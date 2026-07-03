package com.projectmanagementapp.dto;

import java.util.List;
import lombok.Value;

@Value
public class DashboardSummaryResponse {
    int activeProjects;
    int openIssues;
    int dueToday;
    List<String> recentUpdates;
}
