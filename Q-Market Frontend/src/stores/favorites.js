import { defineStore } from 'pinia'
import { ref, computed } from 'vue'

export const useFavoritesStore = defineStore('favorites', () => {
  // 收藏列表
  const favorites = ref([])
  
  // 从本地存储加载收藏数据
  const loadFavorites = () => {
    const savedFavorites = localStorage.getItem('favorites')
    if (savedFavorites) {
      try {
        favorites.value = JSON.parse(savedFavorites)
      } catch (e) {
        favorites.value = []
      }
    }
  }
  
  // 保存收藏到本地存储
  const saveFavorites = () => {
    localStorage.setItem('favorites', JSON.stringify(favorites.value))
  }
  
  // 初始化
  loadFavorites()
  
  // 添加商品到收藏
  const addToFavorites = (product) => {
    // 检查商品是否已在收藏中
    const existingIndex = favorites.value.findIndex(item => item.id === product.id)
    
    if (existingIndex === -1) {
      // 如果不存在，添加到收藏
      favorites.value.push({
        id: product.id,
        name: product.name,
        price: product.price,
        originalPrice: product.originalPrice,
        image: product.image,
        category: product.category,
        rating: product.rating,
        reviewCount: product.reviewCount,
        addTime: new Date().toISOString()
      })
      
      // 保存到本地存储
      saveFavorites()
      return true
    }
    
    return false
  }
  
  // 从收藏中移除商品
  const removeFromFavorites = (productId) => {
    favorites.value = favorites.value.filter(item => item.id !== productId)
    saveFavorites()
  }
  
  // 检查商品是否已收藏
  const isFavorite = (productId) => {
    // 确保类型一致，将两边都转为数字进行比较
    const numericId = Number(productId)
    return favorites.value.some(item => Number(item.id) === numericId)
  }
  
  // 收藏总数
  const totalFavorites = computed(() => {
    return favorites.value.length
  })
  
  return { 
    favorites,
    addToFavorites,
    removeFromFavorites,
    isFavorite,
    totalFavorites
  }
}) 