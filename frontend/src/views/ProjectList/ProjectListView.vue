<template>
  <PageSection
    title="プロジェクト一覧"
    kicker="Projects"
    description="進行中のプロジェクトを確認し、基本情報を管理します。"
  >
    <p v-if="error" class="alert">{{ error }}</p>

    <div class="summary-bar">
      <div>
        <p class="section-kicker">Current view</p>
        <div class="summary-meta">
          <span>{{ filteredProjects.length }} 件を表示</span>
          <span>{{ projects.length }} 件中</span>
        </div>
      </div>
      <div class="actions">
        <button class="button secondary" type="button" @click="query = ''">リセット</button>
        <RouterLink class="button" to="/projects/new">新規作成</RouterLink>
      </div>
    </div>

    <div class="page-tabs">
      <button class="tab-button active" type="button">すべて</button>
      <button class="tab-button" type="button">進行中</button>
      <button class="tab-button" type="button">更新順</button>
    </div>

    <div class="toolbar">
      <div class="toolbar-group">
        <input v-model="query" class="search-input" type="search" placeholder="キー・名称・説明で検索" />
      </div>
      <div class="view-switcher">
        <button class="view-button active" type="button">一覧</button>
        <button class="view-button" type="button">ボード</button>
      </div>
    </div>

    <div class="table-card">
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>キー</th>
            <th>名称</th>
            <th>説明</th>
            <th>更新日時</th>
            <th></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="project in filteredProjects" :key="project.id" class="status-line status-progress">
            <td>#{{ project.id }}</td>
            <td><span class="badge status-review">{{ project.projectKey }}</span></td>
            <td>
              <div class="row-title">
                <strong>{{ project.name }}</strong>
                <small>Project workspace</small>
              </div>
            </td>
            <td>{{ project.description || "-" }}</td>
            <td>{{ formatDate(project.updatedAt) }}</td>
            <td class="actions">
              <RouterLink class="button secondary" :to="`/projects/${project.id}/edit`">編集</RouterLink>
              <button class="button danger" type="button" @click="remove(project.id)">削除</button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </PageSection>
</template>

<script setup lang="ts">
import { computed, onMounted, ref } from "vue";
import { RouterLink } from "vue-router";
import PageSection from "../../components/PageSection.vue";
import { projectApi } from "../../api/projectApi";
import type { Project } from "../../types/project";

const projects = ref<Project[]>([]);
const error = ref("");
const query = ref("");

const filteredProjects = computed(() => {
  const keyword = query.value.trim().toLowerCase();
  if (!keyword) return projects.value;

  return projects.value.filter((project) =>
    [project.projectKey, project.name, project.description]
      .filter(Boolean)
      .some((value) => String(value).toLowerCase().includes(keyword))
  );
});

function formatDate(value: string) {
  return new Date(value).toLocaleString("ja-JP");
}

async function load() {
  projects.value = await projectApi.findAll();
}

async function remove(id: number) {
  if (!confirm("このプロジェクトを削除しますか？")) return;
  try {
    await projectApi.delete(id);
    await load();
  } catch (e: any) {
    error.value = e.message ?? "削除に失敗しました。";
  }
}

onMounted(load);
</script>
