import request from '@/utils/request'

// 查询库存查询列表
export function listInventory(query) {
  return request({
    url: '/inventory/inventory/list',
    method: 'get',
    params: query
  })
}

// 查询库存查询详细
export function getInventory(id) {
  return request({
    url: '/inventory/inventory/' + id,
    method: 'get'
  })
}

// 新增库存查询
export function addInventory(data) {
  return request({
    url: '/inventory/inventory',
    method: 'post',
    data: data
  })
}

// 修改库存查询
export function updateInventory(data) {
  return request({
    url: '/inventory/inventory',
    method: 'put',
    data: data
  })
}

// 删除库存查询
export function delInventory(id) {
  return request({
    url: '/inventory/inventory/' + id,
    method: 'delete'
  })
}
