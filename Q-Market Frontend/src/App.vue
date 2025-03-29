<script setup>
import { RouterView } from 'vue-router'
import AppHeader from '@/components/layout/AppHeader.vue'
import AppFooter from '@/components/layout/AppFooter.vue'
</script>

<template>
  <div id="app">
    <AppHeader />
    <main class="main-content">
      <RouterView />
    </main>
    <AppFooter />
  </div>
</template>

<style lang="scss">
@import '@/assets/css/variables.scss';

.main-content {
  flex: 1;
  padding-top: 60px; // 头部导航高度
}
</style> 