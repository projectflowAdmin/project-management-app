<template>
  <PageSection :title="isEdit ? 'プロジェクト編集' : 'プロジェクト登録'" kicker="Project Form">
    <form class="form-panel" @submit.prevent="save">
      <p v-if="error" class="alert">{{ error }}</p>
      <label>
        プロジェクトキー
        <input v-model.trim="form.projectKey" required maxlength="20" pattern="[A-Za-z0-9_-]+" />
      </label>
      <label>
        プロジェクト名
        <input v-model.trim="form.name" required maxlength="100" />
      </label>
      <label>
        説明
        <textarea v-model.trim="form.description" maxlength="2000" rows="6"></textarea>
      </label>
      <div class="actions">
        <button class="button" type="submit">保存</button>
        <RouterLink class="button secondary" to="/projects">戻る</RouterLink>
      </div>
    </form>
  </PageSection>
</template>

<script setup lang="ts">
import { computed, onMounted, reactive, ref } from "vue";
import { RouterLink, useRouter } from "vue-router";
import PageSection from "../../components/PageSection.vue";
import { projectApi } from "../../api/projectApi";
import type { ProjectRequest } from "../../types/project";

const props = defineProps<{ id?: string }>();
const router = useRouter();
const isEdit = computed(() => Boolean(props.id));
const error = ref("");
const form = reactive<ProjectRequest>({ projectKey: "", name: "", description: "" });

onMounted(async () => {
  if (!props.id) return;
  const project = await projectApi.findById(Number(props.id));
  form.projectKey = project.projectKey;
  form.name = project.name;
  form.description = project.description ?? "";
});

async function save() {
  try {
    if (props.id) {
      await projectApi.update(Number(props.id), form);
    } else {
      await projectApi.create(form);
    }
    await router.push("/projects");
  } catch (e: any) {
    error.value = e.message ?? "保存に失敗しました。";
  }
}
</script>
