import request from '@/utils/request'

// 查询库存调拨列表
export function listTransfer(query) {
  return request({
    url: '/inventory/transfer/list',
    method: 'get',
    params: query
  })
}

// 查询库存调拨详细
export function getTransfer(id) {
  return request({
    url: '/inventory/transfer/' + id,
    method: 'get'
  })
}

// 新增库存调拨
export function addTransfer(data) {
  return request({
    url: '/inventory/transfer',
    method: 'post',
    data: data
  })
}

// 修改库存调拨
export function updateTransfer(data) {
  return request({
    url: '/inventory/transfer',
    method: 'put',
    data: data
  })
}

// 删除库存调拨
export function delTransfer(id) {
  return request({
    url: '/inventory/transfer/' + id,
    method: 'delete'
  })
}
