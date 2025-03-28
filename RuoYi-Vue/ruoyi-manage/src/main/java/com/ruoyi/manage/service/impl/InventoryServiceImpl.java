package com.ruoyi.manage.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.manage.mapper.InventoryMapper;
import com.ruoyi.manage.domain.Inventory;
import com.ruoyi.manage.service.IInventoryService;

/**
 * 库存查询Service业务层处理
 * 
 * @author shiro
 * @date 2025-03-27
 */
@Service
public class InventoryServiceImpl implements IInventoryService 
{
    @Autowired
    private InventoryMapper inventoryMapper;

    /**
     * 查询库存查询
     * 
     * @param id 库存查询主键
     * @return 库存查询
     */
    @Override
    public Inventory selectInventoryById(Long id)
    {
        return inventoryMapper.selectInventoryById(id);
    }

    /**
     * 查询库存查询列表
     * 
     * @param inventory 库存查询
     * @return 库存查询
     */
    @Override
    public List<Inventory> selectInventoryList(Inventory inventory)
    {
        return inventoryMapper.selectInventoryList(inventory);
    }

    /**
     * 新增库存查询
     * 
     * @param inventory 库存查询
     * @return 结果
     */
    @Override
    public int insertInventory(Inventory inventory)
    {
        return inventoryMapper.insertInventory(inventory);
    }

    /**
     * 修改库存查询
     * 
     * @param inventory 库存查询
     * @return 结果
     */
    @Override
    public int updateInventory(Inventory inventory)
    {
        inventory.setUpdateTime(DateUtils.getNowDate());
        return inventoryMapper.updateInventory(inventory);
    }

    /**
     * 批量删除库存查询
     * 
     * @param ids 需要删除的库存查询主键
     * @return 结果
     */
    @Override
    public int deleteInventoryByIds(Long[] ids)
    {
        return inventoryMapper.deleteInventoryByIds(ids);
    }

    /**
     * 删除库存查询信息
     * 
     * @param id 库存查询主键
     * @return 结果
     */
    @Override
    public int deleteInventoryById(Long id)
    {
        return inventoryMapper.deleteInventoryById(id);
    }
}
