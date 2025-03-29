package com.ruoyi.manage.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.manage.mapper.InventoryStockOutMapper;
import com.ruoyi.manage.domain.InventoryStockOut;
import com.ruoyi.manage.service.IInventoryStockOutService;

/**
 * 出库管理Service业务层处理
 * 
 * @author shiro
 * @date 2025-03-28
 */
@Service
public class InventoryStockOutServiceImpl implements IInventoryStockOutService 
{
    @Autowired
    private InventoryStockOutMapper inventoryStockOutMapper;

    /**
     * 查询出库管理
     * 
     * @param id 出库管理主键
     * @return 出库管理
     */
    @Override
    public InventoryStockOut selectInventoryStockOutById(Long id)
    {
        return inventoryStockOutMapper.selectInventoryStockOutById(id);
    }

    /**
     * 查询出库管理列表
     * 
     * @param inventoryStockOut 出库管理
     * @return 出库管理
     */
    @Override
    public List<InventoryStockOut> selectInventoryStockOutList(InventoryStockOut inventoryStockOut)
    {
        return inventoryStockOutMapper.selectInventoryStockOutList(inventoryStockOut);
    }

    /**
     * 新增出库管理
     * 
     * @param inventoryStockOut 出库管理
     * @return 结果
     */
    @Override
    public int insertInventoryStockOut(InventoryStockOut inventoryStockOut)
    {
        inventoryStockOut.setCreateTime(DateUtils.getNowDate());
        return inventoryStockOutMapper.insertInventoryStockOut(inventoryStockOut);
    }

    /**
     * 修改出库管理
     * 
     * @param inventoryStockOut 出库管理
     * @return 结果
     */
    @Override
    public int updateInventoryStockOut(InventoryStockOut inventoryStockOut)
    {
        inventoryStockOut.setUpdateTime(DateUtils.getNowDate());
        return inventoryStockOutMapper.updateInventoryStockOut(inventoryStockOut);
    }

    /**
     * 批量删除出库管理
     * 
     * @param ids 需要删除的出库管理主键
     * @return 结果
     */
    @Override
    public int deleteInventoryStockOutByIds(Long[] ids)
    {
        return inventoryStockOutMapper.deleteInventoryStockOutByIds(ids);
    }

    /**
     * 删除出库管理信息
     * 
     * @param id 出库管理主键
     * @return 结果
     */
    @Override
    public int deleteInventoryStockOutById(Long id)
    {
        return inventoryStockOutMapper.deleteInventoryStockOutById(id);
    }
}
