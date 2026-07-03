package com.projectmanagementapp.controller;

import com.projectmanagementapp.dto.DashboardSummaryResponse;
import com.projectmanagementapp.domain.service.DashboardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping
    public DashboardSummaryResponse getSummary() {
        return dashboardService.getSummary();
    }
}

