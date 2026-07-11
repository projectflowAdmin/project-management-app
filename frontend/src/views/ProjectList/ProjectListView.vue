<template>
  <PageSection
    title="プロジェクト一覧"
    kicker="Projects"
    description="進行中のプロジェクトを確認し、基本情報を管理します。"
  >
    <p v-if="error" class="alert">{{ error }}</p>
    <div class="toolbar">
      <RouterLink class="button" to="/projects/new">新規作成</RouterLink>
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
          <tr v-for="project in projects" :key="project.id">
            <td>{{ project.id }}</td>
            <td><strong>{{ project.projectKey }}</strong></td>
            <td>{{ project.name }}</td>
            <td>{{ project.description }}</td>
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
import { onMounted, ref } from "vue";
import { RouterLink } from "vue-router";
import PageSection from "../../components/PageSection.vue";
import { projectApi } from "../../api/projectApi";
import type { Project } from "../../types/project";

const projects = ref<Project[]>([]);
const error = ref("");

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
