<template>
  <div class="orders-page">
    <div class="container">
      <h1 class="page-title">我的订单</h1>
      
      <div v-if="orderStore.loading" class="loading-container">
        <el-skeleton :rows="5" animated />
      </div>
      
      <div v-else-if="orderStore.filteredOrders.length === 0" class="empty-orders">
        <el-empty description="您还没有订单">
          <el-button type="primary" @click="router.push('/products')">去购物</el-button>
        </el-empty>
      </div>
      
      <div v-else>
        <!-- 订单筛选 -->
        <div class="order-filter">
          <el-tabs v-model="activeTab" @tab-click="handleTabClick">
            <el-tab-pane label="全部" name="all"></el-tab-pane>
            <el-tab-pane label="待付款" name="unpaid"></el-tab-pane>
            <el-tab-pane label="待发货" name="pending"></el-tab-pane>
            <el-tab-pane label="待收货" name="shipped"></el-tab-pane>
            <el-tab-pane label="待评价" name="received"></el-tab-pane>
            <el-tab-pane label="已完成" name="completed"></el-tab-pane>
          </el-tabs>
          
          <div class="search-filter">
            <el-input 
              v-model="searchKeyword" 
              placeholder="订单号/商品名称" 
              prefix-icon="Search"
              clearable
              @keyup.enter="searchOrders"
            >
            </el-input>
            <el-button @click="searchOrders">搜索</el-button>
          </div>
        </div>
        
        <!-- 订单列表 -->
        <div class="order-list">
          <div v-for="order in orderStore.currentPageOrders" :key="order.id" class="order-card">
            <div class="order-header">
              <div class="order-info">
                <span class="order-date">{{ formatDate(order.createTime) }}</span>
                <span class="order-number">订单号：{{ order.orderNumber }}</span>
              </div>
              <div class="order-status" :class="`status-${order.status}`">
                {{ orderStore.getStatusText(order.status) }}
              </div>
            </div>
            
            <div class="order-items">
              <div v-for="item in order.items" :key="`${order.id}-${item.id}`" class="order-item">
                <div class="item-info" @click="goToProductDetail(item.product.id)">
                  <img :src="item.product.image" :alt="item.product.name" class="item-image" />
                  <div class="item-details">
                    <div class="item-name">{{ item.product.name }}</div>
                    <div class="item-specs" v-if="item.product.specs">
                      {{ item.product.specs }}
                    </div>
                  </div>
                </div>
                <div class="item-price">
                  <div class="price">¥{{ item.product.price.toFixed(2) }}</div>
                  <div class="quantity">x{{ item.quantity }}</div>
                </div>
              </div>
            </div>
            
            <div class="order-footer">
              <div class="order-total">
                共 <span class="count">{{ order.items.length }}</span> 件商品，
                合计：<span class="price">¥{{ order.totalAmount.toFixed(2) }}</span>
                （含运费 ¥{{ order.shippingFee.toFixed(2) }}）
              </div>
              
              <div class="order-actions">
                <template v-if="order.status === 'unpaid'">
                  <el-button type="danger" @click="payOrder(order.id)">立即付款</el-button>
                  <el-button @click="cancelOrder(order.id)">取消订单</el-button>
                </template>
                
                <template v-else-if="order.status === 'pending'">
                  <el-button @click="viewOrderDetail(order.id)">查看详情</el-button>
                  <el-button @click="contactCustomerService(order.id)">联系客服</el-button>
                </template>
                
                <template v-else-if="order.status === 'shipped'">
                  <el-button type="primary" @click="confirmReceive(order.id)">确认收货</el-button>
                  <el-button @click="trackShipment(order.id)">查看物流</el-button>
                </template>
                
                <template v-else-if="order.status === 'received'">
                  <el-button type="primary" @click="reviewOrder(order.id)">评价订单</el-button>
                  <el-button @click="viewOrderDetail(order.id)">查看详情</el-button>
                </template>
                
                <template v-else>
                  <el-button @click="viewOrderDetail(order.id)">查看详情</el-button>
                  <el-button v-if="order.status === 'completed'" @click="buyAgain(order.id)">再次购买</el-button>
                </template>
              </div>
            </div>
          </div>
        </div>
        
        <!-- 分页 -->
        <div class="pagination-container">
          <el-pagination
            background
            layout="prev, pager, next"
            :total="orderStore.pagination.total"
            :page-size="orderStore.pagination.pageSize"
            :current-page="orderStore.pagination.currentPage"
            @current-change="handlePageChange"
          />
        </div>
      </div>
    </div>
    
    <!-- 订单详情对话框 -->
    <el-dialog
      v-model="orderDetailVisible"
      title="订单详情"
      width="60%"
    >
      <div v-if="currentOrder" class="order-detail">
        <div class="detail-section">
          <h3>订单信息</h3>
          <el-descriptions :column="1" border>
            <el-descriptions-item label="订单编号">{{ currentOrder.orderNumber }}</el-descriptions-item>
            <el-descriptions-item label="下单时间">{{ formatDate(currentOrder.createTime) }}</el-descriptions-item>
            <el-descriptions-item label="订单状态">{{ orderStore.getStatusText(currentOrder.status) }}</el-descriptions-item>
            <el-descriptions-item label="支付方式">{{ currentOrder.paymentMethod || '未支付' }}</el-descriptions-item>
            <el-descriptions-item label="支付时间" v-if="currentOrder.payTime">{{ formatDate(currentOrder.payTime) }}</el-descriptions-item>
          </el-descriptions>
        </div>
        
        <div class="detail-section">
          <h3>收货信息</h3>
          <el-descriptions :column="1" border>
            <el-descriptions-item label="收货人">{{ currentOrder.shipping.receiver }}</el-descriptions-item>
            <el-descriptions-item label="联系电话">{{ currentOrder.shipping.phone }}</el-descriptions-item>
            <el-descriptions-item label="收货地址">{{ currentOrder.shipping.address }}</el-descriptions-item>
            <el-descriptions-item label="配送方式">{{ currentOrder.shipping.method }}</el-descriptions-item>
            <el-descriptions-item label="物流单号" v-if="currentOrder.shipping.trackingNumber">
              {{ currentOrder.shipping.trackingNumber }}
            </el-descriptions-item>
          </el-descriptions>
        </div>
        
        <div class="detail-section">
          <h3>商品信息</h3>
          <el-table :data="currentOrder.items" stripe>
            <el-table-column label="商品" width="400">
              <template #default="scope">
                <div class="table-product-info">
                  <img :src="scope.row.product.image" :alt="scope.row.product.name" class="table-product-image" />
                  <div>
                    <div class="table-product-name">{{ scope.row.product.name }}</div>
                    <div class="table-product-specs" v-if="scope.row.product.specs">{{ scope.row.product.specs }}</div>
                  </div>
                </div>
              </template>
            </el-table-column>
            <el-table-column prop="product.price" label="单价" width="100">
              <template #default="scope">
                ¥{{ scope.row.product.price.toFixed(2) }}
              </template>
            </el-table-column>
            <el-table-column prop="quantity" label="数量" width="80" />
            <el-table-column label="小计" width="120">
              <template #default="scope">
                ¥{{ (scope.row.product.price * scope.row.quantity).toFixed(2) }}
              </template>
            </el-table-column>
          </el-table>
        </div>
        
        <div class="detail-section">
          <h3>金额明细</h3>
          <div class="amount-detail">
            <div class="amount-item">
              <span>商品总额：</span>
              <span>¥{{ calculateSubtotal(currentOrder.items).toFixed(2) }}</span>
            </div>
            <div class="amount-item">
              <span>运费：</span>
              <span>¥{{ currentOrder.shippingFee.toFixed(2) }}</span>
            </div>
            <div class="amount-item">
              <span>优惠：</span>
              <span class="discount">-¥{{ currentOrder.discount.toFixed(2) }}</span>
            </div>
            <div class="amount-item total">
              <span>实付款：</span>
              <span>¥{{ currentOrder.totalAmount.toFixed(2) }}</span>
            </div>
          </div>
        </div>
      </div>
      
      <template #footer>
        <div class="dialog-footer">
          <el-button @click="orderDetailVisible = false">关闭</el-button>
          <template v-if="currentOrder && currentOrder.status === 'unpaid'">
            <el-button type="danger" @click="payOrder(currentOrder.id)">立即付款</el-button>
          </template>
          <template v-else-if="currentOrder && currentOrder.status === 'shipped'">
            <el-button type="primary" @click="confirmReceive(currentOrder.id)">确认收货</el-button>
          </template>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { ElMessage, ElMessageBox } from 'element-plus'
