package com.ruoyi.manage.mapper;

import java.util.List;
import com.ruoyi.manage.domain.Inventory;

/**
 * 库存查询Mapper接口
 * 
 * @author shiro
 * @date 2025-03-27
 */
public interface InventoryMapper 
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
     * 删除库存查询
     * 
     * @param id 库存查询主键
     * @return 结果
     */
    public int deleteInventoryById(Long id);

    /**
     * 批量删除库存查询
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteInventoryByIds(Long[] ids);
}
