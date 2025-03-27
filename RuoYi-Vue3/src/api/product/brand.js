import request from '@/utils/request'

// 查询商品品牌管理列表
export function listBrand(query) {
  return request({
    url: '/product/brand/list',
    method: 'get',
    params: query
  })
}

// 查询商品品牌管理详细
export function getBrand(id) {
  return request({
    url: '/product/brand/' + id,
    method: 'get'
  })
}

// 新增商品品牌管理
export function addBrand(data) {
  return request({
    url: '/product/brand',
    method: 'post',
    data: data
  })
}

// 修改商品品牌管理
export function updateBrand(data) {
  return request({
    url: '/product/brand',
    method: 'put',
    data: data
  })
}

// 删除商品品牌管理
export function delBrand(id) {
  return request({
    url: '/product/brand/' + id,
    method: 'delete'
  })
}
