<script setup>
import { ref, reactive, computed, onMounted, watch } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import ProductCard from '@/components/product/ProductCard.vue'

const route = useRoute()
const router = useRouter()

// 获取URL中的查询参数
const queryParams = computed(() => route.query)

// 筛选条件
const filters = reactive({
  category: queryParams.value.category || '',
  minPrice: '',
  maxPrice: '',
  brand: '',
  sort: queryParams.value.sort || 'default'
})

// 商品分类
const categories = ref([
  { id: 1, name: '电子产品' },
  { id: 2, name: '服装鞋帽' },
  { id: 3, name: '家居生活' },
  { id: 4, name: '美妆个护' },
  { id: 5, name: '食品饮料' },
  { id: 6, name: '运动户外' },
  { id: 7, name: '母婴玩具' },
  { id: 8, name: '图书音像' }
])

// 商品品牌
const brands = ref([
  { id: 1, name: '品牌A' },
  { id: 2, name: '品牌B' },
  { id: 3, name: '品牌C' },
  { id: 4, name: '品牌D' },
  { id: 5, name: '品牌E' }
])

// 加载状态
const loading = ref(false)

// 分页信息
const pagination = reactive({
  current: 1,
  pageSize: 12,
  total: 0
})

// 模拟商品列表数据
const productList = ref([])

// 排序选项
const sortOptions = [
  { value: 'default', label: '默认排序' },
  { value: 'price-asc', label: '价格从低到高' },
  { value: 'price-desc', label: '价格从高到低' },
  { value: 'sales', label: '销量优先' },
  { value: 'rating', label: '评分优先' },
  { value: 'new', label: '新品优先' }
]

// 价格区间选项
const priceRanges = [
  { min: '', max: '', label: '全部' },
  { min: 0, max: 100, label: '¥0 - ¥100' },
  { min: 100, max: 500, label: '¥100 - ¥500' },
  { min: 500, max: 1000, label: '¥500 - ¥1000' },
  { min: 1000, max: 5000, label: '¥1000 - ¥5000' },
  { min: 5000, max: '', label: '¥5000以上' }
]

// 当前活跃筛选标签
const activeFilters = computed(() => {
  const result = []
  
  if (filters.category) {
    const category = categories.value.find(c => c.name === filters.category)
    if (category) {
      result.push({
        type: 'category',
        label: `分类: ${category.name}`,
        value: category.name
      })
    }
  }
  
  if (filters.brand) {
    const brand = brands.value.find(b => b.name === filters.brand)
    if (brand) {
      result.push({
        type: 'brand',
        label: `品牌: ${brand.name}`,
        value: brand.name
      })
    }
  }
  
  if (filters.minPrice || filters.maxPrice) {
    let priceLabel = '价格: '
    if (filters.minPrice && filters.maxPrice) {
      priceLabel += `¥${filters.minPrice} - ¥${filters.maxPrice}`
    } else if (filters.minPrice) {
      priceLabel += `¥${filters.minPrice}以上`
    } else if (filters.maxPrice) {
      priceLabel += `¥${filters.maxPrice}以下`
    }
    
    result.push({
      type: 'price',
      label: priceLabel,
      value: { min: filters.minPrice, max: filters.maxPrice }
    })
  }
  
  return result
})

// 移除筛选标签
const removeFilter = (filter) => {
  if (filter.type === 'category') {
    filters.category = ''
  } else if (filter.type === 'brand') {
    filters.brand = ''
  } else if (filter.type === 'price') {
    filters.minPrice = ''
    filters.maxPrice = ''
  }
  
  // 应用筛选
  applyFilters()
}

// 清除所有筛选
const clearAllFilters = () => {
  filters.category = ''
  filters.minPrice = ''
  filters.maxPrice = ''
  filters.brand = ''
  
  // 应用筛选
  applyFilters()
}

// 应用筛选
const applyFilters = () => {
  pagination.current = 1
  fetchProducts()
  
  // 更新URL参数
  const query = {}
  if (filters.category) query.category = filters.category
  if (filters.brand) query.brand = filters.brand
  if (filters.minPrice) query.minPrice = filters.minPrice
  if (filters.maxPrice) query.maxPrice = filters.maxPrice
  if (filters.sort !== 'default') query.sort = filters.sort
  
  router.push({ query })
}

// 设置价格区间
const setPriceRange = (range) => {
  filters.minPrice = range.min
  filters.maxPrice = range.max
  applyFilters()
}

// 处理排序变化
const handleSortChange = () => {
  applyFilters()
}

// 处理页码变化
const handlePageChange = (page) => {
  pagination.current = page
  fetchProducts()
}

