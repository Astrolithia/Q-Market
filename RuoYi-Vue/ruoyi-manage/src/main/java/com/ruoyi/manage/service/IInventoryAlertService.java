package com.ruoyi.manage.service;

import java.util.List;
import com.ruoyi.manage.domain.InventoryAlert;

/**
 * 库存预警Service接口
 * 
 * @author shiro
 * @date 2025-03-28
 */
public interface IInventoryAlertService 
{
    /**
     * 查询库存预警
     * 
     * @param id 库存预警主键
     * @return 库存预警
     */
    public InventoryAlert selectInventoryAlertById(Long id);

    /**
     * 查询库存预警列表
     * 
     * @param inventoryAlert 库存预警
     * @return 库存预警集合
     */
    public List<InventoryAlert> selectInventoryAlertList(InventoryAlert inventoryAlert);

    /**
     * 新增库存预警
     * 
     * @param inventoryAlert 库存预警
     * @return 结果
     */
    public int insertInventoryAlert(InventoryAlert inventoryAlert);

    /**
     * 修改库存预警
     * 
     * @param inventoryAlert 库存预警
     * @return 结果
     */
    public int updateInventoryAlert(InventoryAlert inventoryAlert);

    /**
     * 批量删除库存预警
     * 
     * @param ids 需要删除的库存预警主键集合
     * @return 结果
     */
    public int deleteInventoryAlertByIds(Long[] ids);

    /**
     * 删除库存预警信息
     * 
     * @param id 库存预警主键
     * @return 结果
     */
    public int deleteInventoryAlertById(Long id);
}
