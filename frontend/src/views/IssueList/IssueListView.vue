<template>
  <PageSection
    title="課題一覧"
    kicker="Issues"
    description="プロジェクトやステータスで絞り込みながら、課題の進捗を確認します。"
  >
    <p v-if="error" class="alert">{{ error }}</p>

    <div class="summary-bar">
      <div>
        <p class="section-kicker">Current view</p>
        <div class="summary-meta">
          <span>{{ filteredIssues.length }} 件を表示</span>
          <span>{{ issues.length }} 件中</span>
          <span>未完了 {{ openIssueCount }} 件</span>
        </div>
      </div>
      <div class="actions">
        <button class="button secondary" type="button" @click="resetFilters">リセット</button>
        <RouterLink class="button" to="/issues/new">新規作成</RouterLink>
      </div>
    </div>

    <div class="page-tabs">
      <button class="tab-button active" type="button">課題</button>
      <button class="tab-button" type="button">担当中</button>
      <button class="tab-button" type="button">期限あり</button>
      <button class="tab-button" type="button">フォロー中</button>
    </div>

    <div class="toolbar">
      <div class="toolbar-group">
        <input v-model="query" class="search-input" type="search" placeholder="課題名・担当者・プロジェクトで検索" />
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
      </div>
      <div class="view-switcher" aria-label="View switcher">
        <button class="view-button" :class="{ active: viewMode === 'list' }" type="button" @click="viewMode = 'list'">一覧</button>
        <button class="view-button" :class="{ active: viewMode === 'board' }" type="button" @click="viewMode = 'board'">ボード</button>
        <button class="view-button" :class="{ active: viewMode === 'timeline' }" type="button" @click="viewMode = 'timeline'">ガント</button>
      </div>
    </div>

    <div v-if="viewMode === 'list'" class="table-card">
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
          <tr v-for="issue in filteredIssues" :key="issue.id" :class="['status-line', statusClass(issue.status)]">
            <td>#{{ issue.id }}</td>
            <td>{{ issue.projectKey }}</td>
            <td>
              <div class="row-title">
                <RouterLink class="text-link" :to="`/issues/${issue.id}`">{{ issue.title }}</RouterLink>
                <small>{{ issue.projectName }}</small>
              </div>
            </td>
            <td><span :class="['badge', statusClass(issue.status)]">{{ issue.statusLabel }}</span></td>
            <td><span :class="['badge', priorityClass(issue.priority)]">{{ issue.priorityLabel }}</span></td>
            <td>{{ issue.assigneeName || "未設定" }}</td>
            <td>{{ issue.dueDate || "-" }}</td>
            <td>{{ formatDate(issue.updatedAt) }}</td>
            <td class="actions">
              <RouterLink class="button secondary" :to="`/issues/${issue.id}/edit`">編集</RouterLink>
              <button class="button danger" type="button" @click="remove(issue.id)">削除</button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>

    <div v-else-if="viewMode === 'board'" class="kanban-board">
      <section v-for="column in statusColumns" :key="column.status" class="kanban-column">
        <div class="kanban-heading">
          <span>{{ column.label }}</span>
          <span>{{ column.items.length }}</span>
        </div>
        <RouterLink
          v-for="issue in column.items"
          :key="issue.id"
          :to="`/issues/${issue.id}`"
          :class="['task-card', statusClass(issue.status)]"
        >
          <strong>{{ issue.title }}</strong>
          <div class="task-card-meta">
            <span>#{{ issue.id }} {{ issue.projectKey }}</span>
            <span>{{ issue.assigneeName || "未設定" }}</span>
          </div>
          <div class="actions">
            <span :class="['badge', priorityClass(issue.priority)]">{{ issue.priorityLabel }}</span>
            <span class="muted">{{ issue.dueDate || "期限なし" }}</span>
          </div>
        </RouterLink>
      </section>
    </div>

    <div v-else class="timeline-board">
      <div class="timeline-header">
        <div>課題</div>
        <div>開始日</div>
        <div>期限日</div>
        <div>タイムライン</div>
      </div>
      <div v-for="(issue, index) in filteredIssues" :key="issue.id" class="timeline-row">
        <div class="row-title">
          <RouterLink class="text-link" :to="`/issues/${issue.id}`">#{{ issue.id }} {{ issue.title }}</RouterLink>
          <small>{{ issue.projectKey }} / {{ issue.assigneeName || "未設定" }}</small>
        </div>
        <div>{{ formatDateOnly(issue.createdAt) }}</div>
        <div>{{ issue.dueDate || "-" }}</div>
        <div class="timeline-track">
          <span
            class="timeline-bar"
            :class="timelineClass(issue.status)"
            :style="{ left: `${(index % 5) * 58}px`, width: `${150 + (index % 4) * 42}px` }"
          ></span>
        </div>
      </div>
    </div>
  </PageSection>
</template>

<script setup lang="ts">
import { computed, onMounted, reactive, ref } from "vue";
import { RouterLink } from "vue-router";
import PageSection from "../../components/PageSection.vue";
import { issueApi } from "../../api/issueApi";
import { projectApi } from "../../api/projectApi";
import type { Issue, IssuePriority, IssueStatus } from "../../types/issue";
import type { Project } from "../../types/project";

type ViewMode = "list" | "board" | "timeline";

const issues = ref<Issue[]>([]);
const projects = ref<Project[]>([]);
const error = ref("");
const query = ref("");
const viewMode = ref<ViewMode>("list");
const filters = reactive<{ projectId: string; status: "" | IssueStatus }>({ projectId: "", status: "" });

const filteredIssues = computed(() => {
  const keyword = query.value.trim().toLowerCase();
  if (!keyword) return issues.value;

  return issues.value.filter((issue) =>
    [issue.title, issue.projectKey, issue.projectName, issue.assigneeName, issue.statusLabel, issue.priorityLabel]
      .filter(Boolean)
      .some((value) => String(value).toLowerCase().includes(keyword))
  );
});

const openIssueCount = computed(() => filteredIssues.value.filter((issue) => issue.status !== "DONE").length);

const statusColumns = computed(() =>
  [
    { status: "TODO" as IssueStatus, label: "未着手" },
    { status: "IN_PROGRESS" as IssueStatus, label: "対応中" },
    { status: "REVIEW" as IssueStatus, label: "レビュー中" },
    { status: "DONE" as IssueStatus, label: "完了" }
  ].map((column) => ({
    ...column,
    items: filteredIssues.value.filter((issue) => issue.status === column.status)
  }))
);

function formatDate(value: string) {
  return new Date(value).toLocaleString("ja-JP");
}

function formatDateOnly(value: string) {
  return new Date(value).toLocaleDateString("ja-JP");
}

function statusClass(status: IssueStatus) {
  return {
    TODO: "status-todo",
    IN_PROGRESS: "status-progress",
    REVIEW: "status-review",
    DONE: "status-done"
  }[status];
}

function priorityClass(priority: IssuePriority) {
  return {
    LOW: "priority-low",
    MEDIUM: "priority-medium",
    HIGH: "priority-high"
  }[priority];
}

function timelineClass(status: IssueStatus) {
  return {
    TODO: "",
    IN_PROGRESS: "progress",
    REVIEW: "review",
    DONE: "done"
  }[status];
}

async function load() {
  issues.value = await issueApi.findAll({
    projectId: filters.projectId ? Number(filters.projectId) : undefined,
    status: filters.status || undefined
  });
}

async function resetFilters() {
  query.value = "";
  filters.projectId = "";
  filters.status = "";
  await load();
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
