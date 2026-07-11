<template>
  <PageSection
    title="プロジェクト一覧"
    kicker="Projects"
    description="サンプルプロジェクトの状態と担当者を確認できます。"
  >
    <div class="table-card">
      <table>
        <thead>
          <tr>
            <th>コード</th>
            <th>名称</th>
            <th>状態</th>
            <th>担当</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="project in projects" :key="project.code">
            <td>{{ project.code }}</td>
            <td>{{ project.name }}</td>
            <td><span class="pill">{{ project.status }}</span></td>
            <td>{{ project.owner }}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </PageSection>
</template>

<script setup lang="ts">
import PageSection from "../../components/PageSection.vue";

const projects = [
  { code: "PJF-101", name: "Alpha Lane", status: "進行中", owner: "Airi Kato" },
  { code: "PJF-102", name: "Nova Hub", status: "計画中", owner: "Ren Ito" },
  { code: "PJF-103", name: "Sprint Canvas", status: "進行中", owner: "Mio Sato" }
];
</script>
