package com.ruoyi.manage.service;

import java.util.List;
import com.ruoyi.manage.domain.OrderItem;

/**
 * 订单明细管理Service接口
 * 
 * @author shiro
 * @date 2025-03-27
 */
public interface IOrderItemService 
{
    /**
     * 查询订单明细管理
     * 
     * @param id 订单明细管理主键
     * @return 订单明细管理
     */
    public OrderItem selectOrderItemById(Long id);

    /**
     * 查询订单明细管理列表
     * 
     * @param orderItem 订单明细管理
     * @return 订单明细管理集合
     */
    public List<OrderItem> selectOrderItemList(OrderItem orderItem);

    /**
     * 新增订单明细管理
     * 
     * @param orderItem 订单明细管理
     * @return 结果
     */
    public int insertOrderItem(OrderItem orderItem);

    /**
     * 修改订单明细管理
     * 
     * @param orderItem 订单明细管理
     * @return 结果
     */
    public int updateOrderItem(OrderItem orderItem);

    /**
     * 批量删除订单明细管理
     * 
     * @param ids 需要删除的订单明细管理主键集合
     * @return 结果
     */
    public int deleteOrderItemByIds(Long[] ids);

    /**
     * 删除订单明细管理信息
     * 
     * @param id 订单明细管理主键
     * @return 结果
     */
    public int deleteOrderItemById(Long id);
}
