<style lang="scss" scoped>
@import '@/assets/css/variables.scss';

.home-page {
  padding-bottom: $spacing-xl;
}

.banner-section {
  margin-bottom: $spacing-xl;
}

.banner-item {
  height: 100%;
  background-size: cover;
  background-position: center;
  display: flex;
  align-items: center;
  cursor: pointer;
}

.banner-content {
  background-color: rgba(255, 255, 255, 0.85);
  padding: $spacing-lg;
  border-radius: $border-radius;
  margin-left: $spacing-xl;
  max-width: 400px;
  backdrop-filter: blur(5px);
  
  @media (max-width: $breakpoint-sm) {
    margin-left: $spacing-md;
    max-width: 80%;
  }
}

.banner-title {
  font-size: 28px;
  font-weight: 700;
  margin-bottom: $spacing-sm;
  color: $text-color;
}

.banner-description {
  font-size: 16px;
  margin-bottom: $spacing-md;
  color: $text-light;
}

.banner-button {
  margin-top: $spacing-sm;
}

.category-section {
  margin-bottom: $spacing-xl;
}

.category-card {
  background-color: $bg-white;
  border-radius: $border-radius;
  padding: $spacing-lg;
  text-align: center;
  box-shadow: $box-shadow;
  transition: all $transition-time;
  cursor: pointer;
  margin-bottom: $spacing-md;
  
  &:hover {
    transform: translateY(-5px);
    box-shadow: $box-shadow-hover;
    
    .el-icon {
      color: $primary-color;
      transform: scale(1.2);
    }
  }
  
  .el-icon {
    font-size: 32px;
    margin-bottom: $spacing-sm;
    color: $text-light;
    transition: all $transition-time;
  }
  
  .category-name {
    font-size: 14px;
    font-weight: 500;
  }
}

.section-header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-bottom: $spacing-lg;
  
  .view-all {
    display: flex;
    align-items: center;
    
    .el-icon {
      margin-left: 4px;
      transition: transform $transition-time;
    }
    
    &:hover .el-icon {
      transform: translateX(3px);
    }
  }
}

.promotion-section {
  margin: $spacing-xl 0;
}

.promotion-card {
  height: 200px;
  background-size: cover;
  background-position: center;
  border-radius: $border-radius;
  overflow: hidden;
  position: relative;
  cursor: pointer;
  margin-bottom: $spacing-lg;
  box-shadow: $box-shadow;
  transition: all $transition-time;
  
  &:hover {
    transform: translateY(-5px);
    box-shadow: $box-shadow-hover;
  }
  
  &::before {
    content: '';
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    background: linear-gradient(to right, rgba(0,0,0,0.7) 0%, rgba(0,0,0,0.3) 50%, rgba(0,0,0,0) 100%);
  }
  
  .promotion-content {
    position: relative;
    z-index: 2;
    padding: $spacing-lg;
    color: white;
    height: 100%;
    display: flex;
    flex-direction: column;
    justify-content: center;
    max-width: 60%;
    
    @media (max-width: $breakpoint-sm) {
      max-width: 80%;
    }
  }
  
  .promotion-title {
    font-size: 24px;
    font-weight: 700;
    margin-bottom: $spacing-sm;
  }
  
  .promotion-description {
    margin-bottom: $spacing-md;
    font-size: 16px;
  }
}

.new-arrivals-section {
  margin-bottom: $spacing-xl;
}
</style> 
<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import ProductCard from '@/components/product/ProductCard.vue'

const router = useRouter()

// 示例数据 - 实际项目中应从API获取
const banners = ref([
  {
    id: 1,
    title: '新品上市',
    description: '探索全新的科技产品系列，限时特惠中',
    image: 'https://picsum.photos/id/180/1200/400',
    link: '/products?category=电子产品'
  },
  {
    id: 2,
    title: '夏季特惠',
    description: '精选夏季服饰，低至5折',
    image: 'https://picsum.photos/id/96/1200/400',
    link: '/products?category=服装鞋帽'
  },
  {
    id: 3,
    title: '厨房焕新',
    description: '高品质厨房用品，让烹饪更有乐趣',
    image: 'https://picsum.photos/id/42/1200/400',
    link: '/products?category=家居生活'
  }
])

// 商品分类
const categories = ref([
  { id: 1, name: '电子产品', icon: 'Monitor' },
  { id: 2, name: '服装鞋帽', icon: 'Clothes' },
  { id: 3, name: '家居生活', icon: 'HomeFilled' },
  { id: 4, name: '美妆个护', icon: 'Brush' },
  { id: 5, name: '食品饮料', icon: 'Food' },
  { id: 6, name: '运动户外', icon: 'Football' },
  { id: 7, name: '母婴玩具', icon: 'Present' },
  { id: 8, name: '图书音像', icon: 'Reading' }
])

// 促销活动
const promotions = ref([
  {
    id: 1,
    title: '限时秒杀',
    description: '每日精选商品，限时特价',
    image: 'https://picsum.photos/id/26/800/200',
    link: '/promotions/flash-sale'
  },
  {
    id: 2,
    title: '新人专享',
    description: '新用户注册，多重好礼等你拿',
    image: 'https://picsum.photos/id/65/800/200',
    link: '/promotions/new-user'
  }
])

