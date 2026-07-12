<template>
  <PageSection title="ダッシュボード" kicker="Dashboard" description="プロジェクト全体の状態と直近の更新を確認します。">
    <p v-if="error" class="alert">{{ error }}</p>

    <div class="summary-bar">
      <div>
        <p class="section-kicker">Workspace status</p>
        <div class="summary-meta">
          <span class="health-row">
            <span class="status-dot" :class="{ ok: health === 'UP' }"></span>
            API {{ health || "確認中" }}
          </span>
          <span>{{ dashboard?.issueCount ?? "-" }} 件の課題</span>
          <span>{{ dashboard?.projectCount ?? "-" }} 件のプロジェクト</span>
        </div>
      </div>
      <div class="actions">
        <RouterLink class="button secondary" to="/projects">プロジェクト一覧</RouterLink>
        <RouterLink class="button" to="/issues/new">課題を作成</RouterLink>
      </div>
    </div>

    <div class="stats-grid dashboard-stats">
      <article class="stat-card stat-project">
        <span class="stat-icon project-stat-icon" aria-hidden="true">
          <img :src="projectDashboardIcon" alt="" />
        </span>
        <div class="stat-copy">
          <span class="stat-label">プロジェクト</span>
          <strong>{{ dashboard?.projectCount ?? "-" }}</strong>
          <small>管理中のワークスペース</small>
        </div>
      </article>
      <article class="stat-card stat-issue">
        <span class="stat-icon issue-stat-icon" aria-hidden="true">
          <img :src="issueDashboardIcon" alt="" />
        </span>
        <div class="stat-copy">
          <span class="stat-label">課題</span>
          <strong>{{ dashboard?.issueCount ?? "-" }}</strong>
          <small>登録されている課題</small>
        </div>
      </article>
      <article class="stat-card stat-todo">
        <span class="stat-icon todo-stat-icon" aria-hidden="true">
          <img :src="todoDashboardIcon" alt="" />
        </span>
        <div class="stat-copy">
          <span class="stat-label">未着手</span>
          <strong>{{ dashboard?.todoCount ?? "-" }}</strong>
          <small>着手待ち</small>
        </div>
      </article>
      <article class="stat-card stat-progress">
        <span class="stat-icon progress-stat-icon" aria-hidden="true">
          <img :src="progressDashboardIcon" alt="" />
        </span>
        <div class="stat-copy">
          <span class="stat-label">対応中</span>
          <strong>{{ dashboard?.inProgressCount ?? "-" }}</strong>
          <small>進行中の作業</small>
        </div>
      </article>
      <article class="stat-card stat-review">
        <span class="stat-icon review-stat-icon" aria-hidden="true">
          <img :src="reviewDashboardIcon" alt="" />
        </span>
        <div class="stat-copy">
          <span class="stat-label">レビュー中</span>
          <strong>{{ dashboard?.reviewCount ?? "-" }}</strong>
          <small>確認待ち</small>
        </div>
      </article>
      <article class="stat-card stat-done">
        <span class="stat-icon complete-stat-icon" aria-hidden="true">
          <img :src="completeDashboardIcon" alt="" />
        </span>
        <div class="stat-copy">
          <span class="stat-label">完了</span>
          <strong>{{ dashboard?.doneCount ?? "-" }}</strong>
          <small>クローズ済み</small>
        </div>
      </article>
    </div>

    <div class="page-tabs">
      <button class="tab-button active" type="button">最近の更新</button>
      <button class="tab-button" type="button">担当中</button>
      <button class="tab-button" type="button">期限あり</button>
    </div>

    <section class="panel">
      <div class="section-line">
        <div>
          <p class="section-kicker">Recent activity</p>
          <h3>最近更新された課題</h3>
        </div>
        <RouterLink class="button secondary" to="/issues">一覧を開く</RouterLink>
      </div>
      <div class="table-card">
        <table>
          <thead>
            <tr>
              <th>プロジェクト</th>
              <th>件名</th>
              <th>ステータス</th>
              <th>更新日時</th>
            </tr>
          </thead>
          <tbody>
            <tr
              v-for="issue in dashboard?.recentIssues ?? []"
              :key="issue.id"
              :class="['status-line', statusClass(issue.status)]"
              @click="router.push(`/issues/${issue.id}`)"
            >
              <td>{{ issue.projectKey }}</td>
              <td>
                <div class="row-title">
                  <span class="text-link">{{ issue.title }}</span>
                  <small>#{{ issue.id }}</small>
                </div>
              </td>
              <td><span :class="['badge', statusClass(issue.status)]">{{ issue.statusLabel }}</span></td>
              <td>{{ formatDate(issue.updatedAt) }}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </section>
  </PageSection>
</template>

<script setup lang="ts">
import { onMounted, ref } from "vue";
import { RouterLink, useRouter } from "vue-router";
import PageSection from "../../components/PageSection.vue";
import { dashboardApi } from "../../api/dashboardApi";
import { healthApi } from "../../api/healthApi";
import completeDashboardIcon from "../../assets/complete-dashboard-icon.png";
import issueDashboardIcon from "../../assets/issue-dashboard-icon.svg";
import progressDashboardIcon from "../../assets/progress-dashboard-icon.png";
import projectDashboardIcon from "../../assets/project-dashboard-icon.png";
import reviewDashboardIcon from "../../assets/review-dashboard-icon.png";
import todoDashboardIcon from "../../assets/todo-dashboard-icon.png";
import type { Dashboard } from "../../types/dashboard";
import type { IssueStatus } from "../../types/issue";

const router = useRouter();
const dashboard = ref<Dashboard>();
const health = ref("");
const error = ref("");

function formatDate(value: string) {
  return new Date(value).toLocaleString("ja-JP");
}

function statusClass(status: IssueStatus) {
  return {
    TODO: "status-todo",
    IN_PROGRESS: "status-progress",
    REVIEW: "status-review",
    DONE: "status-done"
  }[status];
}

onMounted(async () => {
  try {
    const [healthResponse, dashboardResponse] = await Promise.all([healthApi.get(), dashboardApi.get()]);
    health.value = healthResponse.status;
    dashboard.value = dashboardResponse;
  } catch (e) {
    error.value = e instanceof Error ? e.message : "データの取得に失敗しました。";
  }
});
</script>
