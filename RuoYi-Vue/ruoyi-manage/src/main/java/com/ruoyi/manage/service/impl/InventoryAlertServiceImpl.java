package com.ruoyi.manage.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.manage.mapper.InventoryAlertMapper;
import com.ruoyi.manage.domain.InventoryAlert;
import com.ruoyi.manage.service.IInventoryAlertService;

/**
 * 库存预警Service业务层处理
 * 
 * @author shiro
 * @date 2025-03-28
 */
@Service
public class InventoryAlertServiceImpl implements IInventoryAlertService 
{
    @Autowired
    private InventoryAlertMapper inventoryAlertMapper;

    /**
     * 查询库存预警
     * 
     * @param id 库存预警主键
     * @return 库存预警
     */
    @Override
    public InventoryAlert selectInventoryAlertById(Long id)
    {
        return inventoryAlertMapper.selectInventoryAlertById(id);
    }

    /**
     * 查询库存预警列表
     * 
     * @param inventoryAlert 库存预警
     * @return 库存预警
     */
    @Override
    public List<InventoryAlert> selectInventoryAlertList(InventoryAlert inventoryAlert)
    {
        return inventoryAlertMapper.selectInventoryAlertList(inventoryAlert);
    }

    /**
     * 新增库存预警
     * 
     * @param inventoryAlert 库存预警
     * @return 结果
     */
    @Override
    public int insertInventoryAlert(InventoryAlert inventoryAlert)
    {
        return inventoryAlertMapper.insertInventoryAlert(inventoryAlert);
    }

    /**
     * 修改库存预警
     * 
     * @param inventoryAlert 库存预警
     * @return 结果
     */
    @Override
    public int updateInventoryAlert(InventoryAlert inventoryAlert)
    {
        inventoryAlert.setUpdateTime(DateUtils.getNowDate());
        return inventoryAlertMapper.updateInventoryAlert(inventoryAlert);
    }

    /**
     * 批量删除库存预警
     * 
     * @param ids 需要删除的库存预警主键
     * @return 结果
     */
    @Override
    public int deleteInventoryAlertByIds(Long[] ids)
    {
        return inventoryAlertMapper.deleteInventoryAlertByIds(ids);
    }

    /**
     * 删除库存预警信息
     * 
     * @param id 库存预警主键
     * @return 结果
     */
    @Override
    public int deleteInventoryAlertById(Long id)
    {
        return inventoryAlertMapper.deleteInventoryAlertById(id);
    }
}
