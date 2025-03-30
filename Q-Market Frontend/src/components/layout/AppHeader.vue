<script setup>
import { ref, computed } from 'vue'
import { useRouter } from 'vue-router'
import { useUserStore } from '@/stores/user'
import { useCartStore } from '@/stores/cart'
import SearchBar from './SearchBar.vue'

const router = useRouter()
const userStore = useUserStore()
const cartStore = useCartStore()
const isMenuOpen = ref(false)

// 使用store中的登录状态替代本地变量
// const isLoggedIn = ref(false) // 删除这行
const isLoggedIn = computed(() => userStore.isLoggedIn) // 改为计算属性

// 从store获取用户信息
const userInfo = computed(() => ({
  name: userStore.username || userStore.userInfo?.nickname || '用户',
  avatar: userStore.avatar || 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png'
}))

// 从cart store获取购物车数量
const cartCount = computed(() => cartStore.totalItems)

const toggleMenu = () => {
  isMenuOpen.value = !isMenuOpen.value
}

const handleLogin = () => {
  router.push('/login')
}

const handleRegister = () => {
  router.push('/register')
}

const goToCart = () => {
  router.push('/cart')
}

// 添加登出方法
const handleLogout = () => {
  userStore.logout()
  router.push('/')
}

const goToProfile = () => {
  router.push('/profile')
}
</script>

<template>
  <header class="app-header">
    <div class="container">
      <div class="header-content">
        <!-- Logo -->
        <div class="logo" @click="router.push('/')">
          <img src="@/assets/images/logo.svg" alt="Q-Market Logo" height="40" />
          <span class="logo-text">Q-Market</span>
        </div>

        <!-- 导航菜单 - 桌面端 -->
        <nav class="nav-menu hidden-xs">
          <router-link to="/" class="nav-item">首页</router-link>
          <router-link to="/products" class="nav-item">商品</router-link>
          <router-link to="/categories" class="nav-item">分类</router-link>
          <router-link to="/new" class="nav-item">新品</router-link>
          <router-link to="/promotions" class="nav-item">特惠</router-link>
        </nav>

        <!-- 搜索框 -->
        <div class="search-container hidden-xs">
          <SearchBar />
        </div>

        <!-- 用户操作区 -->
        <div class="user-actions">
          <!-- 购物车 -->
          <div class="cart-icon" @click="goToCart">
            <el-badge :value="cartCount" :hidden="cartCount === 0">
              <el-icon size="24"><ShoppingCart /></el-icon>
            </el-badge>
          </div>

          <!-- 用户信息/登录按钮 -->
          <template v-if="isLoggedIn">
            <el-dropdown trigger="click">
              <div class="user-avatar">
                <el-avatar :size="32" :src="userInfo.avatar" />
              </div>
              <template #dropdown>
                <el-dropdown-menu>
                  <el-dropdown-item @click="goToProfile">
                    <el-icon><User /></el-icon> 个人中心
                  </el-dropdown-item>
                  <el-dropdown-item @click="router.push('/orders')">
                    <el-icon><List /></el-icon> 我的订单
                  </el-dropdown-item>
                  <el-dropdown-item @click="router.push('/favorites')">
                    <el-icon><Star /></el-icon> 我的收藏
                  </el-dropdown-item>
                  <el-dropdown-item divided @click="handleLogout">
                    <el-icon><SwitchButton /></el-icon> 退出登录
                  </el-dropdown-item>
                </el-dropdown-menu>
              </template>
            </el-dropdown>
          </template>
          <template v-else>
            <el-button type="text" @click="handleLogin">登录</el-button>
            <el-button type="primary" @click="handleRegister">注册</el-button>
          </template>

          <!-- 移动端菜单按钮 -->
          <div class="menu-toggle visible-xs" @click="toggleMenu">
            <el-icon size="24"><Menu /></el-icon>
          </div>
        </div>
      </div>

      <!-- 移动端导航菜单 -->
      <div class="mobile-menu" :class="{ 'is-open': isMenuOpen }">
        <div class="mobile-search">
          <SearchBar />
        </div>
        <nav class="mobile-nav">
          <router-link to="/" class="nav-item" @click="isMenuOpen = false">首页</router-link>
          <router-link to="/products" class="nav-item" @click="isMenuOpen = false">商品</router-link>
          <router-link to="/categories" class="nav-item" @click="isMenuOpen = false">分类</router-link>
          <router-link to="/new" class="nav-item" @click="isMenuOpen = false">新品</router-link>
          <router-link to="/promotions" class="nav-item" @click="isMenuOpen = false">特惠</router-link>
        </nav>
        <div class="mobile-auth" v-if="!isLoggedIn">
          <el-button type="primary" @click="handleLogin">登录</el-button>
          <el-button @click="handleRegister">注册</el-button>
        </div>
      </div>
    </div>
  </header>
</template>

<style lang="scss" scoped>
@import '@/assets/css/variables.scss';

.app-header {
  background-color: $bg-white;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  z-index: 1000;
}

.header-content {
  height: 60px;
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.logo {
  display: flex;
  align-items: center;
  cursor: pointer;
  
  .logo-text {
    font-size: 20px;
    font-weight: 600;
    color: $primary-color;
    margin-left: $spacing-sm;
  }
}

.nav-menu {
  display: flex;
  margin-left: $spacing-xl;
  
  .nav-item {
    padding: 0 $spacing-md;
    color: $text-color;
    text-decoration: none;
    font-weight: 500;
    height: 60px;
    display: flex;
    align-items: center;
    position: relative;
    
    &::after {
      content: '';
      position: absolute;
      bottom: 0;
      left: 50%;
      width: 0;
      height: 3px;
      background-color: $primary-color;
      transition: all $transition-time;
      transform: translateX(-50%);
    }
    
    &:hover, &.router-link-active {
      color: $primary-color;
      
      &::after {
        width: 70%;
      }
    }
  }
}

.search-container {
  margin: 0 $spacing-xl;
  width: 300px;
}

.user-actions {
  display: flex;
  align-items: center;
  
  .cart-icon {
    margin-right: $spacing-lg;
    cursor: pointer;
    transition: all $transition-time;
    
    &:hover {
      color: $primary-color;
      transform: scale(1.1);
    }
  }
  
  .user-avatar {
    cursor: pointer;
  }
  
  .menu-toggle {
    cursor: pointer;
  }
}

.mobile-menu {
  background-color: $bg-white;
  padding: $spacing-md;
  display: none;
  flex-direction: column;
  transform: translateY(-100%);
  transition: transform $transition-time;
  
  &.is-open {
    transform: translateY(0);
    display: flex;
  }
  
  .mobile-search {
    margin-bottom: $spacing-md;
  }
  
  .mobile-nav {
    display: flex;
    flex-direction: column;
    
    .nav-item {
      padding: $spacing-md 0;
      color: $text-color;
      text-decoration: none;
      font-weight: 500;
      border-bottom: 1px solid $border-light;
      
      &:hover, &.router-link-active {
        color: $primary-color;
      }
    }
  }
  
  .mobile-auth {
    margin-top: $spacing-lg;
    display: flex;
    gap: $spacing-md;
    
    .el-button {
      flex: 1;
    }
  }
}

@media (max-width: $breakpoint-md) {
  .search-container {
    width: 200px;
    margin: 0 $spacing-md;
  }
}
</style> 