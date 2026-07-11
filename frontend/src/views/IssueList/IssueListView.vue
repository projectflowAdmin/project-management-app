<template>
  <PageSection
    title="課題一覧"
    kicker="Issues"
    description="プロジェクトやステータスで絞り込みながら、課題の進捗を確認します。"
  >
    <p v-if="error" class="alert">{{ error }}</p>
    <div class="toolbar">
      <select v-model="filters.projectId" @change="load">
        <option value="">すべてのプロジェクト</option>
        <option v-for="project in projects" :key="project.id" :value="project.id">{{ project.projectKey }}</option>
      </select>
      <select v-model="filters.status" @change="load">
        <option value="">すべてのステータス</option>
        <option value="TODO">未着手</option>
        <option value="IN_PROGRESS">対応中</option>
        <option value="REVIEW">レビュー中</option>
        <option value="DONE">完了</option>
      </select>
      <RouterLink class="button" to="/issues/new">新規作成</RouterLink>
    </div>
    <div class="table-card">
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>プロジェクト</th>
            <th>件名</th>
            <th>ステータス</th>
            <th>優先度</th>
            <th>担当者</th>
            <th>期限日</th>
            <th>更新日時</th>
            <th></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="issue in issues" :key="issue.id">
            <td>{{ issue.id }}</td>
            <td>{{ issue.projectKey }}</td>
            <td><RouterLink class="text-link" :to="`/issues/${issue.id}`">{{ issue.title }}</RouterLink></td>
            <td><span class="pill">{{ issue.statusLabel }}</span></td>
            <td>{{ issue.priorityLabel }}</td>
            <td>{{ issue.assigneeName }}</td>
            <td>{{ issue.dueDate }}</td>
            <td>{{ formatDate(issue.updatedAt) }}</td>
            <td class="actions">
              <RouterLink class="button secondary" :to="`/issues/${issue.id}/edit`">編集</RouterLink>
              <button class="button danger" type="button" @click="remove(issue.id)">削除</button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </PageSection>
</template>

<script setup lang="ts">
import { onMounted, reactive, ref } from "vue";
import { RouterLink } from "vue-router";
import PageSection from "../../components/PageSection.vue";
import { issueApi } from "../../api/issueApi";
import { projectApi } from "../../api/projectApi";
import type { Issue, IssueStatus } from "../../types/issue";
import type { Project } from "../../types/project";

const issues = ref<Issue[]>([]);
const projects = ref<Project[]>([]);
const error = ref("");
const filters = reactive<{ projectId: string; status: "" | IssueStatus }>({ projectId: "", status: "" });

function formatDate(value: string) {
  return new Date(value).toLocaleString("ja-JP");
}

async function load() {
  issues.value = await issueApi.findAll({
    projectId: filters.projectId ? Number(filters.projectId) : undefined,
    status: filters.status || undefined
  });
}

async function remove(id: number) {
  if (!confirm("この課題を削除しますか？")) return;
  try {
    await issueApi.delete(id);
    await load();
  } catch (e: any) {
    error.value = e.message ?? "削除に失敗しました。";
  }
}

onMounted(async () => {
  projects.value = await projectApi.findAll();
  await load();
});
</script>
