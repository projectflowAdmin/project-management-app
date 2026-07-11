<template>
  <RouterView v-if="isPublicLayout" />

  <div v-else class="shell">
    <aside class="sidebar">
      <RouterLink class="brand" to="/dashboard">
        <span class="brand-mark">PF</span>
        <span>
          <strong>ProjectFlow</strong>
          <small>Team Workspace</small>
        </span>
      </RouterLink>

      <nav class="menu">
        <RouterLink to="/dashboard">ダッシュボード</RouterLink>
        <RouterLink to="/projects">プロジェクト</RouterLink>
        <RouterLink to="/issues">課題</RouterLink>
      </nav>

      <div class="sidebar-footer">
        <div class="user-info-block">
          <div class="user-avatar">PF</div>
          <div class="user-meta">
            <span class="user-name">ProjectFlow</span>
            <span class="user-role">v0.1.0</span>
          </div>
        </div>
      </div>
    </aside>

    <main class="content">
      <RouterView />
    </main>
  </div>
</template>

<script setup lang="ts">
import { computed } from "vue";
import { RouterLink, RouterView, useRoute } from "vue-router";

const route = useRoute();
const isPublicLayout = computed(() => route.meta.publicLayout === true);
</script>
