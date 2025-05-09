package com.ruoyi.manage.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.manage.domain.InventoryAlert;
import com.ruoyi.manage.service.IInventoryAlertService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 库存预警Controller
 * 
 * @author shiro
 * @date 2025-03-28
 */
@RestController
@RequestMapping("/inventory/alert")
public class InventoryAlertController extends BaseController
{
    @Autowired
    private IInventoryAlertService inventoryAlertService;

    /**
     * 查询库存预警列表
     */
    @PreAuthorize("@ss.hasPermi('inventory:alert:list')")
    @GetMapping("/list")
    public TableDataInfo list(InventoryAlert inventoryAlert)
    {
        startPage();
        List<InventoryAlert> list = inventoryAlertService.selectInventoryAlertList(inventoryAlert);
        return getDataTable(list);
    }

    /**
     * 导出库存预警列表
     */
    @PreAuthorize("@ss.hasPermi('inventory:alert:export')")
    @Log(title = "库存预警", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, InventoryAlert inventoryAlert)
    {
        List<InventoryAlert> list = inventoryAlertService.selectInventoryAlertList(inventoryAlert);
        ExcelUtil<InventoryAlert> util = new ExcelUtil<InventoryAlert>(InventoryAlert.class);
        util.exportExcel(response, list, "库存预警数据");
    }

    /**
     * 获取库存预警详细信息
     */
    @PreAuthorize("@ss.hasPermi('inventory:alert:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(inventoryAlertService.selectInventoryAlertById(id));
    }

    /**
     * 新增库存预警
     */
    @PreAuthorize("@ss.hasPermi('inventory:alert:add')")
    @Log(title = "库存预警", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody InventoryAlert inventoryAlert)
    {
        return toAjax(inventoryAlertService.insertInventoryAlert(inventoryAlert));
    }

    /**
     * 修改库存预警
     */
    @PreAuthorize("@ss.hasPermi('inventory:alert:edit')")
    @Log(title = "库存预警", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody InventoryAlert inventoryAlert)
    {
        return toAjax(inventoryAlertService.updateInventoryAlert(inventoryAlert));
    }

    /**
     * 删除库存预警
     */
    @PreAuthorize("@ss.hasPermi('inventory:alert:remove')")
    @Log(title = "库存预警", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(inventoryAlertService.deleteInventoryAlertByIds(ids));
    }
}
