import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView,
      meta: { title: '首页 - Q-Market' }
    },
    {
      path: '/products',
      name: 'products',
      component: () => import('../views/ProductListView.vue'),
      meta: { title: '商品列表 - Q-Market' }
    },
    {
      path: '/product/:id',
      name: 'product-detail',
      component: () => import('../views/ProductDetailView.vue'),
      meta: { title: '商品详情 - Q-Market' }
    },
    {
      path: '/cart',
      name: 'cart',
      component: () => import('../views/CartView.vue'),
      meta: { title: '购物车 - Q-Market' }
    },
    {
      path: '/login',
      name: 'login',
      component: () => import('../views/LoginView.vue'),
      meta: { title: '登录/注册 - Q-Market' }
    },
    {
      path: '/checkout',
      name: 'checkout',
      component: () => import('../views/CheckoutView.vue'),
      meta: { title: '结算 - Q-Market' }
    },
    {
      path: '/profile',
      name: 'profile',
      component: () => import('../views/ProfileView.vue'),
      meta: { title: '个人中心 - Q-Market', requiresAuth: true }
    },
    {
      path: '/about',
      name: 'about',
      component: () => import('../views/AboutView.vue'),
      meta: { title: '关于我们 - Q-Market' }
    },
    // 404页面，放在最后
    {
      path: '/:pathMatch(.*)*',
      name: 'not-found',
      component: () => import('../views/NotFoundView.vue'),
      meta: { title: '页面未找到 - Q-Market' }
    }
  ],
  scrollBehavior(to, from, savedPosition) {
    if (savedPosition) {
      return savedPosition
    } else {
      return { top: 0 }
    }
  }
})

// 动态设置页面标题
router.beforeEach((to, from, next) => {
  // 设置标题
  if (to.meta.title) {
    document.title = to.meta.title
  } else {
    document.title = 'Q-Market购物平台'
  }
  
  // 权限检查
  if (to.meta.requiresAuth) {
    // 这里应该检查用户是否已登录，如果没有登录则跳转到登录页面
    // 示例：
    // const isLoggedIn = store.state.user.isLoggedIn
    // if (!isLoggedIn) {
    //   next({ name: 'login', query: { redirect: to.fullPath } })
    //   return
    // }
  }
  
  next()
})

export default router 