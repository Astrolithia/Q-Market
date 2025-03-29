<template>
  <div class="cart-page">
    <div class="container">
      <h1 class="page-title">购物车</h1>
      
      <div v-if="cartItems.length === 0" class="empty-cart">
        <el-empty description="您的购物车是空的">
          <el-button type="primary" @click="continueShopping">去购物</el-button>
        </el-empty>
      </div>
      
      <div v-else class="cart-container">
        <!-- 购物车表格 -->
        <div class="cart-table">
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
          
          <div class="cart-body">
            <div v-for="item in cartItems" :key="item.id" class="cart-row">
              <div class="cart-cell checkbox-cell">
                <el-checkbox v-model="item.selected" @change="() => toggleSelect(item)" />
              </div>
              
              <div class="cart-cell product-cell">
                <div class="product-info">
                  <img :src="item.product.image" :alt="item.product.name" class="product-image" @click="router.push(`/product/${item.product.id}`)" />
                  <div class="product-details">
                    <h3 class="product-name" @click="router.push(`/product/${item.product.id}`)">{{ item.product.name }}</h3>
                    <div class="product-specs">{{ item.product.specs }}</div>
                  </div>
                </div>
              </div>
              
              <div class="cart-cell price-cell">
                <div class="price">
                  <span class="current-price">¥{{ item.product.price.toFixed(2) }}</span>
                  <span v-if="item.product.originalPrice" class="original-price">¥{{ item.product.originalPrice.toFixed(2) }}</span>
                </div>
              </div>
              
              <div class="cart-cell quantity-cell">
                <div class="quantity-control">
                  <el-button 
                    size="small" 
                    :icon="Minus" 
                    @click="decrementQuantity(item)" 
                    :disabled="item.quantity <= 1"
                  />
                  <el-input-number 
                    v-model="item.quantity" 
                    :min="1" 
                    :max="99" 
                    controls-position="right"
                    size="small"
                    @change="(value) => updateQuantity(item, value)"
                  />
                  <el-button 
                    size="small" 
                    :icon="Plus" 
                    @click="incrementQuantity(item)"
                  />
                </div>
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
                商品总价: <span class="price">¥{{ totalPrice.toFixed(2) }}</span>
              </div>
              <div class="summary-item">
                已节省: <span class="discount">¥{{ totalSaved.toFixed(2) }}</span>
              </div>
            </div>
            
            <div class="checkout-actions">
              <el-button @click="continueShopping">继续购物</el-button>
              <el-button 
                type="primary" 
                @click="goToCheckout" 
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
  width: 20%;
  
  .quantity-control {
    display: flex;
    align-items: center;
    
    .el-input-number {
      width: 120px;
      margin: 0 $spacing-xs;
    }
  }
}

.subtotal-cell {
  width: 15%;
  
  .subtotal {
    font-size: 18px;
    font-weight: 600;
    color: $danger-color;
  }
}

.action-cell {
  width: 5%;
  justify-content: center;
}

.cart-footer {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: $bg-white;
  padding: $spacing-md $spacing-lg;
  margin-top: $spacing-md;
  border-radius: $border-radius;
  box-shadow: $box-shadow;
  
  @media (max-width: $breakpoint-md) {
    flex-direction: column;
    gap: $spacing-md;
  }
}

.cart-operations {
  display: flex;
  align-items: center;
  gap: $spacing-md;
}

.cart-summary {
  display: flex;
  align-items: center;
  gap: $spacing-xl;
  
  @media (max-width: $breakpoint-md) {
    flex-direction: column;
    align-items: flex-start;
    width: 100%;
  }
  
  .summary-info {
    text-align: right;
    
    @media (max-width: $breakpoint-md) {
      text-align: left;
      width: 100%;
    }
    
    .summary-item {
      margin-bottom: $spacing-xs;
      color: $text-light;
      
      .highlight {
        color: $primary-color;
        font-weight: 600;
      }
      
      .price {
        font-size: 20px;
        font-weight: 600;
        color: $danger-color;
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
    
    @media (max-width: $breakpoint-md) {
      justify-content: flex-end;
      width: 100%;
    }
  }
}

.recommended-products {
  .section-title {
    font-size: 20px;
    font-weight: 600;
    margin-bottom: $spacing-lg;
    color: $text-color;
  }
}

@media (max-width: $breakpoint-sm) {
  .cart-table {
    font-size: 14px;
  }
  
  .cart-header {
    display: none;
  }
  
  .cart-row {
    flex-wrap: wrap;
    padding: $spacing-md $spacing-sm;
  }
  
  .cart-cell {
    margin-bottom: $spacing-sm;
  }
  
  .checkbox-cell {
    width: 10%;
  }
  
  .product-cell {
    width: 90%;
    order: 1;
  }
  
  .price-cell {
    width: 30%;
    order: 2;
  }
  
  .quantity-cell {
    width: 40%;
    order: 3;
    
    .quantity-control {
      justify-content: center;
      .el-input-number {
        width: 100px;
      }
    }
  }
  
  .subtotal-cell {
    width: 30%;
    order: 4;
    justify-content: flex-end;
  }
  
  .action-cell {
    width: 100%;
    order: 5;
    justify-content: flex-end;
  }
}
</style> 