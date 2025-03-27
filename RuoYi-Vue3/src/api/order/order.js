import request from '@/utils/request'

// 查询订单信息管理列表
export function listOrder(query) {
  return request({
    url: '/order/order/list',
    method: 'get',
    params: query
  })
}

// 查询订单信息管理详细
export function getOrder(id) {
  return request({
    url: '/order/order/' + id,
    method: 'get'
  })
}

// 新增订单信息管理
export function addOrder(data) {
  return request({
    url: '/order/order',
    method: 'post',
    data: data
  })
}

// 修改订单信息管理
export function updateOrder(data) {
  return request({
    url: '/order/order',
    method: 'put',
    data: data
  })
}

// 删除订单信息管理
export function delOrder(id) {
  return request({
    url: '/order/order/' + id,
    method: 'delete'
  })
}
