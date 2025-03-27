import request from '@/utils/request'

// 查询活动商品关联列表
export function listProduct(query) {
  return request({
    url: '/marketing/product/list',
    method: 'get',
    params: query
  })
}

// 查询活动商品关联详细
export function getProduct(id) {
  return request({
    url: '/marketing/product/' + id,
    method: 'get'
  })
}

// 新增活动商品关联
export function addProduct(data) {
  return request({
    url: '/marketing/product',
    method: 'post',
    data: data
  })
}

// 修改活动商品关联
export function updateProduct(data) {
  return request({
    url: '/marketing/product',
    method: 'put',
    data: data
  })
}

// 删除活动商品关联
export function delProduct(id) {
  return request({
    url: '/marketing/product/' + id,
    method: 'delete'
  })
}
