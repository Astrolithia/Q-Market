<script setup>
import { ref, reactive, computed } from 'vue'
import { useRouter } from 'vue-router'
import { useCartStore } from '@/stores/cart'

const router = useRouter()
const cartStore = useCartStore()

// 收货地址列表
const addresses = ref([
  {
    id: 1,
    name: '张三',
    phone: '13800138000',
    region: '广东省 深圳市 南山区',
    detail: '科技园南区A栋101室',
    isDefault: true
  },
  {
    id: 2,
    name: '李四',
    phone: '13900139000',
    region: '北京市 海淀区',
    detail: '中关村software园B区23号楼',
    isDefault: false
  }
])

// 当前选中的地址ID
const selectedAddressId = ref(addresses.value.find(addr => addr.isDefault)?.id || null)

// 配送方式
const deliveryOptions = [
  { label: '普通配送', value: 'normal', fee: 10 },
  { label: '加急配送 (24小时内送达)', value: 'express', fee: 20 },
  { label: '上门自提', value: 'self', fee: 0 }
]

// 当前选中的配送方式
const selectedDelivery = ref('normal')

// 支付方式
const paymentOptions = [
  { label: '支付宝', value: 'alipay', icon: 'AlipayCircleFilled' },
  { label: '微信支付', value: 'wechat', icon: 'ChatDotRound' },
  { label: '银联支付', value: 'unionpay', icon: 'CreditCard' }
]

// 当前选中的支付方式
const selectedPayment = ref('alipay')

// 优惠券
const coupons = ref([
  { id: 1, name: '满100减10优惠券', discount: 10, condition: 100 },
  { id: 2, name: '满500减50优惠券', discount: 50, condition: 500 },
  { id: 3, name: '满1000减150优惠券', discount: 150, condition: 1000 }
])

// 当前选中的优惠券ID
const selectedCouponId = ref(null)

// 订单备注
const orderNote = ref('')

// 发票信息
const needInvoice = ref(false)
const invoiceInfo = reactive({
  type: 'personal', // personal or company
  title: '',
  taxNumber: '',
  content: '商品明细'
})

// 购物车商品列表 (示例数据)
const cartItems = ref([
  {
    id: 1,
    product: {
      id: 1,
      name: '高端智能手机 Pro Max',
      price: 5999,
      originalPrice: 6999,
      image: 'https://picsum.photos/id/1/100/100',
      specs: '星空蓝 / 256GB'
    },
    quantity: 1,
    selected: true
  },
  {
    id: 2,
    product: {
      id: 3,
      name: '智能手表 Series 7',
      price: 2599,
      originalPrice: 2999,
      image: 'https://picsum.photos/id/3/100/100',
      specs: '黑色 / 44mm'
    },
    quantity: 2,
    selected: true
  }
])

// 计算商品总价
const subtotal = computed(() => {
  return cartItems.value.reduce((sum, item) => sum + item.product.price * item.quantity, 0)
})

// 获取当前选中的优惠券
const selectedCoupon = computed(() => {
  return coupons.value.find(coupon => coupon.id === selectedCouponId.value)
})

// 计算优惠金额
const discountAmount = computed(() => {
  if (!selectedCoupon.value) return 0
  return subtotal.value >= selectedCoupon.value.condition ? selectedCoupon.value.discount : 0
})

// 计算配送费
const deliveryFee = computed(() => {
  const option = deliveryOptions.find(option => option.value === selectedDelivery.value)
  return option ? option.fee : 0
})

// 计算订单总价
const totalAmount = computed(() => {
  return subtotal.value + deliveryFee.value - discountAmount.value
})

// 提交订单
const submitOrder = () => {
  // 这里应该调用下单API
  ElMessage.success('订单提交成功，即将跳转到支付页面')
  setTimeout(() => {
    router.push('/payment?orderNo=ON' + Date.now())
  }, 1500)
}

// 返回购物车
const goBack = () => {
  router.push('/cart')
}

// 打开新增地址对话框
const addressDialogVisible = ref(false)
const newAddress = reactive({
  name: '',
  phone: '',
  region: '',
  detail: '',
  isDefault: false
})

// 添加新地址
const addNewAddress = () => {
  // 这里应该调用添加地址API
  const id = Date.now()
  addresses.value.push({
    id,
    ...newAddress
  })
  
  if (newAddress.isDefault) {
    addresses.value.forEach(addr => {
      if (addr.id !== id) {
        addr.isDefault = false
      }
    })
  }
  
  selectedAddressId.value = id
  addressDialogVisible.value = false
  
  // 重置表单
  Object.keys(newAddress).forEach(key => {
    if (key === 'isDefault') {
      newAddress[key] = false
    } else {
      newAddress[key] = ''
    }
  })
}
</script>

