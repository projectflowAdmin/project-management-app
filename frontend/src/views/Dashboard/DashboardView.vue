<template>
  <PageSection title="ダッシュボード" kicker="Dashboard">
    <p v-if="error" class="alert">{{ error }}</p>
    <div class="health-row">
      <span class="status-dot" :class="{ ok: health === 'UP' }"></span>
      <span>バックエンド接続: {{ health || "確認中" }}</span>
    </div>

    <div class="stats-grid">
      <article class="stat-card"><span>プロジェクト</span><strong>{{ dashboard?.projectCount ?? "-" }}</strong></article>
      <article class="stat-card"><span>課題</span><strong>{{ dashboard?.issueCount ?? "-" }}</strong></article>
      <article class="stat-card"><span>未着手</span><strong>{{ dashboard?.todoCount ?? "-" }}</strong></article>
      <article class="stat-card"><span>対応中</span><strong>{{ dashboard?.inProgressCount ?? "-" }}</strong></article>
      <article class="stat-card"><span>レビュー中</span><strong>{{ dashboard?.reviewCount ?? "-" }}</strong></article>
      <article class="stat-card"><span>完了</span><strong>{{ dashboard?.doneCount ?? "-" }}</strong></article>
    </div>

    <section class="panel">
      <div class="section-line">
        <h3>最近更新された課題</h3>
        <RouterLink class="button secondary" to="/issues">一覧を見る</RouterLink>
      </div>
      <div class="table-card">
        <table>
          <tbody>
            <tr v-for="issue in dashboard?.recentIssues ?? []" :key="issue.id" @click="router.push(`/issues/${issue.id}`)">
              <td>{{ issue.projectKey }}</td>
              <td>{{ issue.title }}</td>
              <td><span class="pill">{{ issue.statusLabel }}</span></td>
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
import type { Dashboard } from "../../types/dashboard";

const router = useRouter();
const dashboard = ref<Dashboard>();
const health = ref("");
const error = ref("");

function formatDate(value: string) {
  return new Date(value).toLocaleString("ja-JP");
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
