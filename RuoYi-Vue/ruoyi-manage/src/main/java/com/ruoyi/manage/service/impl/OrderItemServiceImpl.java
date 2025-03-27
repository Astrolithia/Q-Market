package com.ruoyi.manage.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.manage.mapper.OrderItemMapper;
import com.ruoyi.manage.domain.OrderItem;
import com.ruoyi.manage.service.IOrderItemService;

/**
 * 订单明细管理Service业务层处理
 * 
 * @author shiro
 * @date 2025-03-27
 */
@Service
public class OrderItemServiceImpl implements IOrderItemService 
{
    @Autowired
    private OrderItemMapper orderItemMapper;

    /**
     * 查询订单明细管理
     * 
     * @param id 订单明细管理主键
     * @return 订单明细管理
     */
    @Override
    public OrderItem selectOrderItemById(Long id)
    {
        return orderItemMapper.selectOrderItemById(id);
    }

    /**
     * 查询订单明细管理列表
     * 
     * @param orderItem 订单明细管理
     * @return 订单明细管理
     */
    @Override
    public List<OrderItem> selectOrderItemList(OrderItem orderItem)
    {
        return orderItemMapper.selectOrderItemList(orderItem);
    }

    /**
     * 新增订单明细管理
     * 
     * @param orderItem 订单明细管理
     * @return 结果
     */
    @Override
    public int insertOrderItem(OrderItem orderItem)
    {
        orderItem.setCreateTime(DateUtils.getNowDate());
        return orderItemMapper.insertOrderItem(orderItem);
    }

    /**
     * 修改订单明细管理
     * 
     * @param orderItem 订单明细管理
     * @return 结果
     */
    @Override
    public int updateOrderItem(OrderItem orderItem)
    {
        return orderItemMapper.updateOrderItem(orderItem);
    }

    /**
     * 批量删除订单明细管理
     * 
     * @param ids 需要删除的订单明细管理主键
     * @return 结果
     */
    @Override
    public int deleteOrderItemByIds(Long[] ids)
    {
        return orderItemMapper.deleteOrderItemByIds(ids);
    }

    /**
     * 删除订单明细管理信息
     * 
     * @param id 订单明细管理主键
     * @return 结果
     */
    @Override
    public int deleteOrderItemById(Long id)
    {
        return orderItemMapper.deleteOrderItemById(id);
    }
}