<template>
  <div class="checkout-page">
    <div class="container">
      <h1 class="page-title">确认订单</h1>
      
      <!-- 收货地址 -->
      <div class="checkout-section">
        <div class="section-header">
          <h2 class="section-title">收货地址</h2>
          <el-button type="primary" link @click="addressDialogVisible = true">
            <el-icon><Plus /></el-icon> 新增地址
          </el-button>
        </div>
        
        <div class="address-list">
          <el-radio-group v-model="selectedAddressId">
            <div v-for="address in addresses" :key="address.id" class="address-item">
              <el-radio :label="address.id">
                <div class="address-info">
                  <div class="recipient">
                    <span class="name">{{ address.name }}</span>
                    <span class="phone">{{ address.phone }}</span>
                    <el-tag v-if="address.isDefault" size="small" type="success">默认</el-tag>
                  </div>
                  <div class="address-detail">
                    {{ address.region }} {{ address.detail }}
                  </div>
                </div>
              </el-radio>
            </div>
          </el-radio-group>
        </div>
      </div>
      
      <!-- 商品清单 -->
      <div class="checkout-section">
        <h2 class="section-title">商品清单</h2>
        
        <div class="product-list">
          <div class="product-header">
            <div class="product-cell product-info-cell">商品信息</div>
            <div class="product-cell">单价</div>
            <div class="product-cell">数量</div>
            <div class="product-cell">小计</div>
          </div>
          
          <div class="product-body">
            <div v-for="item in cartItems" :key="item.id" class="product-row">
              <div class="product-cell product-info-cell">
                <div class="product-info">
                  <img :src="item.product.image" :alt="item.product.name" class="product-image" />
                  <div class="product-details">
                    <h3 class="product-name">{{ item.product.name }}</h3>
                    <div class="product-specs">{{ item.product.specs }}</div>
                  </div>
                </div>
              </div>
              
              <div class="product-cell">
                <div class="price">
                  <span class="current-price">¥{{ item.product.price.toFixed(2) }}</span>
                  <span v-if="item.product.originalPrice" class="original-price">¥{{ item.product.originalPrice.toFixed(2) }}</span>
                </div>
              </div>
              
              <div class="product-cell">
                <span class="quantity">× {{ item.quantity }}</span>
              </div>
              
              <div class="product-cell">
                <span class="subtotal">¥{{ (item.product.price * item.quantity).toFixed(2) }}</span>
              </div>
            </div>
          </div>
        </div>
      </div>
      
      <!-- 配送方式 -->
      <div class="checkout-section">
        <h2 class="section-title">配送方式</h2>
        
        <div class="delivery-options">
          <el-radio-group v-model="selectedDelivery">
            <el-radio 
              v-for="option in deliveryOptions" 
              :key="option.value" 
              :label="option.value"
            >
              {{ option.label }} <span class="fee" v-if="option.fee > 0">+¥{{ option.fee.toFixed(2) }}</span>
            </el-radio>
          </el-radio-group>
        </div>
      </div>
      
      <!-- 支付方式 -->
      <div class="checkout-section">
        <h2 class="section-title">支付方式</h2>
        
        <div class="payment-options">
          <el-radio-group v-model="selectedPayment">
            <el-radio 
              v-for="option in paymentOptions" 
              :key="option.value" 
              :label="option.value"
            >
              <el-icon>
                <component :is="option.icon"></component>
              </el-icon>
              {{ option.label }}
            </el-radio>
          </el-radio-group>
        </div>
      </div>
      
      <!-- 优惠券 -->
      <div class="checkout-section">
        <h2 class="section-title">优惠券</h2>
        
        <div class="coupon-options">
          <el-select v-model="selectedCouponId" placeholder="请选择优惠券" clearable>
            <el-option
              v-for="coupon in coupons"
              :key="coupon.id"
              :label="coupon.name"
              :value="coupon.id"
              :disabled="subtotal < coupon.condition"
            >
              <div class="coupon-option">
                <span>{{ coupon.name }}</span>
                <span v-if="subtotal < coupon.condition" class="coupon-condition">
                  还差¥{{ (coupon.condition - subtotal).toFixed(2) }}
                </span>
              </div>
            </el-option>
          </el-select>
        </div>
      </div>
      
      <!-- 发票信息 -->
      <div class="checkout-section">
        <h2 class="section-title">发票信息</h2>
        
        <div class="invoice-options">
          <el-checkbox v-model="needInvoice">需要发票</el-checkbox>
          
          <div v-if="needInvoice" class="invoice-form">
            <el-form label-position="top">
              <el-form-item label="发票类型">
                <el-radio-group v-model="invoiceInfo.type">
                  <el-radio label="personal">个人</el-radio>
                  <el-radio label="company">单位</el-radio>
                </el-radio-group>
              </el-form-item>
              
              <el-form-item label="发票抬头">
                <el-input v-model="invoiceInfo.title" placeholder="请输入发票抬头"></el-input>
              </el-form-item>
              
              <el-form-item v-if="invoiceInfo.type === 'company'" label="税号">
                <el-input v-model="invoiceInfo.taxNumber" placeholder="请输入单位税号"></el-input>
              </el-form-item>
              
              <el-form-item label="发票内容">
                <el-select v-model="invoiceInfo.content">
                  <el-option label="商品明细" value="商品明细"></el-option>
                  <el-option label="商品类别" value="商品类别"></el-option>
                </el-select>
              </el-form-item>
            </el-form>
          </div>
        </div>
      </div>
      
      <!-- 订单备注 -->
      <div class="checkout-section">
        <h2 class="section-title">订单备注</h2>
        
        <div class="order-note">
          <el-input 
            v-model="orderNote" 
            type="textarea" 
            placeholder="请输入订单备注（选填）" 
            :rows="3"
            maxlength="200"
            show-word-limit
          ></el-input>
        </div>
      </div>
      
      <!-- 订单金额 -->
      <div class="checkout-footer">
        <div class="order-summary">
          <div class="summary-item">
            <span class="summary-label">商品总价:</span>
            <span class="summary-value">¥{{ subtotal.toFixed(2) }}</span>
          </div>
          <div class="summary-item">
            <span class="summary-label">运费:</span>
            <span class="summary-value">+¥{{ deliveryFee.toFixed(2) }}</span>
          </div>
          <div class="summary-item" v-if="discountAmount > 0">
            <span class="summary-label">优惠券:</span>
            <span class="summary-value discount">-¥{{ discountAmount.toFixed(2) }}</span>
          </div>
          <div class="summary-item total">
            <span class="summary-label">应付金额:</span>
            <span class="summary-value total-price">¥{{ totalAmount.toFixed(2) }}</span>
          </div>
        </div>
        
        <div class="order-actions">
          <el-button @click="goBack">返回购物车</el-button>
          <el-button type="primary" @click="submitOrder" :disabled="!selectedAddressId">提交订单</el-button>
        </div>
      </div>
    </div>
    
    <!-- 新增地址对话框 -->
    <el-dialog
      v-model="addressDialogVisible"
      title="新增收货地址"
      width="500px"
    >
      <el-form label-position="top">
        <el-form-item label="收货人">
          <el-input v-model="newAddress.name" placeholder="请输入收货人姓名"></el-input>
        </el-form-item>
        
        <el-form-item label="手机号码">
          <el-input v-model="newAddress.phone" placeholder="请输入手机号码"></el-input>
        </el-form-item>
        
        <el-form-item label="所在地区">
          <el-input v-model="newAddress.region" placeholder="请选择所在地区"></el-input>
        </el-form-item>
        
        <el-form-item label="详细地址">
          <el-input 
            v-model="newAddress.detail" 
            type="textarea" 
            :rows="3" 
            placeholder="请输入详细地址"
          ></el-input>
        </el-form-item>
        
        <el-form-item>
          <el-checkbox v-model="newAddress.isDefault">设为默认收货地址</el-checkbox>
        </el-form-item>
      </el-form>
      
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="addressDialogVisible = false">取消</el-button>
          <el-button type="primary" @click="addNewAddress">保存</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<style lang="scss" scoped>
