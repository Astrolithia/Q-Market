package com.ruoyi.manage.service;

import java.util.List;
import com.ruoyi.manage.domain.InventoryStockIn;

/**
 * 入库管理Service接口
 * 
 * @author shiro
 * @date 2025-03-27
 */
public interface IInventoryStockInService 
{
    /**
     * 查询入库管理
     * 
     * @param id 入库管理主键
     * @return 入库管理
     */
    public InventoryStockIn selectInventoryStockInById(Long id);

    /**
     * 查询入库管理列表
     * 
     * @param inventoryStockIn 入库管理
     * @return 入库管理集合
     */
    public List<InventoryStockIn> selectInventoryStockInList(InventoryStockIn inventoryStockIn);

    /**
     * 新增入库管理
     * 
     * @param inventoryStockIn 入库管理
     * @return 结果
     */
    public int insertInventoryStockIn(InventoryStockIn inventoryStockIn);

    /**
     * 修改入库管理
     * 
     * @param inventoryStockIn 入库管理
     * @return 结果
     */
    public int updateInventoryStockIn(InventoryStockIn inventoryStockIn);

    /**
     * 批量删除入库管理
     * 
     * @param ids 需要删除的入库管理主键集合
     * @return 结果
     */
    public int deleteInventoryStockInByIds(Long[] ids);

    /**
     * 删除入库管理信息
     * 
     * @param id 入库管理主键
     * @return 结果
     */
    public int deleteInventoryStockInById(Long id);
}
