package com.ruoyi.manage.service;

import java.util.List;
import com.ruoyi.manage.domain.Inventory;

/**
 * 库存查询Service接口
 * 
 * @author shiro
 * @date 2025-03-27
 */
public interface IInventoryService 
{
    /**
     * 查询库存查询
     * 
     * @param id 库存查询主键
     * @return 库存查询
     */
    public Inventory selectInventoryById(Long id);

    /**
     * 查询库存查询列表
     * 
     * @param inventory 库存查询
     * @return 库存查询集合
     */
    public List<Inventory> selectInventoryList(Inventory inventory);

    /**
     * 新增库存查询
     * 
     * @param inventory 库存查询
     * @return 结果
     */
    public int insertInventory(Inventory inventory);

    /**
     * 修改库存查询
     * 
     * @param inventory 库存查询
     * @return 结果
     */
    public int updateInventory(Inventory inventory);

    /**
     * 批量删除库存查询
     * 
     * @param ids 需要删除的库存查询主键集合
     * @return 结果
     */
    public int deleteInventoryByIds(Long[] ids);

    /**
     * 删除库存查询信息
     * 
     * @param id 库存查询主键
     * @return 结果
     */
    public int deleteInventoryById(Long id);
}
