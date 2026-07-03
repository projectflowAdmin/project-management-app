package com.projectmanagementapp.domain.service;

import com.projectmanagementapp.dto.DashboardSummaryResponse;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class DashboardServiceImpl implements DashboardService {

    @Override
    public DashboardSummaryResponse getSummary() {
        return new DashboardSummaryResponse(
            8,
            14,
            3,
            List.of(
                "UI モックアップをレビュー待ちに更新",
                "API 設計の草案をチームへ共有",
                "プロジェクト Alpha Lane の作業を開始"
            )
        );
    }
}
