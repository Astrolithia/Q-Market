<template>
  <div class="cart-page">
    <div class="container">
      <h1 class="page-title">购物车</h1>
      
      <div v-if="cartItems.length === 0" class="empty-cart">
        <el-empty description="您的购物车还是空的">
          <el-button type="primary" @click="continueShopping">去购物</el-button>
        </el-empty>
      </div>
      
      <div v-else class="cart-container">
        <div class="cart-table">
          <!-- 购物车表头 -->
          <div class="cart-header">
            <div class="cart-cell checkbox-cell">
              <el-checkbox v-model="allSelected" />
            </div>
            <div class="cart-cell product-cell">商品信息</div>
            <div class="cart-cell price-cell">单价</div>
            <div class="cart-cell quantity-cell">数量</div>
            <div class="cart-cell subtotal-cell">小计</div>
            <div class="cart-cell action-cell">操作</div>
          </div>
          
          <!-- 购物车商品列表 -->
          <div class="cart-body">
            <div class="cart-row" v-for="item in cartItems" :key="item.id">
              <div class="cart-cell checkbox-cell">
                <el-checkbox 
                  v-model="item.selected" 
                  @change="toggleSelect(item.id)"
                />
              </div>
              
              <div class="cart-cell product-cell">
                <div class="product-info">
                  <img 
                    class="product-image" 
                    :src="item.product.image" 
                    :alt="item.product.name"
                    @click="router.push(`/product/${item.product.id}`)"
                  />
                  <div class="product-details">
                    <div 
                      class="product-name"
                      @click="router.push(`/product/${item.product.id}`)"
                    >
                      {{ item.product.name }}
                    </div>
                    <div class="product-specs" v-if="item.product.specs">
                      {{ item.product.specs }}
                    </div>
                  </div>
                </div>
              </div>
              
              <div class="cart-cell price-cell">
                <div class="price">
                  <span class="current-price">¥{{ item.product.price.toFixed(2) }}</span>
                  <span class="original-price" v-if="item.product.originalPrice">
                    ¥{{ item.product.originalPrice.toFixed(2) }}
                  </span>
                </div>
              </div>
              
              <div class="cart-cell quantity-cell">
                <el-input-number 
                  v-model="item.quantity" 
                  :min="1" 
                  :max="99"
                  @change="updateQuantity(item.id, item.quantity)"
                />
              </div>
              
              <div class="cart-cell subtotal-cell">
                <span class="subtotal">¥{{ (item.product.price * item.quantity).toFixed(2) }}</span>
              </div>
              
              <div class="cart-cell action-cell">
                <el-button 
                  type="danger" 
                  text
                  @click="removeItem(item.id)"
                >
                  删除
                </el-button>
              </div>
            </div>
          </div>
        </div>
        
        <!-- 购物车底部区域 -->
        <div class="cart-footer">
          <div class="cart-operations">
            <el-checkbox v-model="allSelected">全选</el-checkbox>
            <el-button text @click="clearCart" type="danger">清空购物车</el-button>
          </div>
          
          <div class="cart-summary">
            <div class="summary-info">
              <div class="summary-item">
                已选商品 <span class="highlight">{{ selectedItemCount }}</span> 件，共 <span class="highlight">{{ selectedQuantityCount }}</span> 件
              </div>
              <div class="summary-item">
                商品总价: <span class="price">¥{{ cartStore.totalPrice.toFixed(2) }}</span>
              </div>
              <div class="summary-item">
                已节省: <span class="discount">¥{{ cartStore.savedAmount.toFixed(2) }}</span>
              </div>
            </div>
            
            <div class="checkout-actions">
              <el-button @click="continueShopping">继续购物</el-button>
              <el-button 
                type="primary" 
                @click="checkout" 
                :disabled="selectedItemCount === 0"
              >
                去结算 ({{ selectedItemCount }})
              </el-button>
            </div>
          </div>
        </div>
      </div>
      
      <!-- 推荐商品 -->
      <div class="recommended-products section">
        <h2 class="section-title">猜你喜欢</h2>
        <el-row :gutter="20">
          <el-col :span="6" v-for="product in recommendedProducts" :key="product.id">
            <ProductCard :product="product" />
          </el-col>
        </el-row>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { useCartStore } from '@/stores/cart'
import { ElMessage, ElMessageBox } from 'element-plus'
import ProductCard from '@/components/product/ProductCard.vue'

const router = useRouter()
const cartStore = useCartStore()

// 购物车商品列表
const cartItems = computed(() => cartStore.items)

// 全选状态
const allSelected = computed({
  get: () => {
    return cartItems.value.length > 0 && cartItems.value.every(item => item.selected)
  },
  set: (value) => {
    cartStore.toggleAllSelection(value)
  }
})

// 继续购物
const continueShopping = () => {
  router.push('/products')
}

// 选择/取消选择单个商品
const toggleSelect = (itemId) => {
  cartStore.toggleItemSelection(itemId)
}

// 更新商品数量
const updateQuantity = (itemId, newQuantity) => {
  cartStore.updateItemQuantity(itemId, newQuantity)
}

// 移除商品
const removeItem = (itemId) => {
  cartStore.removeItem(itemId)
}

