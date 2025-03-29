import request from '@/utils/request'

// 查询库存盘点列表
export function listCheck(query) {
  return request({
    url: '/inventory/check/list',
    method: 'get',
    params: query
  })
}

// 查询库存盘点详细
export function getCheck(id) {
  return request({
    url: '/inventory/check/' + id,
    method: 'get'
  })
}

// 新增库存盘点
export function addCheck(data) {
  return request({
    url: '/inventory/check',
    method: 'post',
    data: data
  })
}

// 修改库存盘点
export function updateCheck(data) {
  return request({
    url: '/inventory/check',
    method: 'put',
    data: data
  })
}

// 删除库存盘点
export function delCheck(id) {
  return request({
    url: '/inventory/check/' + id,
    method: 'delete'
  })
}