// 热门商品 - 确保每个商品使用不同ID
const featuredProducts = ref([
  {
    id: 1,
    name: '高端智能手机 Pro Max',
    description: '最新一代旗舰手机，强劲性能，出色拍照',
    price: 5999,
    originalPrice: 6999,
    image: 'https://picsum.photos/id/160/300/300',
    rating: 4.8,
    reviewCount: 2345,
    category: '电子产品',
    isNew: true
  },
  {
    id: 2,
    name: '轻薄笔记本电脑',
    description: '轻薄设计，长效续航，高性能处理器',
    price: 7999,
    originalPrice: 8999,
    image: 'https://picsum.photos/id/119/300/300',
    rating: 4.7,
    reviewCount: 1298,
    category: '电子产品',
    isNew: true
  },
  {
    id: 3,
    name: '智能手表 Series 7',
    description: '全天候健康监测，多种运动模式',
    price: 2499,
    originalPrice: 2999,
    image: 'https://picsum.photos/id/111/300/300',
    rating: 4.9,
    reviewCount: 892,
    category: '电子产品',
    isNew: true
  },
  {
    id: 4,
    name: '无线降噪耳机',
    description: '主动降噪，高清音质，舒适佩戴',
    price: 1599,
    originalPrice: 1899,
    image: 'https://picsum.photos/id/112/300/300',
    rating: 4.6,
    reviewCount: 1567,
    category: '电子产品',
    isNew: false
  }
])

// 新品上架 - 确保每个商品使用不同ID
const newArrivals = ref([
  {
    id: 5,
    name: '专业相机 DSLR',
    description: '高清画质，专业摄影，多种场景模式',
    price: 8999,
    originalPrice: 10999,
    image: 'https://picsum.photos/id/120/300/300',
    rating: 4.9,
    reviewCount: 468,
    category: '电子产品',
    isNew: true
  },
  {
    id: 6,
    name: '智能扫地机器人',
    description: '智能规划路线，强劲吸力，远程控制',
    price: 2999,
    originalPrice: 3599,
    image: 'https://picsum.photos/id/118/300/300',
    rating: 4.7,
    reviewCount: 253,
    category: '家用电器',
    isNew: true
  },
  {
    id: 7,
    name: '高端厨师机',
    description: '多功能料理，强劲马力，大容量',
    price: 3999,
    originalPrice: 4699,
    image: 'https://picsum.photos/id/116/300/300',
    rating: 4.8,
    reviewCount: 187,
    category: '厨房电器',
    isNew: true
  },
  {
    id: 8,
    name: '智能空气净化器',
    description: '高效过滤，智能检测，静音运行',
    price: 1999,
    originalPrice: 2499,
    image: 'https://picsum.photos/id/117/300/300',
    rating: 4.6,
    reviewCount: 342,
    category: '家用电器',
    isNew: true
  }
])

// 导航到商品详情页
const goToProduct = (productId) => {
  router.push(`/product/${productId}`)
}

// 导航到分类页面
const goToCategory = (category) => {
  router.push(`/products?category=${category}`)
}

// 导航到促销活动页面
const goToPromotion = (link) => {
  router.push(link)
}

// 导航到banner链接
const goToBanner = (link) => {
  router.push(link)
}
</script>

<template>
  <div class="home-page">
    <div class="container">
      <!-- 轮播广告 -->
      <section class="banner-section">
        <el-carousel height="400px" indicator-position="outside" :interval="5000">
          <el-carousel-item v-for="banner in banners" :key="banner.id" @click="goToBanner(banner.link)">
            <div class="banner-item" :style="{ backgroundImage: `url(${banner.image})` }">
              <div class="banner-content">
                <h2 class="banner-title">{{ banner.title }}</h2>
                <p class="banner-description">{{ banner.description }}</p>
                <el-button type="primary" class="banner-button">了解更多</el-button>
              </div>
            </div>
          </el-carousel-item>
        </el-carousel>
      </section>
      
      <!-- 商品分类 -->
      <section class="category-section">
        <div class="section-header">
          <h2 class="section-title">商品分类</h2>
        </div>
        <el-row :gutter="20">
          <el-col :xs="12" :sm="6" :md="3" v-for="category in categories" :key="category.id">
            <div class="category-card" @click="goToCategory(category.name)">
              <el-icon size="32"><component :is="category.icon" /></el-icon>
              <div class="category-name">{{ category.name }}</div>
            </div>
          </el-col>
        </el-row>
      </section>
      
      <!-- 精选商品 -->
      <section class="featured-section">
        <div class="section-header">
          <h2 class="section-title">精选商品</h2>
          <div class="view-all" @click="router.push('/products?sort=rating')">
            查看全部 <el-icon><ArrowRight /></el-icon>
          </div>
        </div>
        <el-row :gutter="20">
          <el-col :xs="12" :sm="8" :md="6" v-for="product in featuredProducts" :key="product.id">
            <ProductCard :product="product" />
          </el-col>
        </el-row>
      </section>
      
      <!-- 促销专区 -->
      <section class="promotion-section">
        <div class="section-header">
          <h2 class="section-title">促销活动</h2>
        </div>
        <el-row :gutter="20">
          <el-col :sm="24" :md="12" v-for="promotion in promotions" :key="promotion.id">
            <div 
              class="promotion-card" 
              :style="{ backgroundImage: `url(${promotion.image})` }"
              @click="goToPromotion(promotion.link)"
            >
              <div class="promotion-content">
                <h3 class="promotion-title">{{ promotion.title }}</h3>
                <p class="promotion-description">{{ promotion.description }}</p>
                <el-button size="small">立即查看</el-button>
              </div>
            </div>
          </el-col>
        </el-row>
      </section>
      
      <!-- 新品上架 -->
      <section class="new-arrivals-section">
        <div class="section-header">
          <h2 class="section-title">新品上架</h2>
          <div class="view-all" @click="router.push('/products?sort=new')">
            查看全部 <el-icon><ArrowRight /></el-icon>
          </div>
        </div>
        <el-row :gutter="20">
          <el-col :xs="12" :sm="8" :md="6" v-for="product in newArrivals" :key="product.id">
            <ProductCard :product="product" />
          </el-col>
        </el-row>
      </section>
    </div>
  </div>
</template>