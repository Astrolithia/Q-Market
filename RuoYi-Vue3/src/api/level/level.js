import request from '@/utils/request'

// 查询会员等级管理列表
export function listLevel(query) {
  return request({
    url: '/level/level/list',
    method: 'get',
    params: query
  })
}

// 查询会员等级管理详细
export function getLevel(id) {
  return request({
    url: '/level/level/' + id,
    method: 'get'
  })
}

// 新增会员等级管理
export function addLevel(data) {
  return request({
    url: '/level/level',
    method: 'post',
    data: data
  })
}

// 修改会员等级管理
export function updateLevel(data) {
  return request({
    url: '/level/level',
    method: 'put',
    data: data
  })
}

// 删除会员等级管理
export function delLevel(id) {
  return request({
    url: '/level/level/' + id,
    method: 'delete'
  })
}
