import request from '@/utils/request'

// 查询商家审核列表
export function listAudit(query) {
  return request({
    url: '/merchant/audit/list',
    method: 'get',
    params: query
  })
}

// 查询商家审核详细
export function getAudit(id) {
  return request({
    url: '/merchant/audit/' + id,
    method: 'get'
  })
}

// 新增商家审核
export function addAudit(data) {
  return request({
    url: '/merchant/audit',
    method: 'post',
    data: data
  })
}

// 修改商家审核
export function updateAudit(data) {
  return request({
    url: '/merchant/audit',
    method: 'put',
    data: data
  })
}

// 删除商家审核
export function delAudit(id) {
  return request({
    url: '/merchant/audit/' + id,
    method: 'delete'
  })
}
