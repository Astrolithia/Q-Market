import { defineStore } from 'pinia'
import { ref, computed } from 'vue'

export const useUserStore = defineStore('user', () => {
  // 用户信息
  const userInfo = ref(null)
  // 登录状态
  const token = ref('')
  
  // 从本地存储加载用户信息
  const loadUserInfo = () => {
    const savedUserInfo = localStorage.getItem('userInfo')
    const savedToken = localStorage.getItem('token')
    
    if (savedUserInfo) {
      try {
        userInfo.value = JSON.parse(savedUserInfo)
      } catch (e) {
        userInfo.value = null
      }
    }
    
    if (savedToken) {
      token.value = savedToken
    }
  }
  
  // 保存用户信息到本地存储
  const saveUserInfo = () => {
    if (userInfo.value) {
      localStorage.setItem('userInfo', JSON.stringify(userInfo.value))
    } else {
      localStorage.removeItem('userInfo')
    }
    
    if (token.value) {
      localStorage.setItem('token', token.value)
    } else {
      localStorage.removeItem('token')
    }
  }
  
  // 初始化
  loadUserInfo()
  
  // 登录
  const login = (userData, userToken) => {
    userInfo.value = userData
    token.value = userToken
    saveUserInfo()
  }
  
  // 登出
  const logout = () => {
    userInfo.value = null
    token.value = ''
    saveUserInfo()
  }
  
  // 更新用户信息
  const updateUserInfo = (data) => {
    userInfo.value = { ...userInfo.value, ...data }
    saveUserInfo()
  }
  
  // 计算属性：是否已登录
  const isLoggedIn = computed(() => !!token.value)
  
  // 计算属性：用户名
  const username = computed(() => userInfo.value?.username || '')
  
  // 计算属性：用户头像
  const avatar = computed(() => userInfo.value?.avatar || '')
  
  // 计算属性：用户角色
  const roles = computed(() => userInfo.value?.roles || [])
  
  // 计算属性：用户权限
  const permissions = computed(() => userInfo.value?.permissions || [])
  
  // 检查用户是否有指定角色
  const hasRole = (role) => {
    if (!isLoggedIn.value) return false
    if (roles.value.includes('admin')) return true
    return roles.value.includes(role)
  }
  
  // 检查用户是否有指定权限
  const hasPermission = (permission) => {
    if (!isLoggedIn.value) return false
    if (roles.value.includes('admin')) return true
    return permissions.value.includes(permission)
  }
  
  // 重置密码
  const resetPassword = (oldPassword, newPassword) => {
    // 这里应该调用重置密码API
    return new Promise((resolve) => {
      // 模拟API调用
      setTimeout(() => {
        resolve({ success: true })
      }, 500)
    })
  }
  
  // 更新用户头像
  const updateAvatar = (avatarUrl) => {
    if (userInfo.value) {
      userInfo.value.avatar = avatarUrl
      saveUserInfo()
    }
  }
  
  return {
    userInfo,
    token,
    isLoggedIn,
    username,
    avatar,
    roles,
    permissions,
    login,
    logout,
    updateUserInfo,
    hasRole,
    hasPermission,
    resetPassword,
    updateAvatar
  }
}) 