package com.ruoyi.manage.mapper;

import java.util.List;
import com.ruoyi.manage.domain.InventoryTransfer;

/**
 * 库存调拨Mapper接口
 * 
 * @author shiro
 * @date 2025-03-28
 */
public interface InventoryTransferMapper 
{
    /**
     * 查询库存调拨
     * 
     * @param id 库存调拨主键
     * @return 库存调拨
     */
    public InventoryTransfer selectInventoryTransferById(Long id);

    /**
     * 查询库存调拨列表
     * 
     * @param inventoryTransfer 库存调拨
     * @return 库存调拨集合
     */
    public List<InventoryTransfer> selectInventoryTransferList(InventoryTransfer inventoryTransfer);

    /**
     * 新增库存调拨
     * 
     * @param inventoryTransfer 库存调拨
     * @return 结果
     */
    public int insertInventoryTransfer(InventoryTransfer inventoryTransfer);

    /**
     * 修改库存调拨
     * 
     * @param inventoryTransfer 库存调拨
     * @return 结果
     */
    public int updateInventoryTransfer(InventoryTransfer inventoryTransfer);

    /**
     * 删除库存调拨
     * 
     * @param id 库存调拨主键
     * @return 结果
     */
    public int deleteInventoryTransferById(Long id);

    /**
     * 批量删除库存调拨
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteInventoryTransferByIds(Long[] ids);
}
