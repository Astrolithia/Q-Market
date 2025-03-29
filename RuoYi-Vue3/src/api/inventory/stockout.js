import request from '@/utils/request'

// 查询出库管理列表
export function listStockout(query) {
  return request({
    url: '/inventory/stockout/list',
    method: 'get',
    params: query
  })
}

// 查询出库管理详细
export function getStockout(id) {
  return request({
    url: '/inventory/stockout/' + id,
    method: 'get'
  })
}

// 新增出库管理
export function addStockout(data) {
  return request({
    url: '/inventory/stockout',
    method: 'post',
    data: data
  })
}

// 修改出库管理
export function updateStockout(data) {
  return request({
    url: '/inventory/stockout',
    method: 'put',
    data: data
  })
}

// 删除出库管理
export function delStockout(id) {
  return request({
    url: '/inventory/stockout/' + id,
    method: 'delete'
  })
}
