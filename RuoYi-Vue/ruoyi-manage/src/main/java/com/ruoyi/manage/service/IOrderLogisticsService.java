package com.ruoyi.manage.service;

import java.util.List;
import com.ruoyi.manage.domain.OrderLogistics;

/**
 * 订单物流Service接口
 * 
 * @author shiro
 * @date 2025-03-27
 */
public interface IOrderLogisticsService 
{
    /**
     * 查询订单物流
     * 
     * @param id 订单物流主键
     * @return 订单物流
     */
    public OrderLogistics selectOrderLogisticsById(Long id);

    /**
     * 查询订单物流列表
     * 
     * @param orderLogistics 订单物流
     * @return 订单物流集合
     */
    public List<OrderLogistics> selectOrderLogisticsList(OrderLogistics orderLogistics);

    /**
     * 新增订单物流
     * 
     * @param orderLogistics 订单物流
     * @return 结果
     */
    public int insertOrderLogistics(OrderLogistics orderLogistics);

    /**
     * 修改订单物流
     * 
     * @param orderLogistics 订单物流
     * @return 结果
     */
    public int updateOrderLogistics(OrderLogistics orderLogistics);

    /**
     * 批量删除订单物流
     * 
     * @param ids 需要删除的订单物流主键集合
     * @return 结果
     */
    public int deleteOrderLogisticsByIds(Long[] ids);

    /**
     * 删除订单物流信息
     * 
     * @param id 订单物流主键
     * @return 结果
     */
    public int deleteOrderLogisticsById(Long id);
}
