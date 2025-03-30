<script setup>
import { ref, reactive } from 'vue'
import { useRouter, useRoute } from 'vue-router'
import { useUserStore } from '@/stores/user'
import { loginUser } from '@/mock/data.js'

const router = useRouter()
const route = useRoute()
const userStore = useUserStore()
const activeTab = ref('login')

// 登录表单数据
const loginForm = reactive({
  username: '',
  password: '',
  remember: false
})

// 注册表单数据
const registerForm = reactive({
  username: '',
  password: '',
  confirmPassword: '',
  email: '',
  phone: '',
  agreement: false
})

// 忘记密码表单数据
const forgotForm = reactive({
  email: ''
})

// 登录表单验证规则
const loginRules = {
  username: [
    { required: true, message: '请输入用户名', trigger: 'blur' },
    { min: 3, max: 20, message: '用户名长度应为3-20个字符', trigger: 'blur' }
  ],
  password: [
    { required: true, message: '请输入密码', trigger: 'blur' },
    { min: 6, max: 20, message: '密码长度应为6-20个字符', trigger: 'blur' }
  ]
}

// 注册表单验证规则
const registerRules = {
  username: [
    { required: true, message: '请输入用户名', trigger: 'blur' },
    { min: 3, max: 20, message: '用户名长度应为3-20个字符', trigger: 'blur' }
  ],
  password: [
    { required: true, message: '请输入密码', trigger: 'blur' },
    { min: 6, max: 20, message: '密码长度应为6-20个字符', trigger: 'blur' }
  ],
  confirmPassword: [
    { required: true, message: '请确认密码', trigger: 'blur' },
    {
      validator: (rule, value, callback) => {
        if (value !== registerForm.password) {
          callback(new Error('两次输入密码不一致'))
        } else {
          callback()
        }
      },
      trigger: 'blur'
    }
  ],
  email: [
    { required: true, message: '请输入邮箱', trigger: 'blur' },
    { type: 'email', message: '请输入有效的邮箱地址', trigger: 'blur' }
  ],
  phone: [
    { required: true, message: '请输入手机号', trigger: 'blur' },
    { pattern: /^1[3-9]\d{9}$/, message: '请输入有效的手机号', trigger: 'blur' }
  ],
  agreement: [
    {
      validator: (rule, value, callback) => {
        if (!value) {
          callback(new Error('请同意用户协议'))
        } else {
          callback()
        }
      },
      trigger: 'change'
    }
  ]
}

// 忘记密码表单验证规则
const forgotRules = {
  email: [
    { required: true, message: '请输入邮箱', trigger: 'blur' },
    { type: 'email', message: '请输入有效的邮箱地址', trigger: 'blur' }
  ]
}

// 登录表单引用
const loginFormRef = ref(null)
// 注册表单引用
const registerFormRef = ref(null)
// 忘记密码表单引用
const forgotFormRef = ref(null)

const loading = ref(false)
const loginError = ref('')

// 登录处理
const handleLogin = async () => {
  if (!loginFormRef.value) return
  
  try {
    await loginFormRef.value.validate()
    
    loading.value = true
    loginError.value = ''
    
    // 使用mock数据进行登录验证
    const result = loginUser(loginForm.username, loginForm.password)
    
    if (result.success) {
      // 登录成功，存储用户信息到store
      userStore.login(result.data.userInfo, result.data.token)
      
      // 跳转到之前的页面或首页
      const redirectUrl = route.query.redirect || '/'
      router.push(redirectUrl)
      
      ElMessage({
        type: 'success',
        message: '登录成功'
      })
    } else {
      // 登录失败
      loginError.value = result.message
      ElMessage({
        type: 'error',
        message: result.message
      })
    }
  } catch (error) {
    console.error('表单验证失败', error)
  } finally {
    loading.value = false
  }
}

