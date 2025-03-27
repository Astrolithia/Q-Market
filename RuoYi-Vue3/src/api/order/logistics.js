import request from '@/utils/request'

// 查询订单物流列表
export function listLogistics(query) {
  return request({
    url: '/order/logistics/list',
    method: 'get',
    params: query
  })
}

// 查询订单物流详细
export function getLogistics(id) {
  return request({
    url: '/order/logistics/' + id,
    method: 'get'
  })
}

// 新增订单物流
export function addLogistics(data) {
  return request({
    url: '/order/logistics',
    method: 'post',
    data: data
  })
}

// 修改订单物流
export function updateLogistics(data) {
  return request({
    url: '/order/logistics',
    method: 'put',
    data: data
  })
}

// 删除订单物流
export function delLogistics(id) {
  return request({
    url: '/order/logistics/' + id,
    method: 'delete'
  })
}