import { useOrderStore } from '@/stores/order'
import { useCartStore } from '@/stores/cart'

const router = useRouter()
const orderStore = useOrderStore()
const cartStore = useCartStore()

// 订单筛选
const activeTab = ref('all')
const searchKeyword = ref('')

// 订单详情
const orderDetailVisible = ref(false)
const currentOrder = ref(null)

// 切换订单状态标签
const handleTabClick = () => {
  orderStore.setFilter({ status: activeTab.value })
}

// 搜索订单
const searchOrders = () => {
  orderStore.setFilter({ keyword: searchKeyword.value })
}

// 处理分页切换
const handlePageChange = (page) => {
  orderStore.setPage(page)
}

// 查看订单详情
const viewOrderDetail = (orderId) => {
  currentOrder.value = orderStore.getOrderById(orderId)
  if (currentOrder.value) {
    orderDetailVisible.value = true
  }
}

// 支付订单
const payOrder = (orderId) => {
  ElMessageBox.confirm('确定要支付此订单吗?', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(() => {
    const success = orderStore.payOrder(orderId)
    if (success) {
      ElMessage.success('支付成功，等待商家发货')
    } else {
      ElMessage.error('支付失败，请稍后再试')
    }
  }).catch(() => {})
}

// 取消订单
const cancelOrder = (orderId) => {
  ElMessageBox.confirm('确定要取消此订单吗?', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(() => {
    const success = orderStore.cancelOrder(orderId)
    if (success) {
      ElMessage.success('订单已取消')
    } else {
      ElMessage.error('取消失败，请稍后再试')
    }
  }).catch(() => {})
}

// 确认收货
const confirmReceive = (orderId) => {
  ElMessageBox.confirm('确认已收到商品吗?', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(() => {
    const success = orderStore.confirmReceive(orderId)
    if (success) {
      ElMessage.success('已确认收货')
    } else {
      ElMessage.error('操作失败，请稍后再试')
    }
  }).catch(() => {})
}

// 查看物流
const trackShipment = (orderId) => {
  const order = orderStore.getOrderById(orderId)
  if (order && order.shipping.trackingNumber) {
    ElMessage('已跳转到物流查询页面，单号：' + order.shipping.trackingNumber)
  } else {
    ElMessage.warning('暂无物流信息')
  }
}

// 评价订单
const reviewOrder = (orderId) => {
  const success = orderStore.reviewOrder(orderId)
  if (success) {
    ElMessage.success('评价成功，感谢您的反馈')
  } else {
    ElMessage.error('评价失败，请稍后再试')
  }
}

// 联系客服
const contactCustomerService = () => {
  ElMessage('已连接客服，请稍候...')
}

// 再次购买
const buyAgain = (orderId) => {
  const order = orderStore.getOrderById(orderId)
  if (!order) return
  
  ElMessageBox.confirm('确定将订单中的商品加入购物车吗?', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'info'
  }).then(() => {
    // 将订单中的商品添加到购物车
    order.items.forEach(item => {
      cartStore.addItem(item.product, item.quantity)
    })
    ElMessage.success('已加入购物车')
  }).catch(() => {})
}

// 跳转到商品详情
const goToProductDetail = (productId) => {
  router.push(`/product/${productId}`)
}

// 格式化日期
const formatDate = (dateStr) => {
  if (!dateStr) return ''
  const date = new Date(dateStr)
  return date.toLocaleString('zh-CN', {
    year: 'numeric',
    month: '2-digit',
    day: '2-digit',
    hour: '2-digit',
    minute: '2-digit'
  })
}

// 计算商品小计
const calculateSubtotal = (items) => {
  return items.reduce((sum, item) => sum + item.product.price * item.quantity, 0)
}
</script>

<style lang="scss" scoped>
@import '@/assets/css/variables.scss';

.orders-page {
  padding: $spacing-xl 0;
}

.page-title {
  font-size: 28px;
  font-weight: 600;
  margin-bottom: $spacing-xl;
  color: $text-color;
}

.loading-container, .empty-orders {
  background-color: $bg-white;
  border-radius: $border-radius;
  padding: $spacing-xl;
  box-shadow: $box-shadow;
  display: flex;
  justify-content: center;
}

.order-filter {
  background-color: $bg-white;
  border-radius: $border-radius;
  box-shadow: $box-shadow;
  padding: $spacing-md $spacing-lg;
  margin-bottom: $spacing-lg;
  display: flex;
  justify-content: space-between;
  align-items: center;
  
  .search-filter {
    display: flex;
    gap: $spacing-sm;
    max-width: 300px;
  }
}

.order-list {
  margin-bottom: $spacing-xl;
}

.order-card {
  background-color: $bg-white;
  border-radius: $border-radius;
  box-shadow: $box-shadow;
  margin-bottom: $spacing-md;
  overflow: hidden;
  
  .order-header {
    display: flex;
    justify-content: space-between;
    padding: $spacing-md $spacing-lg;
    background-color: $bg-color;
    border-bottom: 1px solid $border-light;
    
    .order-info {
      display: flex;
      gap: $spacing-lg;
      color: $text-light;
      
      .order-date {
        font-weight: 500;
      }
    }
    
    .order-status {
      font-weight: 600;
      
      &.status-unpaid { color: $warning-color; }
      &.status-pending { color: $primary-color; }
      &.status-shipped { color: $info-color; }
      &.status-received { color: $success-color; }
      &.status-completed { color: $text-color; }
      &.status-cancelled { color: $text-lighter; }
    }
  }
  
  .order-items {
    padding: $spacing-md $spacing-lg;
    border-bottom: 1px solid $border-light;
    
    .order-item {
      display: flex;
      justify-content: space-between;
      align-items: center;
      padding: $spacing-sm 0;
      
      &:not(:last-child) {
        border-bottom: 1px dashed $border-light;
        margin-bottom: $spacing-sm;
        padding-bottom: $spacing-md;
      }
      
      .item-info {
        display: flex;
        align-items: center;
        cursor: pointer;
        flex: 1;
        
        .item-image {
          width: 60px;
          height: 60px;
          object-fit: cover;
          border-radius: $border-radius-sm;
          margin-right: $spacing-md;
        }
        
        .item-details {
          .item-name {
            font-weight: 500;
            margin-bottom: 4px;
            
            &:hover {
              color: $primary-color;
            }
          }
          
          .item-specs {
            color: $text-light;
            font-size: 14px;
          }
        }
      }
      
      .item-price {
        display: flex;
        flex-direction: column;
        align-items: flex-end;
        
        .price {
          font-weight: 500;
        }
        
        .quantity {
          color: $text-light;
          font-size: 14px;
        }
      }
    }
  }
  
  .order-footer {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: $spacing-md $spacing-lg;
    
    .order-total {
      color: $text-light;
      
      .count, .price {
        color: $danger-color;
        font-weight: 600;
      }
    }
    
    .order-actions {
      display: flex;
      gap: $spacing-sm;
    }
  }
}

.pagination-container {
  display: flex;
  justify-content: center;
  margin-top: $spacing-xl;
}

// 订单详情样式
.order-detail {
  .detail-section {
    margin-bottom: $spacing-lg;
    
    h3 {
      font-size: 16px;
      font-weight: 600;
      margin-bottom: $spacing-md;
      padding-bottom: $spacing-xs;
      border-bottom: 1px solid $border-light;
    }
  }
  
  .table-product-info {
    display: flex;
    align-items: center;
    
    .table-product-image {
      width: 50px;
      height: 50px;
      object-fit: cover;
      border-radius: $border-radius-sm;
      margin-right: $spacing-md;
    }
    
    .table-product-name {
      font-weight: 500;
    }
    
    .table-product-specs {
      color: $text-light;
      font-size: 14px;
    }
  }
  
  .amount-detail {
    background-color: $bg-color;
    border-radius: $border-radius;
    padding: $spacing-md;
    
    .amount-item {
      display: flex;
      justify-content: space-between;
      margin-bottom: $spacing-xs;
      
      &.total {
        margin-top: $spacing-sm;
        padding-top: $spacing-sm;
        border-top: 1px solid $border-light;
        font-size: 16px;
        font-weight: 600;
        
        span:last-child {
          color: $danger-color;
        }
      }
      
      .discount {
        color: $success-color;
      }
    }
  }
}

.dialog-footer {
  display: flex;
  justify-content: flex-end;
  gap: $spacing-sm;
}
</style> 