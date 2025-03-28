import request from '@/utils/request'

// 查询库存预警列表
export function listAlert(query) {
  return request({
    url: '/inventory/alert/list',
    method: 'get',
    params: query
  })
}

// 查询库存预警详细
export function getAlert(id) {
  return request({
    url: '/inventory/alert/' + id,
    method: 'get'
  })
}

// 新增库存预警
export function addAlert(data) {
  return request({
    url: '/inventory/alert',
    method: 'post',
    data: data
  })
}

// 修改库存预警
export function updateAlert(data) {
  return request({
    url: '/inventory/alert',
    method: 'put',
    data: data
  })
}

// 删除库存预警
export function delAlert(id) {
  return request({
    url: '/inventory/alert/' + id,
    method: 'delete'
  })
}
