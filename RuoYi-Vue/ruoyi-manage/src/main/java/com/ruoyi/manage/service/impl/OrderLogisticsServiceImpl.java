package com.ruoyi.manage.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.manage.mapper.OrderLogisticsMapper;
import com.ruoyi.manage.domain.OrderLogistics;
import com.ruoyi.manage.service.IOrderLogisticsService;

/**
 * 订单物流Service业务层处理
 * 
 * @author shiro
 * @date 2025-03-27
 */
@Service
public class OrderLogisticsServiceImpl implements IOrderLogisticsService 
{
    @Autowired
    private OrderLogisticsMapper orderLogisticsMapper;

    /**
     * 查询订单物流
     * 
     * @param id 订单物流主键
     * @return 订单物流
     */
    @Override
    public OrderLogistics selectOrderLogisticsById(Long id)
    {
        return orderLogisticsMapper.selectOrderLogisticsById(id);
    }

    /**
     * 查询订单物流列表
     * 
     * @param orderLogistics 订单物流
     * @return 订单物流
     */
    @Override
    public List<OrderLogistics> selectOrderLogisticsList(OrderLogistics orderLogistics)
    {
        return orderLogisticsMapper.selectOrderLogisticsList(orderLogistics);
    }

    /**
     * 新增订单物流
     * 
     * @param orderLogistics 订单物流
     * @return 结果
     */
    @Override
    public int insertOrderLogistics(OrderLogistics orderLogistics)
    {
        orderLogistics.setCreateTime(DateUtils.getNowDate());
        return orderLogisticsMapper.insertOrderLogistics(orderLogistics);
    }

    /**
     * 修改订单物流
     * 
     * @param orderLogistics 订单物流
     * @return 结果
     */
    @Override
    public int updateOrderLogistics(OrderLogistics orderLogistics)
    {
        orderLogistics.setUpdateTime(DateUtils.getNowDate());
        return orderLogisticsMapper.updateOrderLogistics(orderLogistics);
    }

    /**
     * 批量删除订单物流
     * 
     * @param ids 需要删除的订单物流主键
     * @return 结果
     */
    @Override
    public int deleteOrderLogisticsByIds(Long[] ids)
    {
        return orderLogisticsMapper.deleteOrderLogisticsByIds(ids);
    }

    /**
     * 删除订单物流信息
     * 
     * @param id 订单物流主键
     * @return 结果
     */
    @Override
    public int deleteOrderLogisticsById(Long id)
    {
        return orderLogisticsMapper.deleteOrderLogisticsById(id);
    }
}
