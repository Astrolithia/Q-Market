package com.ruoyi.manage.service;

import java.util.List;
import com.ruoyi.manage.domain.Order;

/**
 * 订单信息管理Service接口
 * 
 * @author shiro
 * @date 2025-03-27
 */
public interface IOrderService 
{
    /**
     * 查询订单信息管理
     * 
     * @param id 订单信息管理主键
     * @return 订单信息管理
     */
    public Order selectOrderById(Long id);

    /**
     * 查询订单信息管理列表
     * 
     * @param order 订单信息管理
     * @return 订单信息管理集合
     */
    public List<Order> selectOrderList(Order order);

    /**
     * 新增订单信息管理
     * 
     * @param order 订单信息管理
     * @return 结果
     */
    public int insertOrder(Order order);

    /**
     * 修改订单信息管理
     * 
     * @param order 订单信息管理
     * @return 结果
     */
    public int updateOrder(Order order);

    /**
     * 批量删除订单信息管理
     * 
     * @param ids 需要删除的订单信息管理主键集合
     * @return 结果
     */
    public int deleteOrderByIds(Long[] ids);

    /**
     * 删除订单信息管理信息
     * 
     * @param id 订单信息管理主键
     * @return 结果
     */
    public int deleteOrderById(Long id);
}