@import '@/assets/css/variables.scss';

.checkout-page {
  padding: $spacing-xl 0;
}

.page-title {
  font-size: 24px;
  font-weight: 600;
  margin-bottom: $spacing-xl;
  color: $text-color;
}

.checkout-section {
  background-color: $bg-white;
  border-radius: $border-radius;
  box-shadow: $box-shadow;
  padding: $spacing-lg;
  margin-bottom: $spacing-lg;
}

.section-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: $spacing-md;
}

.section-title {
  font-size: 18px;
  font-weight: 600;
  color: $text-color;
  margin-bottom: $spacing-md;
}

// 地址样式
.address-list {
  display: flex;
  flex-direction: column;
  gap: $spacing-md;
  
  .address-item {
    border: 1px solid $border-light;
    border-radius: $border-radius;
    padding: $spacing-md;
    transition: all $transition-time;
    
    &:hover {
      border-color: $primary-color;
    }
    
    .address-info {
      margin-left: $spacing-md;
    }
    
    .recipient {
      margin-bottom: $spacing-xs;
      
      .name {
        font-weight: 600;
        margin-right: $spacing-md;
      }
      
      .phone {
        color: $text-light;
        margin-right: $spacing-md;
      }
    }
    
    .address-detail {
      color: $text-light;
    }
  }
}

