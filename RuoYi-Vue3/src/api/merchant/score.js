import request from '@/utils/request'

// 查询商家评级列表
export function listScore(query) {
  return request({
    url: '/merchant/score/list',
    method: 'get',
    params: query
  })
}

// 查询商家评级详细
export function getScore(id) {
  return request({
    url: '/merchant/score/' + id,
    method: 'get'
  })
}

// 新增商家评级
export function addScore(data) {
  return request({
    url: '/merchant/score',
    method: 'post',
    data: data
  })
}

// 修改商家评级
export function updateScore(data) {
  return request({
    url: '/merchant/score',
    method: 'put',
    data: data
  })
}

// 删除商家评级
export function delScore(id) {
  return request({
    url: '/merchant/score/' + id,
    method: 'delete'
  })
}
