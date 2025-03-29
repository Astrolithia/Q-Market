<script setup>
import { ref, reactive, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { useUserStore } from '@/stores/user'

const router = useRouter()
const userStore = useUserStore()

// 激活的标签页
const activeTab = ref('profile')

// 用户信息表单
const userForm = reactive({
  username: userStore.username,
  nickname: userStore.userInfo?.nickname || '',
  email: userStore.userInfo?.email || '',
  phone: userStore.userInfo?.phone || '',
  gender: userStore.userInfo?.gender || '',
  birthday: userStore.userInfo?.birthday || '',
  avatar: userStore.avatar
})

// 修改密码表单
const passwordForm = reactive({
  oldPassword: '',
  newPassword: '',
  confirmPassword: ''
})

// 我的订单列表
const orders = ref([])
// 我的收藏列表
const favorites = ref([])
// 我的地址列表
const addresses = ref([])

// 加载中状态
const loading = ref({
  profile: false,
  orders: false,
  favorites: false,
  addresses: false
})

// 订单状态枚举
const orderStatus = {
  0: '待付款',
  1: '待发货',
  2: '待收货',
  3: '已完成',
  4: '已取消',
  5: '退款中',
  6: '已退款'
}

// 订单状态颜色映射
const orderStatusColor = {
  0: 'warning',
  1: 'primary',
  2: 'info',
  3: 'success',
  4: 'info',
  5: 'danger',
  6: 'info'
}

// 加载订单数据
const loadOrders = () => {
  loading.value.orders = true
  
  // 模拟API请求
  setTimeout(() => {
    orders.value = [
      {
        id: 'ORD20230512001',
        date: '2023-05-12 14:23:45',
        status: 3,
        total: 5999,
        items: [
          {
            id: 1,
            name: '高端智能手机 Pro Max',
            price: 5999,
            quantity: 1,
            image: 'https://picsum.photos/id/1/100/100'
          }
        ]
      },
      {
        id: 'ORD20230423002',
        date: '2023-04-23 09:12:34',
        status: 2,
        total: 2599,
        items: [
          {
            id: 3,
            name: '智能手表 Series 7',
            price: 2599,
            quantity: 1,
            image: 'https://picsum.photos/id/3/100/100'
          }
        ]
      },
      {
        id: 'ORD20230318003',
        date: '2023-03-18 16:45:22',
        status: 0,
        total: 799,
        items: [
          {
            id: 6,
            name: '无线蓝牙耳机',
            price: 799,
            quantity: 1,
            image: 'https://picsum.photos/id/6/100/100'
          }
        ]
      }
    ]
    
    loading.value.orders = false
  }, 500)
}

// 加载收藏数据
const loadFavorites = () => {
  loading.value.favorites = true
  
  // 模拟API请求
  setTimeout(() => {
    favorites.value = [
      {
        id: 1,
        name: '高端智能手机 Pro Max',
        price: 5999,
        originalPrice: 6999,
        image: 'https://picsum.photos/id/1/300/300',
        category: '电子产品',
        rating: 4.9,
        reviewCount: 128
      },
      {
        id: 3,
        name: '智能手表 Series 7',
        price: 2599,
        originalPrice: 2999,
        image: 'https://picsum.photos/id/3/300/300',
        category: '电子产品',
        rating: 4.8,
        reviewCount: 89
      },
      {
        id: 7,
        name: '专业游戏笔记本电脑',
        price: 8999,
        originalPrice: 9999,
        image: 'https://picsum.photos/id/7/300/300',
        category: '电子产品',
        rating: 4.7,
        reviewCount: 56
      },
      {
        id: 12,
        name: '高端无线键鼠套装',
        price: 899,
        originalPrice: 999,
        image: 'https://picsum.photos/id/12/300/300',
        category: '电子产品',
        rating: 4.6,
        reviewCount: 42
      }
    ]
    
    loading.value.favorites = false
  }, 500)
}

// 加载地址数据
const loadAddresses = () => {
  loading.value.addresses = true
  
  // 模拟API请求
  setTimeout(() => {
    addresses.value = [
      {
        id: 1,
        name: '张三',
        phone: '13812345678',
        province: '北京市',
        city: '北京市',
        district: '海淀区',
        address: '中关村南大街5号',
        isDefault: true
      },
      {
        id: 2,
        name: '李四',
        phone: '13987654321',
        province: '上海市',
        city: '上海市',
        district: '浦东新区',
        address: '张江高科技园区博云路2号',
        isDefault: false
      }
    ]
    
    loading.value.addresses = false
  }, 500)
}

// 更新用户信息
const updateProfile = () => {
  loading.value.profile = true
  
  // 模拟API请求
  setTimeout(() => {
    userStore.updateUserInfo({
      nickname: userForm.nickname,
      email: userForm.email,
      phone: userForm.phone,
      gender: userForm.gender,
      birthday: userForm.birthday
    })
    
    ElMessage.success('个人信息更新成功')
    loading.value.profile = false
  }, 500)
}

// 修改密码
const changePassword = () => {
  if (passwordForm.newPassword !== passwordForm.confirmPassword) {
    ElMessage.error('两次输入的新密码不一致')
    return
  }
  
  loading.value.profile = true
  
  userStore.resetPassword(passwordForm.oldPassword, passwordForm.newPassword)
    .then(res => {
      if (res.success) {
        ElMessage.success('密码修改成功，请重新登录')
        passwordForm.oldPassword = ''
        passwordForm.newPassword = ''
        passwordForm.confirmPassword = ''
        
        // 延迟登出，让用户看到成功提示
        setTimeout(() => {
          userStore.logout()
          router.push('/login')
        }, 1500)
      }
    })
    .finally(() => {
      loading.value.profile = false
    })
}

// 上传头像
const handleAvatarSuccess = (res) => {
  userForm.avatar = res.url
  userStore.updateAvatar(res.url)
  ElMessage.success('头像上传成功')
}

// 查看订单详情
const viewOrder = (orderId) => {
  router.push(`/order/${orderId}`)
}

// 前往商品详情
const goToProduct = (productId) => {
  router.push(`/product/${productId}`)
}

// 移除收藏
const removeFavorite = (productId) => {
  ElMessageBox.confirm('确定从收藏中移除该商品吗?', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(() => {
    favorites.value = favorites.value.filter(item => item.id !== productId)
    ElMessage.success('已从收藏中移除')
  }).catch(() => {})
}

// 设为默认地址
const setDefaultAddress = (addressId) => {
  addresses.value.forEach(address => {
    address.isDefault = address.id === addressId
  })
  ElMessage.success('默认地址设置成功')
}

// 编辑地址
const editAddress = (address) => {
  // 这里应该打开编辑地址的对话框
  ElMessage.info('编辑地址功能开发中...')
}

// 删除地址
const deleteAddress = (addressId) => {
  ElMessageBox.confirm('确定删除该地址吗?', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(() => {
    addresses.value = addresses.value.filter(address => address.id !== addressId)
    ElMessage.success('地址删除成功')
  }).catch(() => {})
}

// 添加新地址
const addNewAddress = () => {
  // 这里应该打开添加地址的对话框
  ElMessage.info('添加地址功能开发中...')
}

// 标签页切换处理
const handleTabChange = (tab) => {
  // 根据激活的标签页加载相应的数据
  if (tab.props.name === 'orders' && orders.value.length === 0) {
    loadOrders()
  } else if (tab.props.name === 'favorites' && favorites.value.length === 0) {
    loadFavorites()
  } else if (tab.props.name === 'addresses' && addresses.value.length === 0) {
    loadAddresses()
  }
}

onMounted(() => {
  // 初始加载个人信息页数据
  loadOrders()
})
</script>

<template>
  <div class="profile-page">
    <div class="container">
      <h1 class="page-title">个人中心</h1>
      
      <el-tabs v-model="activeTab" class="profile-tabs" @tab-change="handleTabChange">
        <!-- 个人资料 -->
        <el-tab-pane label="个人资料" name="profile">
          <div class="profile-content">
            <el-card class="profile-card">
              <template #header>
                <div class="card-header">
                  <span>基本信息</span>
                </div>
              </template>
              
              <el-form :model="userForm" label-width="80px">
                <el-form-item label="头像">
                  <el-avatar :size="100" :src="userForm.avatar">
                    <el-icon><User /></el-icon>
                  </el-avatar>
                  <div class="avatar-uploader">
                    <el-upload
                      class="avatar-upload"
                      action="/api/upload/avatar"
                      :show-file-list="false"
                      :on-success="handleAvatarSuccess"
                    >
                      <el-button size="small" type="primary">上传头像</el-button>
                    </el-upload>
                  </div>
                </el-form-item>
                
                <el-form-item label="用户名">
                  <el-input v-model="userForm.username" disabled></el-input>
                </el-form-item>
                
                <el-form-item label="昵称">
                  <el-input v-model="userForm.nickname" placeholder="设置您的昵称"></el-input>
                </el-form-item>
                
                <el-form-item label="邮箱">
                  <el-input v-model="userForm.email" placeholder="设置您的邮箱"></el-input>
                </el-form-item>
                
                <el-form-item label="手机号">
                  <el-input v-model="userForm.phone" placeholder="设置您的手机号"></el-input>
                </el-form-item>
                
                <el-form-item label="性别">
                  <el-radio-group v-model="userForm.gender">
                    <el-radio label="male">男</el-radio>
                    <el-radio label="female">女</el-radio>
                    <el-radio label="other">保密</el-radio>
                  </el-radio-group>
                </el-form-item>
                
                <el-form-item label="生日">
                  <el-date-picker
                    v-model="userForm.birthday"
                    type="date"
                    placeholder="选择您的生日"
                    format="YYYY/MM/DD"
                  ></el-date-picker>
                </el-form-item>
                
                <el-form-item>
                  <el-button type="primary" @click="updateProfile" :loading="loading.profile">
                    保存修改
                  </el-button>
                </el-form-item>
              </el-form>
            </el-card>
            
            <el-card class="profile-card">
              <template #header>
                <div class="card-header">
                  <span>修改密码</span>
                </div>
              </template>
              
              <el-form :model="passwordForm" label-width="100px">
                <el-form-item label="当前密码">
                  <el-input
                    v-model="passwordForm.oldPassword"
                    type="password"
                    placeholder="请输入当前密码"
                    show-password
                  ></el-input>
                </el-form-item>
                
                <el-form-item label="新密码">
                  <el-input
                    v-model="passwordForm.newPassword"
                    type="password"
                    placeholder="请输入新密码"
                    show-password
                  ></el-input>
                </el-form-item>
                
                <el-form-item label="确认新密码">
                  <el-input
                    v-model="passwordForm.confirmPassword"
                    type="password"
                    placeholder="请确认新密码"
                    show-password
                  ></el-input>
                </el-form-item>
                
                <el-form-item>
                  <el-button type="primary" @click="changePassword" :loading="loading.profile">
                    修改密码
                  </el-button>
                </el-form-item>
              </el-form>
            </el-card>
          </div>
        </el-tab-pane>
        
        <!-- 我的订单 -->
        <el-tab-pane label="我的订单" name="orders">
          <div class="orders-content">
            <el-card v-loading="loading.orders">
              <template #header>
                <div class="card-header">
                  <span>我的订单</span>
                </div>
              </template>
              
              <div v-if="orders.length === 0 && !loading.orders" class="empty-data">
                <el-empty description="暂无订单"></el-empty>
              </div>
              
              <div v-else class="order-list">
                <div v-for="order in orders" :key="order.id" class="order-item">
                  <div class="order-header">
                    <div class="order-info">
                      <span class="order-id">订单号: {{ order.id }}</span>
                      <span class="order-date">下单时间: {{ order.date }}</span>
                    </div>
                    <div class="order-status">
                      <el-tag :type="orderStatusColor[order.status]">
                        {{ orderStatus[order.status] }}
                      </el-tag>
                    </div>
                  </div>
                  
                  <div class="order-body">
                    <div v-for="item in order.items" :key="item.id" class="order-product">
                      <img :src="item.image" :alt="item.name" class="product-image" @click="goToProduct(item.id)" />
                      <div class="product-info">
                        <div class="product-name" @click="goToProduct(item.id)">{{ item.name }}</div>
                        <div class="product-price">¥{{ item.price.toFixed(2) }} × {{ item.quantity }}</div>
                      </div>
                    </div>
                  </div>
                  
                  <div class="order-footer">
                    <div class="order-total">
                      <span>订单总额: </span>
                      <span class="total-price">¥{{ order.total.toFixed(2) }}</span>
                    </div>
                    <div class="order-actions">
                      <el-button type="primary" size="small" @click="viewOrder(order.id)">查看详情</el-button>
                      <el-button v-if="order.status === 0" type="danger" size="small">去付款</el-button>
                      <el-button v-if="order.status === 2" type="success" size="small">确认收货</el-button>
                      <el-button v-if="order.status === 3" type="info" size="small">评价</el-button>
                    </div>
                  </div>
                </div>
              </div>
            </el-card>
          </div>
        </el-tab-pane>
        
        <!-- 我的收藏 -->
        <el-tab-pane label="我的收藏" name="favorites">
          <div class="favorites-content">
            <el-card v-loading="loading.favorites">
              <template #header>
                <div class="card-header">
                  <span>我的收藏</span>
                </div>
              </template>
              
              <div v-if="favorites.length === 0 && !loading.favorites" class="empty-data">
                <el-empty description="暂无收藏商品"></el-empty>
              </div>
              
              <div v-else class="favorites-list">
                <el-row :gutter="20">
                  <el-col :span="6" v-for="product in favorites" :key="product.id">
                    <div class="favorite-item">
                      <img :src="product.image" :alt="product.name" class="product-image" @click="goToProduct(product.id)" />
                      <div class="product-info">
                        <h3 class="product-name" @click="goToProduct(product.id)">{{ product.name }}</h3>
                        <div class="product-price">
                          <span class="current-price">¥{{ product.price.toFixed(2) }}</span>
                          <span v-if="product.originalPrice" class="original-price">¥{{ product.originalPrice.toFixed(2) }}</span>
                        </div>
                        <div class="product-rating">
                          <el-rate v-model="product.rating" disabled text-color="#ff9900" />
                          <span class="review-count">({{ product.reviewCount }})</span>
                        </div>
                      </div>
                      <div class="favorite-actions">
                        <el-button type="primary" size="small" @click.stop="goToProduct(product.id)">查看详情</el-button>
                        <el-button type="danger" size="small" @click.stop="removeFavorite(product.id)">取消收藏</el-button>
                      </div>
                    </div>
                  </el-col>
                </el-row>
              </div>
            </el-card>
          </div>
        </el-tab-pane>
        
        <!-- 收货地址 -->
        <el-tab-pane label="收货地址" name="addresses">
          <div class="addresses-content">
            <el-card v-loading="loading.addresses">
              <template #header>
                <div class="card-header">
                  <span>收货地址</span>
                  <el-button type="primary" size="small" @click="addNewAddress">新增地址</el-button>
                </div>
              </template>
              
              <div v-if="addresses.length === 0 && !loading.addresses" class="empty-data">
                <el-empty description="暂无收货地址">
                  <el-button type="primary" @click="addNewAddress">新增地址</el-button>
                </el-empty>
              </div>
              
              <div v-else class="address-list">
                <div v-for="address in addresses" :key="address.id" class="address-item">
                  <div class="address-info">
                    <div class="address-header">
                      <span class="address-name">{{ address.name }}</span>
                      <span class="address-phone">{{ address.phone }}</span>
                      <el-tag v-if="address.isDefault" type="success" size="small">默认</el-tag>
                    </div>
                    <div class="address-detail">
                      {{ address.province }} {{ address.city }} {{ address.district }} {{ address.address }}
                    </div>
                  </div>
                  <div class="address-actions">
                    <el-button type="text" @click="editAddress(address)">编辑</el-button>
                    <el-button type="text" @click="deleteAddress(address.id)">删除</el-button>
                    <el-button v-if="!address.isDefault" type="text" @click="setDefaultAddress(address.id)">设为默认</el-button>
                  </div>
                </div>
              </div>
            </el-card>
          </div>
        </el-tab-pane>
      </el-tabs>
    </div>
  </div>
</template>

<style lang="scss" scoped>
@import '@/assets/css/variables.scss';

.profile-page {
  padding: $spacing-xl 0;
}

.page-title {
  font-size: 28px;
  font-weight: 600;
  margin-bottom: $spacing-xl;
  color: $text-color;
}

.profile-tabs {
  background-color: $bg-white;
  border-radius: $border-radius;
  box-shadow: $box-shadow;
  padding: $spacing-lg;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-size: 18px;
  font-weight: 600;
  color: $text-color;
}

.profile-content {
  display: flex;
  gap: $spacing-lg;
  flex-wrap: wrap;
  
  .profile-card {
    flex: 1;
    min-width: 300px;
  }
}

.avatar-uploader {
  margin-top: $spacing-md;
}

.empty-data {
  padding: $spacing-xl 0;
  display: flex;
  justify-content: center;
}

// 订单列表样式
.order-list {
  .order-item {
    background-color: $bg-white;
    border-radius: $border-radius;
    box-shadow: $box-shadow;
    margin-bottom: $spacing-lg;
    overflow: hidden;
    
    .order-header {
      display: flex;
      justify-content: space-between;
      align-items: center;
      background-color: $bg-color;
      padding: $spacing-md;
      border-bottom: 1px solid $border-light;
      
      .order-info {
        .order-id {
          font-weight: 600;
          margin-right: $spacing-md;
        }
        
        .order-date {
          color: $text-light;
        }
      }
    }
    
    .order-body {
      padding: $spacing-md;
      
      .order-product {
        display: flex;
        margin-bottom: $spacing-md;
        
        &:last-child {
          margin-bottom: 0;
        }
        
        .product-image {
          width: 80px;
          height: 80px;
          object-fit: cover;
          border-radius: $border-radius-sm;
          cursor: pointer;
          margin-right: $spacing-md;
        }
        
        .product-info {
          .product-name {
            font-weight: 600;
            margin-bottom: $spacing-xs;
            cursor: pointer;
            
            &:hover {
              color: $primary-color;
            }
          }
          
          .product-price {
            color: $text-light;
          }
        }
      }
    }
    
    .order-footer {
      display: flex;
      justify-content: space-between;
      align-items: center;
      padding: $spacing-md;
      border-top: 1px solid $border-light;
      background-color: $bg-color;
      
      .order-total {
        .total-price {
          font-size: 18px;
          font-weight: 600;
          color: $danger-color;
        }
      }
      
      .order-actions {
        display: flex;
        gap: $spacing-sm;
      }
    }
  }
}

// 收藏列表样式
.favorites-list {
  .favorite-item {
    background-color: $bg-white;
    border-radius: $border-radius;
    box-shadow: $box-shadow;
    margin-bottom: $spacing-lg;
    overflow: hidden;
    transition: all $transition-time;
    
    &:hover {
      transform: translateY(-5px);
      box-shadow: $box-shadow-hover;
    }
    
    .product-image {
      width: 100%;
      height: 200px;
      object-fit: cover;
      cursor: pointer;
    }
    
    .product-info {
      padding: $spacing-md;
      
      .product-name {
        font-size: 16px;
        font-weight: 600;
        margin-bottom: $spacing-xs;
        cursor: pointer;
        display: -webkit-box;
        -webkit-line-clamp: 2;
        -webkit-box-orient: vertical;
        overflow: hidden;
        
        &:hover {
          color: $primary-color;
        }
      }
      
      .product-price {
        margin: $spacing-sm 0;
        
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
    
    .favorite-actions {
      display: flex;
      justify-content: space-between;
      padding: $spacing-sm $spacing-md $spacing-md;
      
      .el-button {
        flex: 1;
      }
    }
  }
}

// 地址列表样式
.address-list {
  .address-item {
    background-color: $bg-white;
    border-radius: $border-radius;
    box-shadow: $box-shadow;
    margin-bottom: $spacing-md;
    padding: $spacing-md;
    border: 1px solid $border-light;
    display: flex;
    justify-content: space-between;
    
    .address-info {
      .address-header {
        margin-bottom: $spacing-xs;
        
        .address-name {
          font-weight: 600;
          margin-right: $spacing-sm;
        }
        
        .address-phone {
          color: $text-light;
          margin-right: $spacing-sm;
        }
      }
      
      .address-detail {
        color: $text-light;
      }
    }
    
    .address-actions {
      display: flex;
      gap: $spacing-sm;
    }
  }
}
</style> 