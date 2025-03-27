import request from '@/utils/request'

// 查询满减活动管理列表
export function listPromotion(query) {
  return request({
    url: '/marketing/promotion/list',
    method: 'get',
    params: query
  })
}

// 查询满减活动管理详细
export function getPromotion(id) {
  return request({
    url: '/marketing/promotion/' + id,
    method: 'get'
  })
}

// 新增满减活动管理
export function addPromotion(data) {
  return request({
    url: '/marketing/promotion',
    method: 'post',
    data: data
  })
}

// 修改满减活动管理
export function updatePromotion(data) {
  return request({
    url: '/marketing/promotion',
    method: 'put',
    data: data
  })
}

// 删除满减活动管理
export function delPromotion(id) {
  return request({
    url: '/marketing/promotion/' + id,
    method: 'delete'
  })
}
