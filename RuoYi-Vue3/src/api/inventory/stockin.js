import request from '@/utils/request'

// 查询入库管理列表
export function listStockin(query) {
  return request({
    url: '/inventory/stockin/list',
    method: 'get',
    params: query
  })
}

// 查询入库管理详细
export function getStockin(id) {
  return request({
    url: '/inventory/stockin/' + id,
    method: 'get'
  })
}

// 新增入库管理
export function addStockin(data) {
  return request({
    url: '/inventory/stockin',
    method: 'post',
    data: data
  })
}

// 修改入库管理
export function updateStockin(data) {
  return request({
    url: '/inventory/stockin',
    method: 'put',
    data: data
  })
}

// 删除入库管理
export function delStockin(id) {
  return request({
    url: '/inventory/stockin/' + id,
    method: 'delete'
  })
}
