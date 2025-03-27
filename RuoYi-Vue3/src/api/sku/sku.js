import request from '@/utils/request'

// 查询商品SKU列表
export function listSku(query) {
  return request({
    url: '/sku/sku/list',
    method: 'get',
    params: query
  })
}

// 查询商品SKU详细
export function getSku(id) {
  return request({
    url: '/sku/sku/' + id,
    method: 'get'
  })
}

// 新增商品SKU
export function addSku(data) {
  return request({
    url: '/sku/sku',
    method: 'post',
    data: data
  })
}

// 修改商品SKU
export function updateSku(data) {
  return request({
    url: '/sku/sku',
    method: 'put',
    data: data
  })
}

// 删除商品SKU
export function delSku(id) {
  return request({
    url: '/sku/sku/' + id,
    method: 'delete'
  })
}
