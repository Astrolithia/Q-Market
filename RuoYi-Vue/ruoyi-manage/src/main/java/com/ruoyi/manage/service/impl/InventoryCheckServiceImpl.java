package com.ruoyi.manage.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.manage.mapper.InventoryCheckMapper;
import com.ruoyi.manage.domain.InventoryCheck;
import com.ruoyi.manage.service.IInventoryCheckService;

/**
 * 库存盘点Service业务层处理
 * 
 * @author shiro
 * @date 2025-03-28
 */
@Service
public class InventoryCheckServiceImpl implements IInventoryCheckService 
{
    @Autowired
    private InventoryCheckMapper inventoryCheckMapper;

    /**
     * 查询库存盘点
     * 
     * @param id 库存盘点主键
     * @return 库存盘点
     */
    @Override
    public InventoryCheck selectInventoryCheckById(Long id)
    {
        return inventoryCheckMapper.selectInventoryCheckById(id);
    }

    /**
     * 查询库存盘点列表
     * 
     * @param inventoryCheck 库存盘点
     * @return 库存盘点
     */
    @Override
    public List<InventoryCheck> selectInventoryCheckList(InventoryCheck inventoryCheck)
    {
        return inventoryCheckMapper.selectInventoryCheckList(inventoryCheck);
    }

    /**
     * 新增库存盘点
     * 
     * @param inventoryCheck 库存盘点
     * @return 结果
     */
    @Override
    public int insertInventoryCheck(InventoryCheck inventoryCheck)
    {
        inventoryCheck.setCreateTime(DateUtils.getNowDate());
        return inventoryCheckMapper.insertInventoryCheck(inventoryCheck);
    }

    /**
     * 修改库存盘点
     * 
     * @param inventoryCheck 库存盘点
     * @return 结果
     */
    @Override
    public int updateInventoryCheck(InventoryCheck inventoryCheck)
    {
        inventoryCheck.setUpdateTime(DateUtils.getNowDate());
        return inventoryCheckMapper.updateInventoryCheck(inventoryCheck);
    }

    /**
     * 批量删除库存盘点
     * 
     * @param ids 需要删除的库存盘点主键
     * @return 结果
     */
    @Override
    public int deleteInventoryCheckByIds(Long[] ids)
    {
        return inventoryCheckMapper.deleteInventoryCheckByIds(ids);
    }

    /**
     * 删除库存盘点信息
     * 
     * @param id 库存盘点主键
     * @return 结果
     */
    @Override
    public int deleteInventoryCheckById(Long id)
    {
        return inventoryCheckMapper.deleteInventoryCheckById(id);
    }
}
