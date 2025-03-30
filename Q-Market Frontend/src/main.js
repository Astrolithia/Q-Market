import { createApp } from 'vue'
import { createPinia } from 'pinia'
import ElementPlus from 'element-plus'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import 'element-plus/dist/index.css'
import './assets/css/main.scss'

import App from './App.vue'
import router from './router'

const app = createApp(App)

// 注册所有Element Plus图标
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component)
}

app.use(createPinia())
app.use(router)
app.use(ElementPlus)

// 全局挂载Element Plus消息组件
app.config.globalProperties.$message = ElMessage
app.config.globalProperties.$msgbox = ElMessageBox

app.mount('#app') 