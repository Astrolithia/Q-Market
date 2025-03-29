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
import com.ruoyi.manage.domain.InventoryCheck;
import com.ruoyi.manage.service.IInventoryCheckService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 库存盘点Controller
 * 
 * @author shiro
 * @date 2025-03-28
 */
@RestController
@RequestMapping("/inventory/check")
public class InventoryCheckController extends BaseController
{
    @Autowired
    private IInventoryCheckService inventoryCheckService;

    /**
     * 查询库存盘点列表
     */
    @PreAuthorize("@ss.hasPermi('inventory:check:list')")
    @GetMapping("/list")
    public TableDataInfo list(InventoryCheck inventoryCheck)
    {
        startPage();
        List<InventoryCheck> list = inventoryCheckService.selectInventoryCheckList(inventoryCheck);
        return getDataTable(list);
    }

    /**
     * 导出库存盘点列表
     */
    @PreAuthorize("@ss.hasPermi('inventory:check:export')")
    @Log(title = "库存盘点", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, InventoryCheck inventoryCheck)
    {
        List<InventoryCheck> list = inventoryCheckService.selectInventoryCheckList(inventoryCheck);
        ExcelUtil<InventoryCheck> util = new ExcelUtil<InventoryCheck>(InventoryCheck.class);
        util.exportExcel(response, list, "库存盘点数据");
    }

    /**
     * 获取库存盘点详细信息
     */
    @PreAuthorize("@ss.hasPermi('inventory:check:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(inventoryCheckService.selectInventoryCheckById(id));
    }

    /**
     * 新增库存盘点
     */
    @PreAuthorize("@ss.hasPermi('inventory:check:add')")
    @Log(title = "库存盘点", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody InventoryCheck inventoryCheck)
    {
        return toAjax(inventoryCheckService.insertInventoryCheck(inventoryCheck));
    }

    /**
     * 修改库存盘点
     */
    @PreAuthorize("@ss.hasPermi('inventory:check:edit')")
    @Log(title = "库存盘点", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody InventoryCheck inventoryCheck)
    {
        return toAjax(inventoryCheckService.updateInventoryCheck(inventoryCheck));
    }

    /**
     * 删除库存盘点
     */
    @PreAuthorize("@ss.hasPermi('inventory:check:remove')")
    @Log(title = "库存盘点", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(inventoryCheckService.deleteInventoryCheckByIds(ids));
    }
}