// 注册处理
const handleRegister = () => {
  registerFormRef.value.validate((valid) => {
    if (valid) {
      // 这里应该调用注册API
      ElMessage.success('注册成功，请登录')
      activeTab.value = 'login'
      resetForms()
    }
  })
}

// 发送重置密码邮件
const sendResetEmail = () => {
  forgotFormRef.value.validate((valid) => {
    if (valid) {
      // 这里应该调用发送重置密码邮件API
      ElMessage.success(`重置密码链接已发送至 ${forgotForm.email}`)
      activeTab.value = 'login'
      resetForms()
    }
  })
}

// 重置所有表单
const resetForms = () => {
  if (loginFormRef.value) loginFormRef.value.resetFields()
  if (registerFormRef.value) registerFormRef.value.resetFields()
  if (forgotFormRef.value) forgotFormRef.value.resetFields()
}

// 切换到忘记密码表单
const showForgotPassword = () => {
  activeTab.value = 'forgot'
}

// 切换到登录表单
const backToLogin = () => {
  activeTab.value = 'login'
}

// 第三方登录
const thirdPartyLogin = (platform) => {
  ElMessage.info(`正在使用${platform}登录...`)
  // 这里应该实现第三方登录逻辑
}

// 去注册页面
const goToRegister = () => {
  router.push('/register')
}
</script>

<template>
  <div class="login-page">
    <div class="container">
      <div class="login-container">
        <div class="login-header">
          <img src="@/assets/images/logo.svg" alt="Q-Market Logo" class="logo" @click="router.push('/')" />
          <h1 class="login-title">欢迎来到 Q-Market</h1>
        </div>
        
        <!-- 登录/注册表单 -->
        <el-tabs v-model="activeTab" class="login-tabs">
          <!-- 登录表单 -->
          <el-tab-pane label="登录" name="login">
            <el-form
              ref="loginFormRef"
              :model="loginForm"
              :rules="loginRules"
              label-position="top"
            >
              <el-form-item label="用户名" prop="username">
                <el-input
                  v-model="loginForm.username"
                  placeholder="请输入用户名"
                  prefix-icon="User"
                />
              </el-form-item>
              
              <el-form-item label="密码" prop="password">
                <el-input
                  v-model="loginForm.password"
                  type="password"
                  placeholder="请输入密码"
                  prefix-icon="Lock"
                  show-password
                />
              </el-form-item>
              
              <div class="form-options">
                <el-checkbox v-model="loginForm.remember">记住我</el-checkbox>
                <el-button type="text" @click="showForgotPassword">忘记密码?</el-button>
              </div>
              
              <el-form-item>
                <el-button type="primary" class="submit-btn" @click="handleLogin">
                  登录
                </el-button>
              </el-form-item>
              
              <div class="third-party-login">
                <div class="divider">
                  <span>使用第三方账号登录</span>
                </div>
                <div class="third-party-buttons">
                  <el-button circle @click="thirdPartyLogin('微信')">
                    <el-icon><ChatDotRound /></el-icon>
                  </el-button>
                  <el-button circle @click="thirdPartyLogin('QQ')">QQ</el-button>
                  <el-button circle @click="thirdPartyLogin('微博')">
                    <el-icon><Share /></el-icon>
                  </el-button>
                </div>
              </div>
            </el-form>
          </el-tab-pane>
          
          <!-- 注册表单 -->
          <el-tab-pane label="注册" name="register">
            <el-form
              ref="registerFormRef"
              :model="registerForm"
              :rules="registerRules"
              label-position="top"
            >
              <el-form-item label="用户名" prop="username">
                <el-input
                  v-model="registerForm.username"
                  placeholder="请输入用户名"
                  prefix-icon="User"
                />
              </el-form-item>
              
              <el-form-item label="邮箱" prop="email">
                <el-input
                  v-model="registerForm.email"
                  placeholder="请输入邮箱"
                  prefix-icon="Message"
                />
              </el-form-item>
              
              <el-form-item label="手机号" prop="phone">
                <el-input
                  v-model="registerForm.phone"
                  placeholder="请输入手机号"
                  prefix-icon="Phone"
                />
              </el-form-item>
              
              <el-form-item label="密码" prop="password">
                <el-input
                  v-model="registerForm.password"
                  type="password"
                  placeholder="请输入密码"
                  prefix-icon="Lock"
                  show-password
                />
              </el-form-item>
              
              <el-form-item label="确认密码" prop="confirmPassword">
                <el-input
                  v-model="registerForm.confirmPassword"
                  type="password"
                  placeholder="请确认密码"
                  prefix-icon="Lock"
                  show-password
                />
              </el-form-item>
              
              <el-form-item prop="agreement">
                <el-checkbox v-model="registerForm.agreement">
                  我已阅读并同意 <el-button type="text">用户协议</el-button> 和 <el-button type="text">隐私政策</el-button>
                </el-checkbox>
              </el-form-item>
              
              <el-form-item>
                <el-button type="primary" class="submit-btn" @click="handleRegister">
                  注册
                </el-button>
              </el-form-item>
            </el-form>
          </el-tab-pane>
          
          <!-- 忘记密码表单 -->
          <el-tab-pane label="忘记密码" name="forgot" :disabled="true">
            <div class="back-link">
              <el-button type="text" @click="backToLogin">
                <el-icon><ArrowLeft /></el-icon> 返回登录
              </el-button>
            </div>
            
            <h2 class="forgot-title">找回密码</h2>
            <p class="forgot-desc">请输入您的邮箱，我们将向您发送重置密码的链接。</p>
            
            <el-form
              ref="forgotFormRef"
              :model="forgotForm"
              :rules="forgotRules"
              label-position="top"
            >
              <el-form-item label="邮箱" prop="email">
                <el-input
                  v-model="forgotForm.email"
                  placeholder="请输入邮箱"
                  prefix-icon="Message"
                />
              </el-form-item>
              
              <el-form-item>
                <el-button type="primary" class="submit-btn" @click="sendResetEmail">
                  发送重置链接
                </el-button>
              </el-form-item>
            </el-form>
          </el-tab-pane>
        </el-tabs>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