// 商品列表样式
.product-list {
  .product-header {
    display: flex;
    background-color: $bg-color;
    padding: $spacing-md;
    border-radius: $border-radius $border-radius 0 0;
    border-bottom: 1px solid $border-light;
    font-weight: 600;
    
    @media (max-width: $breakpoint-sm) {
      display: none;
    }
  }
  
  .product-body {
    .product-row {
      display: flex;
      padding: $spacing-md;
      border-bottom: 1px solid $border-light;
      
      &:last-child {
        border-bottom: none;
      }
      
      @media (max-width: $breakpoint-sm) {
        flex-wrap: wrap;
      }
    }
  }
  
  .product-cell {
    flex: 1;
    display: flex;
    align-items: center;
    justify-content: center;
    
    &.product-info-cell {
      flex: 3;
      justify-content: flex-start;
      
      @media (max-width: $breakpoint-sm) {
        flex-basis: 100%;
        margin-bottom: $spacing-md;
      }
    }
    
    @media (max-width: $breakpoint-sm) {
      flex-basis: 33.333%;
    }
  }
  
  .product-info {
    display: flex;
    align-items: center;
    
    .product-image {
      width: 80px;
      height: 80px;
      border-radius: $border-radius-sm;
      object-fit: cover;
      margin-right: $spacing-md;
    }
    
    .product-details {
      .product-name {
        font-size: 16px;
        font-weight: 600;
        margin-bottom: $spacing-xs;
        color: $text-color;
      }
      
      .product-specs {
        font-size: 14px;
        color: $text-lighter;
      }
    }
  }
  
  .price {
    .current-price {
      font-weight: 600;
      color: $text-color;
    }
    
    .original-price {
      margin-left: $spacing-xs;
      font-size: 12px;
      color: $text-lighter;
      text-decoration: line-through;
    }
  }
  
  .quantity {
    font-weight: 600;
  }
  
  .subtotal {
    font-weight: 600;
    color: $primary-color;
  }
}

// 配送方式样式
.delivery-options {
  .fee {
    color: $danger-color;
    font-weight: 600;
  }
}

// 支付方式样式
.payment-options {
  .el-icon {
    margin-right: $spacing-xs;
    font-size: 18px;
    vertical-align: middle;
  }
}

// 优惠券样式
.coupon-options {
  width: 100%;
  max-width: 300px;
  
  .coupon-option {
    display: flex;
    justify-content: space-between;
    align-items: center;
    width: 100%;
    
    .coupon-condition {
      color: $warning-color;
      font-size: 12px;
    }
  }
}

// 发票样式
.invoice-form {
  margin-top: $spacing-md;
  max-width: 500px;
}

// 订单底部
.checkout-footer {
  background-color: $bg-white;
  border-radius: $border-radius;
  box-shadow: $box-shadow;
  padding: $spacing-lg;
  margin-top: $spacing-xl;
  
  display: flex;
  justify-content: space-between;
  align-items: flex-end;
  
  @media (max-width: $breakpoint-md) {
    flex-direction: column;
    align-items: stretch;
    gap: $spacing-lg;
  }
}

.order-summary {
  .summary-item {
    display: flex;
    justify-content: flex-end;
    margin-bottom: $spacing-xs;
    
    .summary-label {
      color: $text-light;
      margin-right: $spacing-md;
    }
    
    .summary-value {
      font-weight: 600;
      min-width: 100px;
      text-align: right;
      
      &.discount {
        color: $success-color;
      }
    }
    
    &.total {
      margin-top: $spacing-md;
      font-size: 18px;
      
      .total-price {
        color: $danger-color;
      }
    }
  }
}

.order-actions {
  display: flex;
  gap: $spacing-md;
  
  @media (max-width: $breakpoint-md) {
    justify-content: flex-end;
  }
  
  .el-button {
    min-width: 120px;
  }
}
</style>