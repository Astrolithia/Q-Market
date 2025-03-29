package com.ruoyi.manage.service;

import java.util.List;
import com.ruoyi.manage.domain.InventoryCheck;

/**
 * 库存盘点Service接口
 * 
 * @author shiro
 * @date 2025-03-28
 */
public interface IInventoryCheckService 
{
    /**
     * 查询库存盘点
     * 
     * @param id 库存盘点主键
     * @return 库存盘点
     */
    public InventoryCheck selectInventoryCheckById(Long id);

    /**
     * 查询库存盘点列表
     * 
     * @param inventoryCheck 库存盘点
     * @return 库存盘点集合
     */
    public List<InventoryCheck> selectInventoryCheckList(InventoryCheck inventoryCheck);

    /**
     * 新增库存盘点
     * 
     * @param inventoryCheck 库存盘点
     * @return 结果
     */
    public int insertInventoryCheck(InventoryCheck inventoryCheck);

    /**
     * 修改库存盘点
     * 
     * @param inventoryCheck 库存盘点
     * @return 结果
     */
    public int updateInventoryCheck(InventoryCheck inventoryCheck);

    /**
     * 批量删除库存盘点
     * 
     * @param ids 需要删除的库存盘点主键集合
     * @return 结果
     */
    public int deleteInventoryCheckByIds(Long[] ids);

    /**
     * 删除库存盘点信息
     * 
     * @param id 库存盘点主键
     * @return 结果
     */
    public int deleteInventoryCheckById(Long id);
}
