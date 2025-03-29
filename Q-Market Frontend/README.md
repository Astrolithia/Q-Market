# Q-Market 购物平台前台系统

Q-Market是一个现代化的电商购物平台，基于Vue 3 + Vite构建，提供简洁美观的用户界面和流畅的购物体验。

## 技术栈

- **前端框架**: Vue 3
- **构建工具**: Vite
- **状态管理**: Pinia
- **路由管理**: Vue Router
- **UI 框架**: Element Plus
- **CSS预处理**: SCSS
- **HTTP客户端**: Axios

## 主要模块与功能

### 1. 首页模块
- 轮播广告展示
- 商品分类导航
- 精选商品推荐
- 新品上架展示
- 促销活动专区
- 品牌专区展示

### 2. 产品展示模块
- 商品列表展示
- 商品筛选功能
  - 价格区间筛选
  - 分类筛选
  - 品牌筛选
  - 销量/评分/价格排序
- 商品分页功能
- 商品快速预览

### 3. 产品详情模块
- 商品详细信息展示
- 商品图片画廊
- 商品规格选择
- 数量选择
- 加入购物车功能
- 立即购买功能
- 商品评价展示
- 相关商品推荐

### 4. 购物车模块
- 购物车商品列表
- 商品数量调整
- 商品移除功能
- 商品选择/取消选择
- 商品价格小计/总计
- 购物车商品保存
- 结算功能

### 5. 用户认证模块
- 用户登录
- 用户注册
- 密码找回
- 第三方账号登录
- 登录状态保持
- 账号安全验证

### 6. 用户中心模块
- 个人信息管理
- 收货地址管理
- 账户安全设置
- 我的收藏
- 浏览历史
- 消息通知中心

### 7. 订单管理模块
- 订单列表展示
- 订单详情查看
- 订单状态跟踪
- 订单评价功能
- 订单售后服务
- 退款/退货申请

### 8. 结算模块
- 确认订单信息
- 收货地址选择
- 支付方式选择
- 优惠券/积分使用
- 订单提交
- 支付流程引导

### 9. 搜索模块
- 关键词搜索
- 搜索历史记录
- 智能搜索推荐
- 搜索结果筛选
- 搜索热榜展示

### 10. 客户服务模块
- 在线客服咨询
- 常见问题解答
- 问题反馈
- 服务政策说明

## 项目安装与运行

### 安装依赖
```sh
npm install
```

### 开发环境运行
```sh
npm run dev
```

### 生产环境构建
```sh
npm run build
```

## 设计规范

- 采用响应式设计，适配移动端、平板和桌面端
- 主题色调：清新淡雅，以蓝色和白色为主
- 交互反馈：按钮点击效果、加载动画、表单验证反馈
- 字体规范：主标题18-24px、副标题16px、正文14px、辅助文字12px

步骤
# 安装Element Plus
npm install element-plus

# 安装Element Plus图标
npm install @element-plus/icons-vue

# 安装SCSS支持
npm install -D sass

# 安装Axios用于API请求
npm install axios

项目结构调整
我建议重新组织项目结构，以更好地适应电商系统：

src/
├── assets/
│   ├── css/
│   │   ├── variables.scss   # 变量定义
│   │   └── main.scss        # 主样式
│   └── images/              # 图片资源
├── components/
│   ├── layout/              # 布局组件
│   │   ├── AppHeader.vue
│   │   ├── AppFooter.vue
│   │   └── SearchBar.vue
│   ├── home/                # 首页组件
│   │   ├── HomeBanner.vue
│   │   ├── CategorySection.vue
│   │   ├── FeaturedProducts.vue
│   │   └── PromotionSection.vue
│   └── product/             # 产品组件
│       ├── ProductCard.vue
│       ├── ProductFilter.vue
│       └── ProductList.vue
├── views/                   # 页面
│   ├── HomeView.vue
│   ├── ProductListView.vue
│   └── ProductDetailView.vue
└── router/                  # 路由配置
    └── index.js