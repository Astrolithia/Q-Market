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
  
  // 初始化时加载购物车数据
  loadCart()
  
  // 如果购物车为空，添加一些初始演示商品
  if (items.value.length === 0) {
    items.value = [
      {
        id: 1001,
        product: {
          id: 1,
          name: '高端智能手机 Pro Max 1',
          price: 5999,
          originalPrice: 6999,
          image: 'https://picsum.photos/id/160/300/300',
          specs: '经典黑 / 128GB'
        },
        quantity: 1,
        selected: true
      },
      {
        id: 1002,
        product: {
          id: 3,
          name: '智能手表 Series 7 3',
          price: 2499,
          originalPrice: 2999,
          image: 'https://picsum.photos/id/111/300/300',
          specs: '典雅白 / 标准版'
        },
        quantity: 2,
        selected: true
      },
      {
        id: 1003,
        product: {
          id: 6,
          name: '智能扫地机器人 6',
          price: 2999,
          originalPrice: 3599,
          image: 'https://picsum.photos/id/118/300/300',
          specs: '高级版'
        },
        quantity: 1,
        selected: false
      }
    ]
    
    // 保存初始数据到本地存储
    saveCart()
  }
  
  // 添加商品到购物车
  const addItem = (product, quantity = 1) => {
    // 检查该商品是否已在购物车中
    const existingItemIndex = items.value.findIndex(item => 
      item.product.id === product.id && item.product.specs === product.specs
    )
    
    if (existingItemIndex !== -1) {
      // 如果已存在，增加数量
      items.value[existingItemIndex].quantity += quantity
    } else {
      // 如果不存在，添加新商品
      const newId = items.value.length > 0 
        ? Math.max(...items.value.map(item => item.id)) + 1 
        : 1001
        
      items.value.push({
        id: newId,
        product,
        quantity,
        selected: true
      })
    }
    
    saveCart()
  }
  
  // 从购物车中移除商品
  const removeItem = (itemId) => {
    items.value = items.value.filter(item => item.id !== itemId)
    saveCart()
  }
  
  // 更新商品数量
  const updateItemQuantity = (itemId, quantity) => {
    const itemIndex = items.value.findIndex(item => item.id === itemId)
    
    if (itemIndex !== -1) {
      // 数量不能小于1
      items.value[itemIndex].quantity = Math.max(1, quantity)
      saveCart()
    }
  }
  
  // 切换商品选中状态
  const toggleItemSelection = (itemId) => {
    const itemIndex = items.value.findIndex(item => item.id === itemId)
    
    if (itemIndex !== -1) {
      items.value[itemIndex].selected = !items.value[itemIndex].selected
      saveCart()
    }
  }
  
  // 切换全选/全不选
  const toggleAllSelection = (selected) => {
    items.value.forEach(item => {
      item.selected = selected
    })
    saveCart()
  }
  
  // 清空购物车
  const clearCart = () => {
    items.value = []
    saveCart()
  }
  
  // 已选商品列表
  const selectedItems = computed(() => {
    return items.value.filter(item => item.selected)
  })
  
  // 商品总数量
  const totalItems = computed(() => {
    return items.value.reduce((total, item) => total + item.quantity, 0)
  })
  
  // 已选商品总价
  const totalPrice = computed(() => {
    return selectedItems.value.reduce((total, item) => total + item.product.price * item.quantity, 0)
  })
  
  // 已选商品原价总和
  const totalOriginalPrice = computed(() => {
    return selectedItems.value.reduce((total, item) => 
      total + (item.product.originalPrice || item.product.price) * item.quantity, 0
    )
  })
  
  // 节省金额
  const savedAmount = computed(() => {
    return totalOriginalPrice.value - totalPrice.value
  })
  
  return {
    items,
    addItem,
    removeItem,
    updateItemQuantity,
    toggleItemSelection,
    toggleAllSelection,
    clearCart,
    selectedItems,
    totalItems,
    totalPrice,
    totalOriginalPrice,
    savedAmount
  }
}) 