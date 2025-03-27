import request from '@/utils/request'

// 查询订单明细管理列表
export function listItem(query) {
  return request({
    url: '/order/item/list',
    method: 'get',
    params: query
  })
}

// 查询订单明细管理详细
export function getItem(id) {
  return request({
    url: '/order/item/' + id,
    method: 'get'
  })
}

// 新增订单明细管理
export function addItem(data) {
  return request({
    url: '/order/item',
    method: 'post',
    data: data
  })
}

// 修改订单明细管理
export function updateItem(data) {
  return request({
    url: '/order/item',
    method: 'put',
    data: data
  })
}

// 删除订单明细管理
export function delItem(id) {
  return request({
    url: '/order/item/' + id,
    method: 'delete'
  })
}
