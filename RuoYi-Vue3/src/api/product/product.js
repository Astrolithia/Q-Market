import request from '@/utils/request'

// 查询商品信息管理列表
export function listProduct(query) {
  return request({
    url: '/product/product/list',
    method: 'get',
    params: query
  })
}

// 查询商品信息管理详细
export function getProduct(id) {
  return request({
    url: '/product/product/' + id,
    method: 'get'
  })
}

// 新增商品信息管理
export function addProduct(data) {
  return request({
    url: '/product/product',
    method: 'post',
    data: data
  })
}

// 修改商品信息管理
export function updateProduct(data) {
  return request({
    url: '/product/product',
    method: 'put',
    data: data
  })
}

// 删除商品信息管理
export function delProduct(id) {
  return request({
    url: '/product/product/' + id,
    method: 'delete'
  })
}