// 获取商品数据
const fetchProducts = () => {
  loading.value = true
  
  // 模拟API请求延迟
  setTimeout(() => {
    // 生成模拟数据
    const mockProducts = []
    const baseCount = 50
    const pageSize = pagination.pageSize
    const startIndex = (pagination.current - 1) * pageSize
    
    // 生成更多随机产品数据
    for (let i = 0; i < baseCount; i++) {
      const id = i + 1
      const category = categories.value[Math.floor(Math.random() * categories.value.length)].name
      const brand = brands.value[Math.floor(Math.random() * brands.value.length)].name
      const price = Math.floor(Math.random() * 9000) + 100
      const discount = Math.random() > 0.5
      const originalPrice = discount ? price * (1 + Math.random() * 0.5) : null
      const rating = (3 + Math.random() * 2).toFixed(1)
      const reviewCount = Math.floor(Math.random() * 200)
      const isNew = Math.random() > 0.7
      
      mockProducts.push({
        id,
        name: `商品${id} ${category}系列`,
        category,
        brand,
        price,
        originalPrice: originalPrice ? Math.floor(originalPrice) : null,
        image: `https://picsum.photos/id/${(id % 100) + 100}/300/300`,
        rating: parseFloat(rating),
        reviewCount,
        isNew
      })
    }
    
    // 应用筛选
    let filtered = [...mockProducts]
    
    if (filters.category) {
      filtered = filtered.filter(p => p.category === filters.category)
    }
    
    if (filters.brand) {
      filtered = filtered.filter(p => p.brand === filters.brand)
    }
    
    if (filters.minPrice) {
      filtered = filtered.filter(p => p.price >= filters.minPrice)
    }
    
    if (filters.maxPrice) {
      filtered = filtered.filter(p => p.price <= filters.maxPrice)
    }
    
    if (queryParams.value.q) {
      const keyword = queryParams.value.q.toLowerCase()
      filtered = filtered.filter(p => 
        p.name.toLowerCase().includes(keyword) || 
        p.category.toLowerCase().includes(keyword)
      )
    }
    
    // 应用排序
    switch (filters.sort) {
      case 'price-asc':
        filtered.sort((a, b) => a.price - b.price)
        break
      case 'price-desc':
        filtered.sort((a, b) => b.price - a.price)
        break
      case 'sales':
        filtered.sort((a, b) => b.reviewCount - a.reviewCount)
        break
      case 'rating':
        filtered.sort((a, b) => b.rating - a.rating)
        break
      case 'new':
        filtered.sort((a, b) => (b.isNew ? 1 : 0) - (a.isNew ? 1 : 0))
        break
      default:
        // 默认排序
        break
    }
    
    // 更新分页信息
    pagination.total = filtered.length
    
    // 获取当前页数据
    productList.value = filtered.slice(startIndex, startIndex + pageSize)
    
    loading.value = false
  }, 500)
}

// 监听查询参数变化
watch(
  () => route.query,
  (newQuery) => {
    filters.category = newQuery.category || ''
    filters.sort = newQuery.sort || 'default'
    fetchProducts()
  }
)

// 组件挂载时获取数据
onMounted(() => {
  // 初始化筛选条件
  if (queryParams.value.q) {
    filters.keyword = queryParams.value.q
  }
  
  if (queryParams.value.category) {
    filters.category = queryParams.value.category
  }
  
  if (queryParams.value.brand) {
    filters.brand = queryParams.value.brand
  }
  
  if (queryParams.value.minPrice) {
    filters.minPrice = queryParams.value.minPrice
  }
  
  if (queryParams.value.maxPrice) {
    filters.maxPrice = queryParams.value.maxPrice
  }
  
  if (queryParams.value.sort) {
    filters.sort = queryParams.value.sort
  }
  
  // 获取数据
  fetchProducts()
})
</script>

