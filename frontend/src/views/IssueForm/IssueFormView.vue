<template>
  <PageSection :title="isEdit ? '課題編集' : '課題登録'" kicker="Issue Form">
    <form class="form-panel" @submit.prevent="save">
      <p v-if="error" class="alert">{{ error }}</p>
      <label>
        プロジェクト
        <select v-model.number="form.projectId" required>
          <option disabled :value="0">選択してください</option>
          <option v-for="project in projects" :key="project.id" :value="project.id">{{ project.projectKey }} / {{ project.name }}</option>
        </select>
      </label>
      <label>
        件名
        <input v-model.trim="form.title" required maxlength="200" />
      </label>
      <label>
        説明
        <textarea v-model.trim="form.description" maxlength="5000" rows="6"></textarea>
      </label>
      <div class="form-grid">
        <label>
          ステータス
          <select v-model="form.status">
            <option value="TODO">未着手</option>
            <option value="IN_PROGRESS">対応中</option>
            <option value="REVIEW">レビュー中</option>
            <option value="DONE">完了</option>
          </select>
        </label>
        <label>
          優先度
          <select v-model="form.priority">
            <option value="LOW">低</option>
            <option value="MEDIUM">中</option>
            <option value="HIGH">高</option>
          </select>
        </label>
      </div>
      <div class="form-grid">
        <label>
          担当者名
          <input v-model.trim="form.assigneeName" maxlength="100" />
        </label>
        <label>
          期限日
          <input v-model="form.dueDate" type="date" />
        </label>
      </div>
      <div class="actions">
        <button class="button" type="submit">保存</button>
        <RouterLink class="button secondary" to="/issues">戻る</RouterLink>
      </div>
    </form>
  </PageSection>
</template>

<script setup lang="ts">
import { computed, onMounted, reactive, ref } from "vue";
import { RouterLink, useRouter } from "vue-router";
import PageSection from "../../components/PageSection.vue";
import { issueApi } from "../../api/issueApi";
import { projectApi } from "../../api/projectApi";
import type { IssueRequest } from "../../types/issue";
import type { Project } from "../../types/project";

const props = defineProps<{ id?: string }>();
const router = useRouter();
const isEdit = computed(() => Boolean(props.id));
const projects = ref<Project[]>([]);
const error = ref("");
const form = reactive<IssueRequest>({
  projectId: 0,
  title: "",
  description: "",
  status: "TODO",
  priority: "MEDIUM",
  assigneeName: "",
  dueDate: null
});

onMounted(async () => {
  projects.value = await projectApi.findAll();
  if (!props.id) return;
  const issue = await issueApi.findById(Number(props.id));
  form.projectId = issue.projectId;
  form.title = issue.title;
  form.description = issue.description ?? "";
  form.status = issue.status;
  form.priority = issue.priority;
  form.assigneeName = issue.assigneeName ?? "";
  form.dueDate = issue.dueDate;
});

async function save() {
  try {
    const body = { ...form, dueDate: form.dueDate || null };
    if (props.id) {
      await issueApi.update(Number(props.id), body);
    } else {
      await issueApi.create(body);
    }
    await router.push("/issues");
  } catch (e: any) {
    error.value = e.message ?? "保存に失敗しました。";
  }
}
</script>
