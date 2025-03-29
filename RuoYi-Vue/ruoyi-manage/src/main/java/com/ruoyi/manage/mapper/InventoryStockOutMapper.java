package com.ruoyi.manage.mapper;

import java.util.List;
import com.ruoyi.manage.domain.InventoryStockOut;

/**
 * 出库管理Mapper接口
 * 
 * @author shiro
 * @date 2025-03-28
 */
public interface InventoryStockOutMapper 
{
    /**
     * 查询出库管理
     * 
     * @param id 出库管理主键
     * @return 出库管理
     */
    public InventoryStockOut selectInventoryStockOutById(Long id);

    /**
     * 查询出库管理列表
     * 
     * @param inventoryStockOut 出库管理
     * @return 出库管理集合
     */
    public List<InventoryStockOut> selectInventoryStockOutList(InventoryStockOut inventoryStockOut);

    /**
     * 新增出库管理
     * 
     * @param inventoryStockOut 出库管理
     * @return 结果
     */
    public int insertInventoryStockOut(InventoryStockOut inventoryStockOut);

    /**
     * 修改出库管理
     * 
     * @param inventoryStockOut 出库管理
     * @return 结果
     */
    public int updateInventoryStockOut(InventoryStockOut inventoryStockOut);

    /**
     * 删除出库管理
     * 
     * @param id 出库管理主键
     * @return 结果
     */
    public int deleteInventoryStockOutById(Long id);

    /**
     * 批量删除出库管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteInventoryStockOutByIds(Long[] ids);
}
