<template>
  <PageSection
    title="課題一覧"
    kicker="Issues"
    description="優先度と進捗に応じて課題を一覧で確認できます。"
  >
    <div class="issue-list">
      <RouterLink
        v-for="issue in issues"
        :key="issue.id"
        class="issue-card"
        :to="`/issues/${issue.id}`"
      >
        <div class="issue-card-row">
          <strong>{{ issue.key }}</strong>
          <span class="pill">{{ issue.status }}</span>
        </div>
        <h3>{{ issue.title }}</h3>
        <p>{{ issue.project }} / {{ issue.priority }} / {{ issue.assignee }}</p>
      </RouterLink>
    </div>
  </PageSection>
</template>

<script setup lang="ts">
import { RouterLink } from "vue-router";
import PageSection from "../../components/PageSection.vue";

const issues = [
  { id: 1, key: "ISS-101", title: "ログイン画面の入力検証を追加", status: "対応中", project: "Alpha Lane", priority: "High", assignee: "Airi Kato" },
  { id: 2, key: "ISS-102", title: "課題詳細 API の仕様確認", status: "未着手", project: "Nova Hub", priority: "Medium", assignee: "Ren Ito" },
  { id: 3, key: "ISS-103", title: "ダッシュボード指標の表示改善", status: "レビュー中", project: "Sprint Canvas", priority: "Low", assignee: "Mio Sato" }
];
</script>
