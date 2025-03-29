import { defineStore } from 'pinia'
import { ref, computed } from 'vue'

export const useCartStore = defineStore('cart', () => {
  // 购物车商品列表
  const items = ref([])
  
  // 从本地存储加载购物车数据
  const loadCart = () => {
    const savedCart = localStorage.getItem('cart')
    if (savedCart) {
      try {
        items.value = JSON.parse(savedCart)
      } catch (e) {
        items.value = []
      }
    }
  }
  
  // 保存购物车到本地存储
  const saveCart = () => {
    localStorage.setItem('cart', JSON.stringify(items.value))
  }
  
  // 初始化
  loadCart()
  
  // 添加商品到购物车
  const addItem = (product, quantity = 1, specs = {}) => {
    // 检查商品是否已在购物车中
    const existingItem = items.value.find(item => 
      item.product.id === product.id && 
      JSON.stringify(item.specs) === JSON.stringify(specs)
    )
    
    if (existingItem) {
      // 如果已存在，增加数量
      existingItem.quantity += quantity
    } else {
      // 如果不存在，添加新商品
      const newItem = {
        id: Date.now(), // 临时ID
        product: {
          id: product.id,
          name: product.name,
          price: product.price,
          originalPrice: product.originalPrice,
          image: product.image
        },
        quantity,
        specs,
        selected: true
      }
      
      items.value.push(newItem)
    }
    
    // 保存到本地存储
    saveCart()
  }
  
  // 移除购物车中的商品
  const removeItem = (itemId) => {
    items.value = items.value.filter(item => item.id !== itemId)
    saveCart()
  }
  
  // 更新商品数量
  const updateItemQuantity = (itemId, quantity) => {
    const item = items.value.find(item => item.id === itemId)
    if (item) {
      item.quantity = quantity
      saveCart()
    }
  }
  
  // 清空购物车
  const clearCart = () => {
    items.value = []
    saveCart()
  }
  
  // 选择/取消选择商品
  const toggleItemSelection = (itemId) => {
    const item = items.value.find(item => item.id === itemId)
    if (item) {
      item.selected = !item.selected
      saveCart()
    }
  }
  
  // 全选/取消全选
  const toggleAllSelection = (selected) => {
    items.value.forEach(item => {
      item.selected = selected
    })
    saveCart()
  }
  
  // 计算属性：购物车中的商品总数
  const totalItems = computed(() => {
    return items.value.reduce((sum, item) => sum + item.quantity, 0)
  })
  
  // 计算属性：已选择的商品数量
  const selectedItems = computed(() => {
    return items.value.filter(item => item.selected)
  })
  
  // 计算属性：购物车商品总价
  const totalPrice = computed(() => {
    return items.value
      .filter(item => item.selected)
      .reduce((sum, item) => sum + item.product.price * item.quantity, 0)
  })
  
  // 计算属性：购物车商品原价总和
  const totalOriginalPrice = computed(() => {
    return items.value
      .filter(item => item.selected)
      .reduce((sum, item) => {
        const originalPrice = item.product.originalPrice || item.product.price
        return sum + originalPrice * item.quantity
      }, 0)
  })
  
  // 计算属性：节省的金额
  const savedAmount = computed(() => {
    return totalOriginalPrice.value - totalPrice.value
  })

  return { 
    items,
    addItem,
    removeItem,
    updateItemQuantity,
    clearCart,
    toggleItemSelection,
    toggleAllSelection,
    totalItems,
    selectedItems,
    totalPrice,
    totalOriginalPrice,
    savedAmount
  }
}) 