<script setup>
import { useRouter } from 'vue-router'

const router = useRouter()

const goHome = () => {
  router.push('/')
}

const goBack = () => {
  router.go(-1)
}
</script>

<template>
  <div class="not-found-page">
    <div class="container">
      <div class="not-found-content">
        <div class="error-code">404</div>
        <h1 class="error-title">页面未找到</h1>
        <p class="error-message">抱歉，您访问的页面不存在或已被移除。</p>
        <div class="action-buttons">
          <el-button type="primary" @click="goHome">回到首页</el-button>
          <el-button @click="goBack">返回上一页</el-button>
        </div>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
@import '@/assets/css/variables.scss';

.not-found-page {
  min-height: calc(100vh - 60px);
  display: flex;
  align-items: center;
  justify-content: center;
  padding: $spacing-xl 0;
  background-color: $bg-color;
}

.not-found-content {
  text-align: center;
  max-width: 600px;
  margin: 0 auto;
  padding: $spacing-xl;
  background-color: $bg-white;
  border-radius: $border-radius;
  box-shadow: $box-shadow;
}

.error-code {
  font-size: 120px;
  font-weight: 700;
  color: $primary-color;
  line-height: 1;
  margin-bottom: $spacing-lg;
  text-shadow: 2px 2px 8px rgba($primary-color, 0.3);
}

.error-title {
  font-size: 32px;
  font-weight: 600;
  margin-bottom: $spacing-md;
  color: $text-color;
}

.error-message {
  font-size: 16px;
  color: $text-light;
  margin-bottom: $spacing-xl;
}

.action-buttons {
  display: flex;
  justify-content: center;
  gap: $spacing-md;
}
</style> 