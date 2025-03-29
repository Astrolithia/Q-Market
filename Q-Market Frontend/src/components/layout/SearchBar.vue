<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()
const searchQuery = ref('')
const isLoading = ref(false)

const handleSearch = () => {
  if (!searchQuery.value.trim()) return
  
  isLoading.value = true
  // 模拟搜索请求延迟
  setTimeout(() => {
    router.push({
      path: '/products',
      query: { q: searchQuery.value }
    })
    isLoading.value = false
  }, 300)
}
</script>

<template>
  <div class="search-bar">
    <el-input
      v-model="searchQuery"
      placeholder="搜索商品..."
      @keyup.enter="handleSearch"
      clearable
    >
      <template #prefix>
        <el-icon><Search /></el-icon>
      </template>
      <template #append>
        <el-button :icon="Search" @click="handleSearch" :loading="isLoading" />
      </template>
    </el-input>
  </div>
</template>

<style lang="scss" scoped>
@import '@/assets/css/variables.scss';

.search-bar {
  width: 100%;
  
  :deep(.el-input__inner) {
    border-radius: 20px 0 0 20px;
    transition: all $transition-time;
    
    &:focus {
      box-shadow: 0 0 0 2px rgba($primary-color, 0.2);
    }
  }
  
  :deep(.el-input-group__append) {
    border-radius: 0 20px 20px 0;
  }
}
</style> 