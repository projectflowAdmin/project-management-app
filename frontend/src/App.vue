<template>
  <RouterView v-if="isPublicLayout" />

  <div v-else class="shell">
    <aside class="sidebar">
      <RouterLink class="brand" to="/dashboard">
        <span class="brand-mark">PF</span>
        <span class="brand-copy">
          <strong>ProjectFlow</strong>
          <small>Project workspace</small>
        </span>
      </RouterLink>

      <div class="sidebar-action">
        <RouterLink class="sidebar-create" to="/projects/new">新規プロジェクト</RouterLink>
      </div>

      <nav class="menu" aria-label="Main navigation">
        <p class="sidebar-label">メニュー</p>
        <RouterLink to="/dashboard">ダッシュボード</RouterLink>
        <RouterLink to="/projects">プロジェクト</RouterLink>
        <RouterLink to="/issues">課題</RouterLink>
      </nav>

      <div class="project-nav">
        <div class="sidebar-label-row">
          <p class="sidebar-label">プロジェクト</p>
          <span>3</span>
        </div>
        <RouterLink class="project-nav-item active" to="/issues">
          <span class="project-icon">PF</span>
          <span>業務進捗管理</span>
        </RouterLink>
        <RouterLink class="project-nav-item" to="/projects">
          <span class="project-icon muted">PJ</span>
          <span>社内改善</span>
        </RouterLink>
        <RouterLink class="project-nav-item" to="/projects">
          <span class="project-icon muted">QA</span>
          <span>品質管理</span>
        </RouterLink>
      </div>

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

    <div class="workspace">
      <header class="topbar">
        <div class="topbar-left">
          <span class="breadcrumb">Workspace / ProjectFlow</span>
        </div>
        <div class="topbar-tools">
          <input class="global-search" type="search" placeholder="キーワードで検索" />
          <button class="icon-button" type="button" aria-label="通知">通知</button>
          <button class="icon-button" type="button" aria-label="ヘルプ">ヘルプ</button>
          <div class="topbar-user">
            <span class="user-avatar small">PF</span>
            <span>管理者</span>
          </div>
        </div>
      </header>

      <main class="content">
        <RouterView />
      </main>
    </div>
  </div>
</template>

<script setup lang="ts">
import { computed } from "vue";
import { RouterLink, RouterView, useRoute } from "vue-router";

const route = useRoute();
const isPublicLayout = computed(() => route.meta.publicLayout === true);
</script>
