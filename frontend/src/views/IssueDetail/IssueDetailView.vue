<template>
  <PageSection title="課題詳細" kicker="Issue Detail">
    <p v-if="error" class="alert">{{ error }}</p>
    <article v-if="issue" class="detail-card">
      <div class="section-line">
        <div>
          <p class="section-kicker">#{{ issue.id }} / {{ issue.projectKey }}</p>
          <h3>{{ issue.title }}</h3>
        </div>
        <RouterLink class="button" :to="`/issues/${issue.id}/edit`">編集</RouterLink>
      </div>
      <dl class="detail-grid">
        <div><dt>プロジェクト名</dt><dd>{{ issue.projectName }}</dd></div>
        <div><dt>プロジェクトキー</dt><dd>{{ issue.projectKey }}</dd></div>
        <div><dt>ステータス</dt><dd>{{ issue.statusLabel }}</dd></div>
        <div><dt>優先度</dt><dd>{{ issue.priorityLabel }}</dd></div>
        <div><dt>担当者名</dt><dd>{{ issue.assigneeName }}</dd></div>
        <div><dt>期限日</dt><dd>{{ issue.dueDate }}</dd></div>
        <div><dt>作成日時</dt><dd>{{ formatDate(issue.createdAt) }}</dd></div>
        <div><dt>更新日時</dt><dd>{{ formatDate(issue.updatedAt) }}</dd></div>
      </dl>
      <p class="detail-text">{{ issue.description }}</p>
    </article>
  </PageSection>
</template>

<script setup lang="ts">
import { onMounted, ref } from "vue";
import { RouterLink } from "vue-router";
import PageSection from "../../components/PageSection.vue";
import { issueApi } from "../../api/issueApi";
import type { Issue } from "../../types/issue";

const props = defineProps<{ id: string }>();
const issue = ref<Issue>();
const error = ref("");

function formatDate(value: string) {
  return new Date(value).toLocaleString("ja-JP");
}

onMounted(async () => {
  try {
    issue.value = await issueApi.findById(Number(props.id));
  } catch (e: any) {
    error.value = e.message ?? "課題の取得に失敗しました。";
  }
});
</script>
