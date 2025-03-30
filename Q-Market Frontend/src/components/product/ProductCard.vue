<script setup>
import { computed } from 'vue'
import { useRouter } from 'vue-router'

const props = defineProps({
  product: {
    type: Object,
    required: true
  },
  showActionButtons: {
    type: Boolean,
    default: false
  }
})

const router = useRouter()

const discountPercent = computed(() => {
  if (!props.product.originalPrice || props.product.originalPrice <= props.product.price) {
    return null
  }
  return Math.round(100 - (props.product.price / props.product.originalPrice * 100))
})

const goToDetail = () => {
  router.push(`/product/${props.product.id}`)
}

const addToCart = (event) => {
  event.stopPropagation()
  // 这里应该调用添加到购物车的方法，通常通过Pinia store实现
  ElMessage({
    message: '商品已添加到购物车',
    type: 'success'
  })
}

const addToWishlist = (event) => {
  event.stopPropagation()
  // 这里应该调用添加到收藏夹的方法
  ElMessage({
    message: '商品已添加到收藏夹',
    type: 'success'
  })
}
</script>

<template>
  <div class="product-card" @click="goToDetail">
    <div class="product-image">
      <img :src="product.image" :alt="product.name" />
      
      <div class="product-tags">
        <span v-if="product.isNew" class="tag new-tag">新品</span>
        <span v-if="discountPercent" class="tag discount-tag">-{{ discountPercent }}%</span>
      </div>
      
      <div class="product-actions">
        <el-button
          circle
          size="small"
          type="primary"
          @click.stop="addToCart"
          title="加入购物车"
        >
          <el-icon><ShoppingCart /></el-icon>
        </el-button>
        
        <el-button
          circle
          size="small"
          @click.stop="addToWishlist"
          title="加入收藏"
        >
          <el-icon><Star /></el-icon>
        </el-button>
      </div>
    </div>
    
    <div class="product-info">
      <h3 class="product-name">{{ product.name }}</h3>
      <div class="product-category">{{ product.category }}</div>
      
      <div class="product-price">
        <span class="current-price">¥{{ product.price.toFixed(2) }}</span>
        <span v-if="product.originalPrice" class="original-price">¥{{ product.originalPrice.toFixed(2) }}</span>
      </div>
      
      <div class="product-rating">
        <el-rate
          v-model="product.rating"
          disabled
          text-color="#FF9900"
          :score-template="product.reviewCount ? `${product.reviewCount}条评价` : '暂无评价'"
        />
        <span class="review-count">({{ product.reviewCount || 0 }})</span>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
@import '@/assets/css/variables.scss';

.product-card {
  background-color: $bg-white;
  border-radius: $border-radius;
  overflow: hidden;
  box-shadow: $box-shadow;
  transition: all $transition-time;
  cursor: pointer;
  height: 100%;
  display: flex;
  flex-direction: column;
  
  &:hover {
    transform: translateY(-5px);
    box-shadow: $box-shadow-hover;
    
    .product-actions {
      opacity: 1;
      transform: translateY(0);
    }
    
    .product-image img {
      transform: scale(1.05);
    }
  }
  
  .product-image {
    position: relative;
    width: 100%;
    aspect-ratio: 1;
    overflow: hidden;
    
    img {
      width: 100%;
      height: 100%;
      object-fit: cover;
      transition: transform $transition-time;
    }
    
    .product-tags {
      position: absolute;
      top: $spacing-sm;
      left: $spacing-sm;
      display: flex;
      flex-direction: column;
      gap: $spacing-xs;
      z-index: 1;
      
      .tag {
        padding: 4px 8px;
        border-radius: $border-radius-sm;
        font-size: 12px;
        font-weight: 600;
      }
      
      .new-tag {
        background-color: $primary-color;
        color: white;
      }
      
      .discount-tag {
        background-color: $danger-color;
        color: white;
      }
    }
    
    .product-actions {
      position: absolute;
      bottom: $spacing-md;
      left: 0;
      right: 0;
      display: flex;
      justify-content: center;
      gap: $spacing-sm;
      opacity: 0;
      transform: translateY(10px);
      transition: all $transition-time;
      z-index: 1;
    }
  }
  
  .product-info {
    padding: $spacing-md;
    flex-grow: 1;
    display: flex;
    flex-direction: column;
    
    .product-name {
      font-size: 16px;
      font-weight: 600;
      margin-bottom: $spacing-xs;
      color: $text-color;
      display: -webkit-box;
      -webkit-line-clamp: 2;
      -webkit-box-orient: vertical;
      overflow: hidden;
    }
    
    .product-category {
      font-size: 14px;
      color: $text-lighter;
      margin-bottom: $spacing-sm;
    }
    
    .product-price {
      margin-top: auto;
      margin-bottom: $spacing-sm;
      
      .current-price {
        font-size: 18px;
        font-weight: 600;
        color: $danger-color;
      }
      
      .original-price {
        margin-left: $spacing-sm;
        font-size: 14px;
        color: $text-lighter;
        text-decoration: line-through;
      }
    }
    
    .product-rating {
      display: flex;
      align-items: center;
      
      .review-count {
        margin-left: $spacing-xs;
        font-size: 12px;
        color: $text-lighter;
      }
    }
  }
}
</style> 