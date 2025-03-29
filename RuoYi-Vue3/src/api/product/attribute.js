import request from '@/utils/request'

// 查询商品属性管理列表
export function listAttribute(query) {
  return request({
    url: '/product/attribute/list',
    method: 'get',
    params: query
  })
}

// 查询商品属性管理详细
export function getAttribute(id) {
  return request({
    url: '/product/attribute/' + id,
    method: 'get'
  })
}

// 新增商品属性管理
export function addAttribute(data) {
  return request({
    url: '/product/attribute',
    method: 'post',
    data: data
  })
}

// 修改商品属性管理
export function updateAttribute(data) {
  return request({
    url: '/product/attribute',
    method: 'put',
    data: data
  })
}

// 删除商品属性管理
export function delAttribute(id) {
  return request({
    url: '/product/attribute/' + id,
    method: 'delete'
  })
}
