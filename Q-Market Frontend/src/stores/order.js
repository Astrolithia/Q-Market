import { defineStore } from 'pinia'
import { ref, computed } from 'vue'

export const useOrderStore = defineStore('order', () => {
  // 订单列表
  const orders = ref([])
  
  // 加载状态
  const loading = ref(false)
  
  // 分页信息
  const pagination = ref({
    currentPage: 1,
    pageSize: 5,
    total: 0
  })
  
  // 筛选条件
  const filter = ref({
    status: 'all',
    keyword: ''
  })
  
  // 初始化 - 加载订单测试数据
  const initOrders = () => {
    loading.value = true
    
    // 模拟API调用延迟
    setTimeout(() => {
      // 模拟订单测试数据
      orders.value = [
        {
          id: 10001,
          orderNumber: 'Q20230610001',
          createTime: '2023-06-10T10:15:30',
          status: 'completed',
          items: [
            {
              id: 1,
              product: {
                id: 1,
                name: '高端智能手机 Pro Max 1',
                price: 5999,
                image: 'https://picsum.photos/id/160/100/100',
                specs: '经典黑 / 256GB'
              },
              quantity: 1
            }
          ],
          totalAmount: 5999,
          discount: 0,
          shippingFee: 0,
          paymentMethod: '支付宝',
          payTime: '2023-06-10T10:20:15',
          shipping: {
            receiver: '张三',
            phone: '13800138000',
            address: '广东省深圳市南山区科技园南区A栋15楼',
            method: '顺丰速运',
            trackingNumber: 'SF1234567890'
          }
        },
        {
          id: 10002,
          orderNumber: 'Q20230615002',
          createTime: '2023-06-15T14:30:45',
          status: 'unpaid',
          items: [
            {
              id: 1,
              product: {
                id: 3,
                name: '智能手表 Series 7 3',
                price: 2499,
                image: 'https://picsum.photos/id/111/100/100',
                specs: '典雅白 / 标准版'
              },
              quantity: 1
            },
            {
              id: 2,
              product: {
                id: 6,
                name: '智能扫地机器人 6',
                price: 2999,
                image: 'https://picsum.photos/id/118/100/100',
                specs: '高级版'
              },
              quantity: 1
            }
          ],
          totalAmount: 5498,
          discount: 200,
          shippingFee: 0,
          paymentMethod: '',
          payTime: null,
          shipping: {
            receiver: '张三',
            phone: '13800138000',
            address: '广东省深圳市南山区科技园南区A栋15楼',
            method: '顺丰速运',
            trackingNumber: ''
          }
        },
        {
          id: 10003,
          orderNumber: 'Q20230620003',
          createTime: '2023-06-20T09:10:25',
          status: 'pending',
          items: [
            {
              id: 1,
              product: {
                id: 2,
                name: '轻薄笔记本电脑 2',
                price: 7999,
                image: 'https://picsum.photos/id/119/100/100',
                specs: '银色 / 512GB'
              },
              quantity: 1
            }
          ],
          totalAmount: 7999,
          discount: 0,
          shippingFee: 0,
          paymentMethod: '微信支付',
          payTime: '2023-06-20T09:15:38',
          shipping: {
            receiver: '张三',
            phone: '13800138000',
            address: '广东省深圳市南山区科技园南区A栋15楼',
            method: '顺丰速运',
            trackingNumber: ''
          }
        },
        {
          id: 10004,
          orderNumber: 'Q20230625004',
          createTime: '2023-06-25T16:45:12',
          status: 'shipped',
          items: [
            {
              id: 1,
              product: {
                id: 4,
                name: '无线降噪耳机 4',
                price: 1599,
                image: 'https://picsum.photos/id/112/100/100',
                specs: '黑色'
              },
              quantity: 1
            },
            {
              id: 2,
              product: {
                id: 101,
                name: '智能手表配件',
                price: 299,
                image: 'https://picsum.photos/id/201/100/100',
                specs: '标准版'
              },
              quantity: 2
            }
          ],
          totalAmount: 2197,
          discount: 0,
          shippingFee: 10,
          paymentMethod: '支付宝',
          payTime: '2023-06-25T16:50:30',
          shipping: {
            receiver: '张三',
            phone: '13800138000',
            address: '广东省深圳市南山区科技园南区A栋15楼',
            method: '中通快递',
            trackingNumber: 'ZT9876543210'
          }
        },
        {
          id: 10005,
          orderNumber: 'Q20230630005',
          createTime: '2023-06-30T11:22:40',
          status: 'received',
          items: [
            {
              id: 1,
              product: {
                id: 5,
                name: '专业相机 DSLR 5',
                price: 8999,
                image: 'https://picsum.photos/id/120/100/100',
                specs: '标准套装'
              },
              quantity: 1
            }
          ],
          totalAmount: 8999,
          discount: 0,
          shippingFee: 0,
          paymentMethod: '银行卡',
          payTime: '2023-06-30T11:25:18',
          shipping: {
            receiver: '张三',
            phone: '13800138000',
            address: '广东省深圳市南山区科技园南区A栋15楼',
            method: '顺丰速运',
            trackingNumber: 'SF5432167890'
          }
        },
        {
          id: 10006,
          orderNumber: 'Q20230705006',
          createTime: '2023-07-05T08:05:33',
          status: 'cancelled',
          items: [
            {
              id: 1,
              product: {
                id: 7,
                name: '高端厨师机 7',
                price: 3999,
                image: 'https://picsum.photos/id/116/100/100',
                specs: '白色'
              },
              quantity: 1
            }
          ],
          totalAmount: 3999,
          discount: 0,
          shippingFee: 0,
          paymentMethod: '',
          payTime: null,
          shipping: {
            receiver: '张三',
            phone: '13800138000',
            address: '广东省深圳市南山区科技园南区A栋15楼',
            method: '圆通快递',
            trackingNumber: ''
          }
        }
      ]

      // 设置总数
      pagination.value.total = orders.value.length
      loading.value = false
    }, 800)
  }
  
  // 获取已过滤的订单
  const filteredOrders = computed(() => {
    let result = [...orders.value]
    
    // 状态筛选
    if (filter.value.status !== 'all') {
      result = result.filter(order => order.status === filter.value.status)
    }
    
    // 关键词搜索
    if (filter.value.keyword) {
      const keyword = filter.value.keyword.toLowerCase()
      result = result.filter(order => 
        order.orderNumber.toLowerCase().includes(keyword) || 
        order.items.some(item => 
          item.product.name.toLowerCase().includes(keyword)
        )
      )
    }
    
    return result
  })
  
  // 当前页面的订单
  const currentPageOrders = computed(() => {
    const start = (pagination.value.currentPage - 1) * pagination.value.pageSize
    const end = start + pagination.value.pageSize
    return filteredOrders.value.slice(start, end)
  })
  
  // 设置筛选条件
  const setFilter = (newFilter) => {
    filter.value = { ...filter.value, ...newFilter }
    // 重置到第一页
    pagination.value.currentPage = 1
  }
  
  // 切换页码
  const setPage = (page) => {
    pagination.value.currentPage = page
  }
  
  // 订单操作方法
  
  // 支付订单
  const payOrder = (orderId) => {
    const order = orders.value.find(o => o.id === orderId)
    if (order && order.status === 'unpaid') {
      order.status = 'pending'
      order.payTime = new Date().toISOString()
      order.paymentMethod = '微信支付' // 模拟支付方式
      return true
    }
    return false
  }
  
  // 取消订单
  const cancelOrder = (orderId) => {
    const order = orders.value.find(o => o.id === orderId)
    if (order && (order.status === 'unpaid' || order.status === 'pending')) {
      order.status = 'cancelled'
      return true
    }
    return false
  }
  
  // 确认收货
  const confirmReceive = (orderId) => {
    const order = orders.value.find(o => o.id === orderId)
    if (order && order.status === 'shipped') {
      order.status = 'received'
      return true
    }
    return false
  }
  
  // 评价订单
  const reviewOrder = (orderId) => {
    const order = orders.value.find(o => o.id === orderId)
    if (order && order.status === 'received') {
      order.status = 'completed'
      return true
    }
    return false
  }
  
  // 获取订单详情
  const getOrderById = (orderId) => {
    return orders.value.find(o => o.id === orderId) || null
  }
  
  // 获取订单状态文本
  const getStatusText = (status) => {
    const statusMap = {
      'unpaid': '待付款',
      'pending': '待发货',
      'shipped': '待收货',
      'received': '待评价',
      'completed': '已完成',
      'cancelled': '已取消'
    }
    return statusMap[status] || '未知状态'
  }
  
  // 初始化
  initOrders()
  
  return {
    orders,
    loading,
    pagination,
    filter,
    filteredOrders,
    currentPageOrders,
    setFilter,
    setPage,
    payOrder,
    cancelOrder,
    confirmReceive,
    reviewOrder,
    getOrderById,
    getStatusText
  }
}) 