<template>
  <div class="product-list-page">
    <div class="container">
      <!-- 页面标题 -->
      <div class="page-header">
        <h1 v-if="queryParams.q" class="page-title">
          搜索结果: "{{ queryParams.q }}"
        </h1>
        <h1 v-else-if="filters.category" class="page-title">
          {{ filters.category }}
        </h1>
        <h1 v-else class="page-title">
          全部商品
        </h1>
      </div>
      
      <!-- 筛选标签区 -->
      <div v-if="activeFilters.length > 0" class="filter-tags">
        <span>已选条件：</span>
        <el-tag
          v-for="filter in activeFilters"
          :key="filter.type"
          closable
          @close="removeFilter(filter)"
          class="filter-tag"
        >
          {{ filter.label }}
        </el-tag>
        <el-button type="text" @click="clearAllFilters">清除全部</el-button>
      </div>
      
      <div class="product-list-container">
        <!-- 筛选侧边栏 -->
        <div class="filter-sidebar">
          <div class="filter-section">
            <h3 class="filter-title">商品分类</h3>
            <ul class="filter-list">
              <li 
                v-for="category in categories" 
                :key="category.id"
                :class="{ active: filters.category === category.name }"
                @click="filters.category = category.name; applyFilters()"
              >
                {{ category.name }}
              </li>
            </ul>
          </div>
          
          <div class="filter-section">
            <h3 class="filter-title">价格区间</h3>
            <ul class="filter-list">
              <li 
                v-for="(range, index) in priceRanges" 
                :key="index"
                :class="{ 
                  active: filters.minPrice === range.min && filters.maxPrice === range.max 
                }"
                @click="setPriceRange(range)"
              >
                {{ range.label }}
              </li>
            </ul>
            
            <div class="price-input">
              <el-input 
                v-model="filters.minPrice" 
                placeholder="¥最低" 
                size="small"
              />
              <span class="price-separator">-</span>
              <el-input 
                v-model="filters.maxPrice" 
                placeholder="¥最高" 
                size="small"
              />
              <el-button 
                type="primary" 
                size="small" 
                @click="applyFilters"
              >
                确定
              </el-button>
            </div>
          </div>
          
          <div class="filter-section">
            <h3 class="filter-title">品牌</h3>
            <ul class="filter-list">
              <li 
                v-for="brand in brands" 
                :key="brand.id"
                :class="{ active: filters.brand === brand.name }"
                @click="filters.brand = brand.name; applyFilters()"
              >
                {{ brand.name }}
              </li>
            </ul>
          </div>
        </div>
        
        <!-- 商品列表主区域 -->
        <div class="product-list-main">
          <!-- 排序工具栏 -->
          <div class="sort-toolbar">
            <div class="total-count">
              共 <span>{{ pagination.total }}</span> 个商品
            </div>
            
            <div class="sort-options">
              <span>排序：</span>
              <el-radio-group v-model="filters.sort" size="small" @change="handleSortChange">
                <el-radio-button 
                  v-for="option in sortOptions" 
                  :key="option.value" 
                  :label="option.value"
                >
                  {{ option.label }}
                </el-radio-button>
              </el-radio-group>
            </div>
          </div>
          
          <!-- 商品列表 -->
          <el-row :gutter="20" v-loading="loading">
            <el-col 
              :xs="12" 
              :sm="8" 
              :md="8" 
              :lg="6" 
              v-for="product in productList" 
              :key="product.id"
            >
              <div class="product-item">
                <ProductCard :product="product" />
              </div>
            </el-col>
          </el-row>
          
          <!-- 空数据提示 -->
          <el-empty 
            v-if="!loading && productList.length === 0"
            description="暂无相关商品"
          />
          
          <!-- 分页 -->
          <div v-if="pagination.total > 0" class="pagination">
            <el-pagination
              background
              layout="prev, pager, next"
              :current-page="pagination.current"
              :page-size="pagination.pageSize"
              :total="pagination.total"
              @current-change="handlePageChange"
            />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
@import '@/assets/css/variables.scss';

.product-list-page {
  padding: $spacing-lg 0 $spacing-xl;
}

.page-header {
  margin-bottom: $spacing-lg;
  
  .page-title {
    font-size: 24px;
    font-weight: 600;
    color: $text-color;
  }
}

.filter-tags {
  display: flex;
  align-items: center;
  flex-wrap: wrap;
  gap: $spacing-sm;
  margin-bottom: $spacing-lg;
  
  span {
    color: $text-light;
  }
  
  .filter-tag {
    margin-right: 0;
  }
}

.product-list-container {
  display: flex;
  gap: $spacing-lg;
  
  @media (max-width: $breakpoint-md) {
    flex-direction: column;
  }
}

.filter-sidebar {
  width: 250px;
  flex-shrink: 0;
  
  @media (max-width: $breakpoint-md) {
    width: 100%;
  }
}

.filter-section {
  background-color: $bg-white;
  border-radius: $border-radius;
  box-shadow: $box-shadow;
  padding: $spacing-md;
  margin-bottom: $spacing-lg;
  
  .filter-title {
    font-size: 16px;
    font-weight: 600;
    margin-bottom: $spacing-md;
    color: $text-color;
    padding-bottom: $spacing-sm;
    border-bottom: 1px solid $border-light;
  }
  
  .filter-list {
    list-style: none;
    padding: 0;
    
    li {
      padding: $spacing-sm 0;
      cursor: pointer;
      color: $text-light;
      transition: color $transition-time;
      
      &:hover {
        color: $primary-color;
      }
      
      &.active {
        color: $primary-color;
        font-weight: 500;
      }
    }
  }
  
  .price-input {
    display: flex;
    align-items: center;
    margin-top: $spacing-md;
    
    .price-separator {
      margin: 0 $spacing-xs;
      color: $text-lighter;
    }
    
    .el-button {
      margin-left: $spacing-sm;
    }
  }
}

.product-list-main {
  flex: 1;
}

.sort-toolbar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: $bg-white;
  border-radius: $border-radius;
  box-shadow: $box-shadow;
  padding: $spacing-md;
  margin-bottom: $spacing-lg;
  
  @media (max-width: $breakpoint-sm) {
    flex-direction: column;
    align-items: flex-start;
    gap: $spacing-md;
  }
  
  .total-count {
    color: $text-light;
    
    span {
      color: $primary-color;
      font-weight: 600;
    }
  }
  
  .sort-options {
    display: flex;
    align-items: center;
    
    span {
      color: $text-light;
      margin-right: $spacing-sm;
    }
  }
}

.product-item {
  margin-bottom: $spacing-lg;
  height: 100%;
}

.pagination {
  display: flex;
  justify-content: center;
  margin-top: $spacing-xl;
}
</style> 