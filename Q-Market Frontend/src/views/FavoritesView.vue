<script setup>
import { ref, onMounted, computed } from 'vue'
import { useRouter } from 'vue-router'
import { useFavoritesStore } from '@/stores/favorites'
import { ElMessage, ElMessageBox } from 'element-plus'
import ProductCard from '@/components/product/ProductCard.vue'

const router = useRouter()
const favoritesStore = useFavoritesStore()

// 加载状态
const loading = ref(true)

// 排序方式
const sortOptions = [
  { label: '添加时间：最新', value: 'time-desc' },
  { label: '添加时间：最早', value: 'time-asc' },
  { label: '价格：从高到低', value: 'price-desc' },
  { label: '价格：从低到高', value: 'price-asc' }
]
const currentSort = ref('time-desc')

// 排序后的收藏列表
const sortedFavorites = computed(() => {
  const list = [...favoritesStore.favorites]
  
  switch (currentSort.value) {
    case 'time-desc':
      return list.sort((a, b) => new Date(b.addTime) - new Date(a.addTime))
    case 'time-asc':
      return list.sort((a, b) => new Date(a.addTime) - new Date(b.addTime))
    case 'price-desc':
      return list.sort((a, b) => b.price - a.price)
    case 'price-asc':
      return list.sort((a, b) => a.price - b.price)
    default:
      return list
  }
})

// 移除收藏
const removeItem = (productId) => {
  ElMessageBox.confirm('确定要移除这个收藏吗?', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(() => {
    favoritesStore.removeFromFavorites(productId)
    ElMessage({
      type: 'success',
      message: '已从收藏中移除'
    })
  }).catch(() => {})
}

// 跳转到商品详情
const goToDetail = (productId) => {
  router.push(`/product/${productId}`)
}

// 初始化
onMounted(() => {
  loading.value = false
})
</script>

<template>
  <div class="favorites-page">
    <div class="container">
      <h1 class="page-title">我的收藏</h1>
      
      <div v-if="loading" class="loading-container">
        <el-skeleton :rows="5" animated />
      </div>
      
      <div v-else-if="favoritesStore.favorites.length === 0" class="empty-favorites">
        <el-empty description="您还没有收藏任何商品">
          <el-button type="primary" @click="router.push('/products')">去浏览商品</el-button>
        </el-empty>
      </div>
      
      <div v-else class="favorites-container">
        <div class="favorites-header">
          <div class="total-count">
            共 <span>{{ favoritesStore.totalFavorites }}</span> 件收藏商品
          </div>
          
          <div class="sort-options">
            <span>排序：</span>
            <el-radio-group v-model="currentSort" size="small">
              <el-radio-button v-for="option in sortOptions" :key="option.value" :label="option.value">
                {{ option.label }}
              </el-radio-button>
            </el-radio-group>
          </div>
        </div>
        
        <el-row :gutter="20">
          <el-col :xs="12" :sm="8" :md="6" v-for="item in sortedFavorites" :key="item.id">
            <div class="favorite-item">
              <ProductCard :product="item" />
              <div class="favorite-actions">
                <el-button type="primary" @click="goToDetail(item.id)">查看详情</el-button>
                <el-button @click="removeItem(item.id)" icon="Delete">移除</el-button>
              </div>
            </div>
          </el-col>
        </el-row>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
@import '@/assets/css/variables.scss';

.favorites-page {
  padding: $spacing-xl 0;
}

.page-title {
  font-size: 28px;
  font-weight: 600;
  margin-bottom: $spacing-xl;
  color: $text-color;
}

.loading-container {
  padding: $spacing-xl;
  background-color: $bg-white;
  border-radius: $border-radius;
  box-shadow: $box-shadow;
}

.empty-favorites {
  display: flex;
  justify-content: center;
  padding: $spacing-xl;
  background-color: $bg-white;
  border-radius: $border-radius;
  box-shadow: $box-shadow;
}

.favorites-container {
  margin-bottom: $spacing-xl;
}

.favorites-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: $bg-white;
  border-radius: $border-radius;
  box-shadow: $box-shadow;
  padding: $spacing-md;
  margin-bottom: $spacing-lg;
  
  @media (max-width: $breakpoint-sm) {
    flex-direction: column;
    align-items: flex-start;
    gap: $spacing-md;
  }
  
  .total-count {
    color: $text-light;
    
    span {
      color: $primary-color;
      font-weight: 600;
    }
  }
  
  .sort-options {
    display: flex;
    align-items: center;
    
    span {
      color: $text-light;
      margin-right: $spacing-sm;
    }
  }
}

.favorite-item {
  margin-bottom: $spacing-lg;
  
  .favorite-actions {
    display: flex;
    justify-content: space-between;
    margin-top: $spacing-sm;
    gap: $spacing-sm;
    
    .el-button {
      flex: 1;
    }
  }
}
</style> 