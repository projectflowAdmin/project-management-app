<template>
  <div class="login-screen">
    <div class="login-card">
      <p class="section-kicker">Access Portal</p>
      <h2>ProjectFlow にサインイン</h2>
      <p class="muted">プロジェクト管理画面のデモ用ログインページです。</p>

      <form class="login-form" @submit.prevent="login">
        <label>
          <span>メールアドレス</span>
          <input type="email" placeholder="demo@projectflow.local" />
        </label>
        <label>
          <span>パスワード</span>
          <input type="password" placeholder="********" />
        </label>
        <button type="submit">ログイン</button>
      </form>
    </div>
  </div>
</template>

<script setup lang="ts">
import { useRouter } from "vue-router";

const router = useRouter();

function login() {
  router.push("/dashboard");
}
</script>