// 清空购物车
const clearCart = () => {
  ElMessageBox.confirm('确定要清空购物车吗?', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(() => {
    cartStore.clearCart()
    ElMessage.success('购物车已清空')
  }).catch(() => {})
}

// 结算
const checkout = () => {
  if (selectedItemCount.value === 0) {
    ElMessage.warning('请至少选择一件商品')
    return
  }
  router.push('/checkout')
}

// 计算已选商品数量
const selectedItemCount = computed(() => {
  return cartStore.selectedItems.length
})

// 计算已选商品总数量
const selectedQuantityCount = computed(() => {
  return cartStore.selectedItems.reduce((total, item) => total + item.quantity, 0)
})

// 推荐商品
const recommendedProducts = ref([
  {
    id: 101,
    name: '智能降噪耳机',
    description: '高品质音频体验',
    price: 899,
    originalPrice: 1299,
    image: 'https://picsum.photos/id/367/300/300',
    rating: 4.8,
    reviewCount: 256,
    category: '电子产品'
  },
  {
    id: 102,
    name: '便携式充电宝',
    description: '20000mAh大容量',
    price: 199,
    originalPrice: 299,
    image: 'https://picsum.photos/id/365/300/300',
    rating: 4.6,
    reviewCount: 1024,
    category: '电子产品'
  },
  {
    id: 103,
    name: '无线充电器',
    description: '快速充电，兼容多设备',
    price: 129,
    originalPrice: 199,
    image: 'https://picsum.photos/id/119/300/300',
    rating: 4.5,
    reviewCount: 768,
    category: '电子产品'
  },
  {
    id: 104,
    name: '手机保护壳',
    description: '防摔设计，完美贴合',
    price: 49,
    originalPrice: 99,
    image: 'https://picsum.photos/id/366/300/300',
    rating: 4.7,
    reviewCount: 512,
    category: '电子产品'
  }
])
</script>

<style lang="scss" scoped>
@import '@/assets/css/variables.scss';

.cart-page {
  padding: $spacing-xl 0;
}

.page-title {
  font-size: 28px;
  font-weight: 600;
  margin-bottom: $spacing-xl;
  color: $text-color;
}

.empty-cart {
  padding: $spacing-xl 0;
  display: flex;
  justify-content: center;
}

.cart-container {
  margin-bottom: $spacing-xl;
}

.cart-table {
  background-color: $bg-white;
  border-radius: $border-radius;
  box-shadow: $box-shadow;
  overflow: hidden;
}

.cart-header {
  display: flex;
  background-color: $bg-color;
  padding: $spacing-md;
  border-bottom: 1px solid $border-light;
  font-weight: 600;
  color: $text-color;
}

.cart-row {
  display: flex;
  padding: $spacing-md;
  border-bottom: 1px solid $border-light;
  
  &:last-child {
    border-bottom: none;
  }
  
  &:hover {
    background-color: $bg-color;
  }
}

.cart-cell {
  display: flex;
  align-items: center;
}

.checkbox-cell {
  width: 5%;
  justify-content: center;
}

.product-cell {
  width: 40%;
  
  .product-info {
    display: flex;
    align-items: center;
    
    .product-image {
      width: 80px;
      height: 80px;
      border-radius: $border-radius-sm;
      object-fit: cover;
      cursor: pointer;
      margin-right: $spacing-md;
    }
    
    .product-details {
      .product-name {
        font-size: 16px;
        font-weight: 600;
        margin-bottom: $spacing-xs;
        cursor: pointer;
        color: $text-color;
        
        &:hover {
          color: $primary-color;
        }
      }
      
      .product-specs {
        font-size: 14px;
        color: $text-light;
      }
    }
  }
}

.price-cell {
  width: 15%;
  
  .price {
    display: flex;
    flex-direction: column;
    
    .current-price {
      font-size: 16px;
      font-weight: 600;
      color: $danger-color;
    }
    
    .original-price {
      font-size: 14px;
      color: $text-lighter;
      text-decoration: line-through;
    }
  }
}

.quantity-cell {
  width: 15%;
}

.subtotal-cell {
  width: 15%;
  
  .subtotal {
    font-size: 16px;
    font-weight: 600;
    color: $danger-color;
  }
}

.action-cell {
  width: 10%;
  justify-content: center;
}

.cart-footer {
  background-color: $bg-white;
  border-radius: $border-radius;
  box-shadow: $box-shadow;
  margin-top: $spacing-md;
  padding: $spacing-md;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.cart-operations {
  display: flex;
  align-items: center;
  
  .el-button {
    margin-left: $spacing-md;
  }
}

.cart-summary {
  display: flex;
  align-items: center;
  
  .summary-info {
    margin-right: $spacing-xl;
    
    .summary-item {
      margin-bottom: $spacing-xs;
      
      &:last-child {
        margin-bottom: 0;
      }
      
      .highlight {
        color: $primary-color;
        font-weight: 600;
      }
      
      .price {
        color: $danger-color;
        font-size: 18px;
        font-weight: 600;
      }
      
      .discount {
        color: $success-color;
        font-weight: 600;
      }
    }
  }
  
  .checkout-actions {
    display: flex;
    gap: $spacing-md;
  }
}

.section {
  margin-top: $spacing-xl;
}

.section-title {
  font-size: 20px;
  font-weight: 600;
  margin-bottom: $spacing-lg;
  color: $text-color;
}
</style> 