import request from '@/utils/request'

// 查询优惠券管理列表
export function listCoupon(query) {
  return request({
    url: '/marketing/coupon/list',
    method: 'get',
    params: query
  })
}

// 查询优惠券管理详细
export function getCoupon(id) {
  return request({
    url: '/marketing/coupon/' + id,
    method: 'get'
  })
}

// 新增优惠券管理
export function addCoupon(data) {
  return request({
    url: '/marketing/coupon',
    method: 'post',
    data: data
  })
}

// 修改优惠券管理
export function updateCoupon(data) {
  return request({
    url: '/marketing/coupon',
    method: 'put',
    data: data
  })
}

// 删除优惠券管理
export function delCoupon(id) {
  return request({
    url: '/marketing/coupon/' + id,
    method: 'delete'
  })
}
