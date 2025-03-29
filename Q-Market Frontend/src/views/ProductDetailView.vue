<script setup>
import { ref, reactive, computed, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import ProductCard from '@/components/product/ProductCard.vue'

const route = useRoute()
const router = useRouter()
const productId = computed(() => route.params.id)

// 加载状态
const loading = ref(true)

// 商品信息
const product = ref(null)

// 当前选中的规格
const selectedSpecs = reactive({
  color: '',
  size: '',
  edition: ''
})

// 购买数量
const quantity = ref(1)

// 当前展示的大图
const currentImage = ref('')

// 商品评价标签
const reviewTags = ref([
  { name: '全部', count: 156 },
  { name: '好评', count: 142 },
  { name: '中评', count: 10 },
  { name: '差评', count: 4 },
  { name: '有图', count: 35 },
  { name: '追评', count: 12 }
])

// 当前选中的评价标签
const activeReviewTag = ref('全部')

// 商品评价列表
const reviews = ref([])

// 相关商品推荐
const relatedProducts = ref([])

// 计算是否可以加入购物车
const canAddToCart = computed(() => {
  // 检查所有必选规格是否已选择
  if (product.value) {
    for (const specType of product.value.specifications) {
      if (specType.required && !selectedSpecs[specType.type]) {
        return false
      }
    }
    return true
  }
  return false
})

// 增加数量
const increaseQuantity = () => {
  quantity.value++
}

// 减少数量
const decreaseQuantity = () => {
  if (quantity.value > 1) {
    quantity.value--
  }
}

// 切换大图
const changeImage = (image) => {
  currentImage.value = image
}

// 选择规格
const selectSpec = (type, value) => {
  selectedSpecs[type] = value
}

// 添加到购物车
const addToCart = () => {
  if (!canAddToCart.value) {
    ElMessage.warning('请选择商品规格')
    return
  }
  
  // 调用购物车store方法
  ElMessage.success('商品已添加到购物车')
}

// 立即购买
const buyNow = () => {
  if (!canAddToCart.value) {
    ElMessage.warning('请选择商品规格')
    return
  }
  
  // 添加到购物车并跳转到结算页面
  router.push('/checkout')
}

// 添加到收藏
const addToWishlist = () => {
  ElMessage.success('商品已添加到收藏夹')
}

// 获取商品数据
const fetchProductData = () => {
  loading.value = true
  
  // 模拟API请求
  setTimeout(() => {
    // 模拟商品数据
    product.value = {
      id: productId.value,
      name: `高端智能手机 Pro Max ${productId.value}`,
      price: 5999,
      originalPrice: 6999,
      sales: 1024,
      rating: 4.8,
      reviewCount: 156,
      brand: '品牌A',
      category: '电子产品',
      tags: ['新品', '热销', '推荐'],
      description: `这是一款高端智能手机，配备了最新的处理器和摄像头系统。<br>
      <ul>
        <li>超长续航，支持快充技术</li>
        <li>高清OLED显示屏，120Hz刷新率</li>
        <li>专业级摄像系统，支持夜景模式</li>
        <li>防水防尘，坚固耐用</li>
      </ul>`,
      specifications: [
        {
          type: 'color',
          name: '颜色',
          required: true,
          options: [
            { name: '午夜黑', image: 'https://picsum.photos/id/1/40/40' },
            { name: '星空蓝', image: 'https://picsum.photos/id/2/40/40' },
            { name: '苹果绿', image: 'https://picsum.photos/id/3/40/40' },
            { name: '粉色', image: 'https://picsum.photos/id/4/40/40' }
          ]
        },
        {
          type: 'size',
          name: '存储容量',
          required: true,
          options: [
            { name: '128GB' },
            { name: '256GB' },
            { name: '512GB' },
            { name: '1TB' }
          ]
        },
        {
          type: 'edition',
          name: '版本',
          required: false,
          options: [
            { name: '标准版' },
            { name: '尊享版' },
            { name: '限定版' }
          ]
        }
      ],
      images: [
        'https://picsum.photos/id/1/500/500',
        'https://picsum.photos/id/2/500/500',
        'https://picsum.photos/id/3/500/500',
        'https://picsum.photos/id/4/500/500',
        'https://picsum.photos/id/5/500/500'
      ]
    }
    
    // 设置默认大图
    currentImage.value = product.value.images[0]
    
    // 模拟评价数据
    reviews.value = [
      {
        id: 1,
        user: { name: '用户123', avatar: 'https://picsum.photos/id/100/40/40' },
        rating: 5,
        date: '2023-10-15',
        content: '非常好用的手机，外观漂亮，系统流畅，拍照效果好，电池续航长。',
        images: ['https://picsum.photos/id/10/100/100', 'https://picsum.photos/id/11/100/100'],
        likes: 25,
        replies: [
          { user: '商家', content: '感谢您的支持，祝您使用愉快！', date: '2023-10-16' }
        ]
      },
      {
        id: 2,
        user: { name: '用户456', avatar: 'https://picsum.photos/id/101/40/40' },
        rating: 4,
        date: '2023-10-10',
        content: '整体不错，但续航稍差，希望后续系统优化能改善。',
        images: [],
        likes: 10,
        replies: []
      },
      {
        id: 3,
        user: { name: '用户789', avatar: 'https://picsum.photos/id/102/40/40' },
        rating: 5,
        date: '2023-10-05',
        content: '拍照效果杠杠的，夜景模式很强大，非常推荐摄影爱好者入手。',
        images: ['https://picsum.photos/id/12/100/100'],
        likes: 18,
        replies: []
      }
    ]
    
    // 模拟相关商品
    relatedProducts.value = [
      {
        id: 101,
        name: '智能手表 Series 5',
        price: 2499,
        originalPrice: 2999,
        image: 'https://picsum.photos/id/111/300/300',
        category: '电子产品',
        rating: 4.7,
        reviewCount: 89,
        isNew: true
      },
      {
        id: 102,
        name: '无线降噪耳机',
        price: 1599,
        originalPrice: 1899,
        image: 'https://picsum.photos/id/112/300/300',
        category: '电子产品',
        rating: 4.6,
        reviewCount: 124
      },
      {
        id: 103,
        name: '快充移动电源',
        price: 299,
        originalPrice: 399,
        image: 'https://picsum.photos/id/113/300/300',
        category: '电子产品',
        rating: 4.8,
        reviewCount: 156
      },
      {
        id: 104,
        name: '蓝牙音箱',
        price: 599,
        originalPrice: 699,
        image: 'https://picsum.photos/id/114/300/300',
        category: '电子产品',
        rating: 4.5,
        reviewCount: 78
      }
    ]
    
    loading.value = false
  }, 800)
}

onMounted(() => {
  fetchProductData()
})
</script>

<template>
  <div class="product-detail-page">
    <div class="container">
      <el-breadcrumb class="breadcrumbs" separator="/">
        <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item :to="{ path: '/products' }">全部商品</el-breadcrumb-item>
        <el-breadcrumb-item v-if="product" :to="{ path: `/products?category=${product.category}` }">{{ product.category }}</el-breadcrumb-item>
        <el-breadcrumb-item v-if="product">{{ product.name }}</el-breadcrumb-item>
      </el-breadcrumb>
      
      <!-- 加载中 -->
      <div v-if="loading" class="loading-container">
        <el-skeleton :rows="10" animated />
      </div>
      
      <!-- 商品详情 -->
      <div v-else class="product-detail-container">
        <div class="product-detail-left">
          <!-- 商品图片展示 -->
          <div class="product-gallery">
            <div class="main-image">
              <img :src="currentImage" :alt="product.name" />
            </div>
            <div class="thumbnail-list">
              <div 
                v-for="(image, index) in product.images" 
                :key="index" 
                class="thumbnail" 
                :class="{ active: image === currentImage }"
                @click="changeImage(image)"
              >
                <img :src="image" :alt="`${product.name}-${index}`" />
              </div>
            </div>
          </div>
        </div>
        
        <div class="product-detail-right">
          <!-- 商品基本信息 -->
          <div class="product-info">
            <h1 class="product-name">{{ product.name }}</h1>
            
            <div class="product-tags">
              <el-tag v-for="tag in product.tags" :key="tag" size="small">{{ tag }}</el-tag>
            </div>
            
            <div class="product-brief">
              <div class="product-rating">
                <el-rate v-model="product.rating" disabled text-color="#FF9900" />
                <span class="rating-value">{{ product.rating }}</span>
                <span class="review-count">{{ product.reviewCount }}条评价</span>
              </div>
              
              <div class="product-sales">
                销量: <span>{{ product.sales }}</span>
              </div>
              
              <div class="product-brand">
                品牌: <span>{{ product.brand }}</span>
              </div>
            </div>
            
            <div class="product-price-container">
              <div class="price-label">价格:</div>
              <div class="price-wrapper">
                <div class="current-price">¥{{ product.price.toFixed(2) }}</div>
                <div v-if="product.originalPrice" class="original-price">¥{{ product.originalPrice.toFixed(2) }}</div>
                <div v-if="product.originalPrice" class="discount">
                  {{ Math.round(100 - (product.price / product.originalPrice * 100)) }}% OFF
                </div>
              </div>
            </div>
            
            <!-- 商品规格选择 -->
            <div class="product-specs">
              <div 
                v-for="spec in product.specifications" 
                :key="spec.type" 
                class="spec-group"
              >
                <div class="spec-title">
                  {{ spec.name }}
                  <span v-if="spec.required" class="required">*</span>
                </div>
                
                <div class="spec-options">
                  <template v-if="spec.type === 'color'">
                    <div 
                      v-for="option in spec.options" 
                      :key="option.name" 
                      class="color-option" 
                      :class="{ active: selectedSpecs[spec.type] === option.name }"
                      @click="selectSpec(spec.type, option.name)"
                    >
                      <img :src="option.image" :alt="option.name" />
                      <span>{{ option.name }}</span>
                    </div>
                  </template>
                  
                  <template v-else>
                    <el-radio-group v-model="selectedSpecs[spec.type]">
                      <el-radio 
                        v-for="option in spec.options" 
                        :key="option.name"
                        :label="option.name"
                        border
                      >
                        {{ option.name }}
                      </el-radio>
                    </el-radio-group>
                  </template>
                </div>
              </div>
            </div>
            
            <!-- 商品数量 -->
            <div class="product-quantity">
              <div class="quantity-title">数量:</div>
              <div class="quantity-control">
                <el-button 
                  type="primary" 
                  :icon="Minus" 
                  circle 
                  plain 
                  @click="decreaseQuantity" 
                  :disabled="quantity <= 1"
                />
                <el-input-number v-model="quantity" :min="1" controls-position="right" :precision="0" />
                <el-button 
                  type="primary" 
                  :icon="Plus" 
                  circle 
                  plain 
                  @click="increaseQuantity"
                />
              </div>
            </div>
            
            <!-- 操作按钮 -->
            <div class="product-actions">
              <el-button type="primary" size="large" @click="addToCart" :disabled="!canAddToCart">
                <el-icon><ShoppingCart /></el-icon>
                加入购物车
              </el-button>
              
              <el-button type="danger" size="large" @click="buyNow" :disabled="!canAddToCart">
                立即购买
              </el-button>
              
              <el-button plain @click="addToWishlist">
                <el-icon><Star /></el-icon>
                收藏
              </el-button>
            </div>
            
            <!-- 服务保障 -->
            <div class="product-services">
              <div class="service-item">
                <el-icon><Check /></el-icon>
                正品保障
              </div>
              <div class="service-item">
                <el-icon><Check /></el-icon>
                极速发货
              </div>
              <div class="service-item">
                <el-icon><Check /></el-icon>
                7天无理由退货
              </div>
            </div>
          </div>
        </div>
      </div>
      
      <!-- 商品详情选项卡 -->
      <div v-if="!loading" class="product-tabs">
        <el-tabs>
          <el-tab-pane label="商品详情">
            <div class="product-description" v-html="product.description"></div>
            
            <div class="product-detail-images">
              <img 
                v-for="(image, index) in product.images" 
                :key="`detail-${index}`" 
                :src="image" 
                :alt="`${product.name}-详情图${index}`" 
              />
            </div>
          </el-tab-pane>
          
          <el-tab-pane label="规格参数">
            <el-descriptions :column="1" border>
              <el-descriptions-item label="品牌">{{ product.brand }}</el-descriptions-item>
              <el-descriptions-item label="商品名称">{{ product.name }}</el-descriptions-item>
              <el-descriptions-item v-for="spec in product.specifications" :key="`param-${spec.type}`" :label="spec.name">
                <span v-for="(option, index) in spec.options" :key="option.name">
                  {{ option.name }}
                  <span v-if="index < spec.options.length - 1"> / </span>
                </span>
              </el-descriptions-item>
            </el-descriptions>
          </el-tab-pane>
          
          <el-tab-pane :label="`用户评价(${product.reviewCount})`">
            <!-- 评价标签 -->
            <div class="review-tags">
              <el-radio-group v-model="activeReviewTag" size="small">
                <el-radio-button 
                  v-for="tag in reviewTags" 
                  :key="tag.name" 
                  :label="tag.name"
                >
                  {{ tag.name }}({{ tag.count }})
                </el-radio-button>
              </el-radio-group>
            </div>
            
            <!-- 评价列表 -->
            <div class="review-list">
              <div v-for="review in reviews" :key="review.id" class="review-item">
                <div class="review-header">
                  <div class="user-info">
                    <el-avatar :size="40" :src="review.user.avatar" />
                    <div class="user-name">{{ review.user.name }}</div>
                  </div>
                  <div class="review-rating">
                    <el-rate v-model="review.rating" disabled text-color="#FF9900" />
                    <div class="review-date">{{ review.date }}</div>
                  </div>
                </div>
                
                <div class="review-content">{{ review.content }}</div>
                
                <div v-if="review.images && review.images.length > 0" class="review-images">
                  <el-image 
                    v-for="(image, index) in review.images" 
                    :key="index"
                    :src="image"
                    :preview-src-list="review.images"
                    fit="cover"
                    class="review-image"
                  />
                </div>
                
                <div class="review-actions">
                  <el-button text type="primary" size="small">
                    <el-icon><ThumbUp /></el-icon>
                    有用({{ review.likes }})
                  </el-button>
                  <el-button text size="small">
                    <el-icon><ChatLineRound /></el-icon>
                    回复
                  </el-button>
                </div>
                
                <div v-if="review.replies && review.replies.length > 0" class="review-replies">
                  <div v-for="(reply, index) in review.replies" :key="index" class="reply-item">
                    <strong>{{ reply.user }}:</strong> {{ reply.content }}
                    <div class="reply-date">{{ reply.date }}</div>
                  </div>
                </div>
              </div>
            </div>
            
            <!-- 查看更多评价 -->
            <div class="view-more-reviews">
              <el-button text>查看更多评价</el-button>
            </div>
          </el-tab-pane>
        </el-tabs>
      </div>
      
      <!-- 相关商品推荐 -->
      <div v-if="!loading" class="related-products">
        <h2 class="section-title">相关商品推荐</h2>
        <el-row :gutter="20">
          <el-col :xs="12" :sm="12" :md="6" v-for="product in relatedProducts" :key="product.id">
            <ProductCard :product="product" />
          </el-col>
        </el-row>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
@import '@/assets/css/variables.scss';

.product-detail-page {
  padding: $spacing-lg 0 $spacing-xl;
}

.breadcrumbs {
  margin-bottom: $spacing-lg;
}

.loading-container {
  margin: $spacing-xl 0;
}

.product-detail-container {
  display: flex;
  gap: $spacing-xl;
  margin-bottom: $spacing-xl;
  
  @media (max-width: $breakpoint-md) {
    flex-direction: column;
  }
}

.product-detail-left {
  width: 450px;
  flex-shrink: 0;
  
  @media (max-width: $breakpoint-md) {
    width: 100%;
  }
}

.product-gallery {
  .main-image {
    width: 100%;
    height: 450px;
    border-radius: $border-radius;
    overflow: hidden;
    margin-bottom: $spacing-md;
    
    img {
      width: 100%;
      height: 100%;
      object-fit: cover;
    }
  }
  
  .thumbnail-list {
    display: flex;
    gap: $spacing-sm;
    
    .thumbnail {
      width: 80px;
      height: 80px;
      border-radius: $border-radius-sm;
      overflow: hidden;
      cursor: pointer;
      border: 2px solid transparent;
      transition: all $transition-time;
      
      &.active {
        border-color: $primary-color;
      }
      
      &:hover {
        transform: translateY(-3px);
      }
      
      img {
        width: 100%;
        height: 100%;
        object-fit: cover;
      }
    }
  }
}

.product-detail-right {
  flex: 1;
}

.product-info {
  .product-name {
    font-size: 24px;
    font-weight: 600;
    color: $text-color;
    margin-bottom: $spacing-sm;
  }
  
  .product-tags {
    margin-bottom: $spacing-md;
    
    .el-tag {
      margin-right: $spacing-xs;
    }
  }
  
  .product-brief {
    display: flex;
    flex-wrap: wrap;
    gap: $spacing-lg;
    background-color: $bg-color;
    padding: $spacing-md;
    border-radius: $border-radius;
    margin-bottom: $spacing-md;
    
    .product-rating, .product-sales, .product-brand {
      display: flex;
      align-items: center;
      color: $text-light;
      
      span {
        color: $text-color;
        margin-left: $spacing-xs;
      }
      
      .rating-value {
        font-weight: 600;
        color: #FF9900;
      }
      
      .review-count {
        margin-left: $spacing-sm;
        color: $text-lighter;
      }
    }
  }
  
  .product-price-container {
    display: flex;
    align-items: center;
    padding: $spacing-md 0;
    margin-bottom: $spacing-md;
    border-bottom: 1px solid $border-light;
    
    .price-label {
      font-size: 16px;
      color: $text-light;
      margin-right: $spacing-md;
    }
    
    .price-wrapper {
      display: flex;
      align-items: center;
      flex-wrap: wrap;
      
      .current-price {
        font-size: 28px;
        font-weight: 600;
        color: $danger-color;
      }
      
      .original-price {
        margin-left: $spacing-md;
        font-size: 16px;
        color: $text-lighter;
        text-decoration: line-through;
      }
      
      .discount {
        margin-left: $spacing-md;
        font-size: 14px;
        color: white;
        background-color: $danger-color;
        padding: 2px 8px;
        border-radius: $border-radius-sm;
      }
    }
  }
  
  .product-specs {
    .spec-group {
      margin-bottom: $spacing-md;
      
      .spec-title {
        font-size: 16px;
        margin-bottom: $spacing-sm;
        color: $text-color;
        
        .required {
          color: $danger-color;
          margin-left: 4px;
        }
      }
      
      .spec-options {
        display: flex;
        flex-wrap: wrap;
        gap: $spacing-sm;
        
        .color-option {
          display: flex;
          flex-direction: column;
          align-items: center;
          cursor: pointer;
          
          img {
            width: 40px;
            height: 40px;
            border-radius: $border-radius-sm;
            border: 2px solid transparent;
            transition: all $transition-time;
          }
          
          span {
            font-size: 12px;
            margin-top: 4px;
            color: $text-light;
          }
          
          &.active, &:hover {
            img {
              border-color: $primary-color;
            }
            
            span {
              color: $primary-color;
            }
          }
          
          &.active {
            img {
              box-shadow: 0 0 0 2px rgba($primary-color, 0.3);
            }
          }
        }
      }
    }
  }
  
  .product-quantity {
    display: flex;
    align-items: center;
    margin-bottom: $spacing-lg;
    
    .quantity-title {
      font-size: 16px;
      color: $text-color;
      margin-right: $spacing-md;
    }
    
    .quantity-control {
      display: flex;
      align-items: center;
      gap: $spacing-sm;
    }
  }
  
  .product-actions {
    display: flex;
    gap: $spacing-md;
    margin-bottom: $spacing-lg;
    
    @media (max-width: $breakpoint-sm) {
      flex-direction: column;
    }
  }
  
  .product-services {
    display: flex;
    flex-wrap: wrap;
    gap: $spacing-lg;
    background-color: $bg-color;
    padding: $spacing-md;
    border-radius: $border-radius;
    
    .service-item {
      display: flex;
      align-items: center;
      color: $text-light;
      font-size: 14px;
      
      .el-icon {
        margin-right: $spacing-xs;
        color: $success-color;
      }
    }
  }
}

.product-tabs {
  margin-bottom: $spacing-xl;
  
  .product-description {
    line-height: 1.8;
    color: $text-color;
    
    ul {
      padding-left: 20px;
      margin: $spacing-md 0;
    }
  }
  
  .product-detail-images {
    display: flex;
    flex-direction: column;
    gap: $spacing-md;
    margin-top: $spacing-lg;
    
    img {
      width: 100%;
      border-radius: $border-radius;
    }
  }
}

.review-tags {
  margin-bottom: $spacing-lg;
}

.review-list {
  .review-item {
    padding: $spacing-lg 0;
    border-bottom: 1px solid $border-light;
    
    &:last-child {
      border-bottom: none;
    }
    
    .review-header {
      display: flex;
      justify-content: space-between;
      margin-bottom: $spacing-md;
      
      @media (max-width: $breakpoint-sm) {
        flex-direction: column;
        gap: $spacing-sm;
      }
      
      .user-info {
        display: flex;
        align-items: center;
        
        .user-name {
          margin-left: $spacing-sm;
          font-weight: 500;
          color: $text-color;
        }
      }
      
      .review-rating {
        display: flex;
        align-items: center;
        
        .review-date {
          margin-left: $spacing-md;
          color: $text-lighter;
          font-size: 14px;
        }
      }
    }
    
    .review-content {
      line-height: 1.6;
      margin-bottom: $spacing-md;
    }
    
    .review-images {
      display: flex;
      gap: $spacing-sm;
      margin-bottom: $spacing-md;
      flex-wrap: wrap;
      
      .review-image {
        width: 100px;
        height: 100px;
        border-radius: $border-radius-sm;
        cursor: pointer;
      }
    }
    
    .review-actions {
      display: flex;
      gap: $spacing-md;
      margin-bottom: $spacing-md;
    }
    
    .review-replies {
      background-color: $bg-color;
      padding: $spacing-md;
      border-radius: $border-radius;
      
      .reply-item {
        margin-bottom: $spacing-sm;
        
        &:last-child {
          margin-bottom: 0;
        }
        
        .reply-date {
          font-size: 12px;
          color: $text-lighter;
          margin-top: 4px;
        }
      }
    }
  }
}

.view-more-reviews {
  text-align: center;
  margin-top: $spacing-lg;
}

.related-products {
  .section-title {
    font-size: 20px;
    font-weight: 600;
    margin-bottom: $spacing-lg;
    color: $text-color;
  }
}
</style> 