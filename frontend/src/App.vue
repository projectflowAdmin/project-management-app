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
          <!-- ログアウトボタン -->
          <button class="button danger small" type="button" @click="openDialog" style="margin-left: auto;">
            ログアウト
          </button>
        </div>
      </header>

      <main class="content">
        <RouterView />
      </main>
    </div>
  </div>

  <!-- モーダル -->
  <dialog ref="logoutDialog" class="modal-dialog">
    <div class="dialog-content">
      <h3>ログアウトの確認</h3>
      <p>アカウントからログアウトしますか？</p>
      <div class="dialog-actions">
        <button class="button secondary" type="button" @click="closeDialog">キャンセル</button>
        <button class="button danger" type="button" @click="handleLogout">ログアウト</button>
      </div>
    </div>
  </dialog>
</template>

<script setup lang="ts">
import { computed, ref } from "vue";
import { RouterLink, RouterView, useRoute, useRouter } from "vue-router";

const route = useRoute();
const router = useRouter();

const isPublicLayout = computed(() => route.meta.publicLayout === true);

const logoutDialog = ref<HTMLDialogElement | null>(null);

// ダイアログを開く
function openDialog() {
  logoutDialog.value?.showModal();
}

// ダイアログを閉じる
function closeDialog() {
  logoutDialog.value?.close();
}

// ログアウトを実行してログイン画面へ
function handleLogout() {
  closeDialog();
  //すべてのデータ(フロント側のログイン状態)をクリアする
  localStorage.clear()
  router.push("/login");
}
</script>

<style scoped>
.modal-dialog {
  border: none;
  border-radius: 8px;
  padding: 24px;
  box-shadow: 0 4px 16px rgba(0, 0, 0, 0.2);
  max-width: 400px;
  width: 100%;
}

.modal-dialog::backdrop {
  background-color: rgba(0, 0, 0, 0.5);
}

.dialog-content h3 {
  margin-top: 0;
}

.dialog-actions {
  display: flex;
  justify-content: flex-end;
  gap: 12px;
  margin-top: 24px;
}
</style>