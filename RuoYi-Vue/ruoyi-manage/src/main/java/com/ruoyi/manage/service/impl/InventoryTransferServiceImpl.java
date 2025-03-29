package com.ruoyi.manage.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.manage.mapper.InventoryTransferMapper;
import com.ruoyi.manage.domain.InventoryTransfer;
import com.ruoyi.manage.service.IInventoryTransferService;

/**
 * 库存调拨Service业务层处理
 * 
 * @author shiro
 * @date 2025-03-28
 */
@Service
public class InventoryTransferServiceImpl implements IInventoryTransferService 
{
    @Autowired
    private InventoryTransferMapper inventoryTransferMapper;

    /**
     * 查询库存调拨
     * 
     * @param id 库存调拨主键
     * @return 库存调拨
     */
    @Override
    public InventoryTransfer selectInventoryTransferById(Long id)
    {
        return inventoryTransferMapper.selectInventoryTransferById(id);
    }

    /**
     * 查询库存调拨列表
     * 
     * @param inventoryTransfer 库存调拨
     * @return 库存调拨
     */
    @Override
    public List<InventoryTransfer> selectInventoryTransferList(InventoryTransfer inventoryTransfer)
    {
        return inventoryTransferMapper.selectInventoryTransferList(inventoryTransfer);
    }

    /**
     * 新增库存调拨
     * 
     * @param inventoryTransfer 库存调拨
     * @return 结果
     */
    @Override
    public int insertInventoryTransfer(InventoryTransfer inventoryTransfer)
    {
        inventoryTransfer.setCreateTime(DateUtils.getNowDate());
        return inventoryTransferMapper.insertInventoryTransfer(inventoryTransfer);
    }

    /**
     * 修改库存调拨
     * 
     * @param inventoryTransfer 库存调拨
     * @return 结果
     */
    @Override
    public int updateInventoryTransfer(InventoryTransfer inventoryTransfer)
    {
        inventoryTransfer.setUpdateTime(DateUtils.getNowDate());
        return inventoryTransferMapper.updateInventoryTransfer(inventoryTransfer);
    }

    /**
     * 批量删除库存调拨
     * 
     * @param ids 需要删除的库存调拨主键
     * @return 结果
     */
    @Override
    public int deleteInventoryTransferByIds(Long[] ids)
    {
        return inventoryTransferMapper.deleteInventoryTransferByIds(ids);
    }

    /**
     * 删除库存调拨信息
     * 
     * @param id 库存调拨主键
     * @return 结果
     */
    @Override
    public int deleteInventoryTransferById(Long id)
    {
        return inventoryTransferMapper.deleteInventoryTransferById(id);
    }
}