@import '@/assets/css/variables.scss';

.login-page {
  min-height: calc(100vh - 60px);
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: $bg-color;
  padding: $spacing-xl 0;
}

.login-container {
  background-color: $bg-white;
  border-radius: $border-radius;
  box-shadow: $box-shadow;
  padding: $spacing-xl;
  width: 100%;
  max-width: 500px;
}

.login-header {
  text-align: center;
  margin-bottom: $spacing-xl;
  
  .logo {
    height: 60px;
    margin-bottom: $spacing-md;
    cursor: pointer;
  }
  
  .login-title {
    font-size: 24px;
    font-weight: 600;
    color: $text-color;
  }
}

.login-tabs {
  width: 100%;
}

.form-options {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: $spacing-md;
}

.submit-btn {
  width: 100%;
  height: 40px;
  font-size: 16px;
}

.third-party-login {
  margin-top: $spacing-lg;
  
  .divider {
    display: flex;
    align-items: center;
    margin: $spacing-md 0;
    
    &::before,
    &::after {
      content: '';
      flex-grow: 1;
      height: 1px;
      background-color: $border-light;
    }
    
    span {
      padding: 0 $spacing-md;
      color: $text-light;
      font-size: 14px;
    }
  }
  
  .third-party-buttons {
    display: flex;
    justify-content: center;
    gap: $spacing-lg;
    margin-top: $spacing-md;
  }
}

.back-link {
  margin-bottom: $spacing-md;
}

.forgot-title {
  font-size: 20px;
  font-weight: 600;
  margin-bottom: $spacing-sm;
  color: $text-color;
}

.forgot-desc {
  color: $text-light;
  margin-bottom: $spacing-lg;
}
</style> 