import request from '@/utils/request'

// 查询商家结算管理列表
export function listSettlement(query) {
  return request({
    url: '/merchant/settlement/list',
    method: 'get',
    params: query
  })
}

// 查询商家结算管理详细
export function getSettlement(id) {
  return request({
    url: '/merchant/settlement/' + id,
    method: 'get'
  })
}

// 新增商家结算管理
export function addSettlement(data) {
  return request({
    url: '/merchant/settlement',
    method: 'post',
    data: data
  })
}

// 修改商家结算管理
export function updateSettlement(data) {
  return request({
    url: '/merchant/settlement',
    method: 'put',
    data: data
  })
}

// 删除商家结算管理
export function delSettlement(id) {
  return request({
    url: '/merchant/settlement/' + id,
    method: 'delete'
  })
}
