package com.ruoyi.manage.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.manage.mapper.InventoryStockInMapper;
import com.ruoyi.manage.domain.InventoryStockIn;
import com.ruoyi.manage.service.IInventoryStockInService;

/**
 * 入库管理Service业务层处理
 * 
 * @author shiro
 * @date 2025-03-27
 */
@Service
public class InventoryStockInServiceImpl implements IInventoryStockInService 
{
    @Autowired
    private InventoryStockInMapper inventoryStockInMapper;

    /**
     * 查询入库管理
     * 
     * @param id 入库管理主键
     * @return 入库管理
     */
    @Override
    public InventoryStockIn selectInventoryStockInById(Long id)
    {
        return inventoryStockInMapper.selectInventoryStockInById(id);
    }

    /**
     * 查询入库管理列表
     * 
     * @param inventoryStockIn 入库管理
     * @return 入库管理
     */
    @Override
    public List<InventoryStockIn> selectInventoryStockInList(InventoryStockIn inventoryStockIn)
    {
        return inventoryStockInMapper.selectInventoryStockInList(inventoryStockIn);
    }

    /**
     * 新增入库管理
     * 
     * @param inventoryStockIn 入库管理
     * @return 结果
     */
    @Override
    public int insertInventoryStockIn(InventoryStockIn inventoryStockIn)
    {
        inventoryStockIn.setCreateTime(DateUtils.getNowDate());
        return inventoryStockInMapper.insertInventoryStockIn(inventoryStockIn);
    }

    /**
     * 修改入库管理
     * 
     * @param inventoryStockIn 入库管理
     * @return 结果
     */
    @Override
    public int updateInventoryStockIn(InventoryStockIn inventoryStockIn)
    {
        inventoryStockIn.setUpdateTime(DateUtils.getNowDate());
        return inventoryStockInMapper.updateInventoryStockIn(inventoryStockIn);
    }

    /**
     * 批量删除入库管理
     * 
     * @param ids 需要删除的入库管理主键
     * @return 结果
     */
    @Override
    public int deleteInventoryStockInByIds(Long[] ids)
    {
        return inventoryStockInMapper.deleteInventoryStockInByIds(ids);
    }

    /**
     * 删除入库管理信息
     * 
     * @param id 入库管理主键
     * @return 结果
     */
    @Override
    public int deleteInventoryStockInById(Long id)
    {
        return inventoryStockInMapper.deleteInventoryStockInById(id);
    }
}
