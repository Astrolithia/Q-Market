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
import com.ruoyi.manage.domain.InventoryStockIn;
import com.ruoyi.manage.service.IInventoryStockInService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 入库管理Controller
 * 
 * @author shiro
 * @date 2025-03-27
 */
@RestController
@RequestMapping("/inventory/stockin")
public class InventoryStockInController extends BaseController
{
    @Autowired
    private IInventoryStockInService inventoryStockInService;

    /**
     * 查询入库管理列表
     */
    @PreAuthorize("@ss.hasPermi('inventory:stockin:list')")
    @GetMapping("/list")
    public TableDataInfo list(InventoryStockIn inventoryStockIn)
    {
        startPage();
        List<InventoryStockIn> list = inventoryStockInService.selectInventoryStockInList(inventoryStockIn);
        return getDataTable(list);
    }

    /**
     * 导出入库管理列表
     */
    @PreAuthorize("@ss.hasPermi('inventory:stockin:export')")
    @Log(title = "入库管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, InventoryStockIn inventoryStockIn)
    {
        List<InventoryStockIn> list = inventoryStockInService.selectInventoryStockInList(inventoryStockIn);
        ExcelUtil<InventoryStockIn> util = new ExcelUtil<InventoryStockIn>(InventoryStockIn.class);
        util.exportExcel(response, list, "入库管理数据");
    }

    /**
     * 获取入库管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('inventory:stockin:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(inventoryStockInService.selectInventoryStockInById(id));
    }

    /**
     * 新增入库管理
     */
    @PreAuthorize("@ss.hasPermi('inventory:stockin:add')")
    @Log(title = "入库管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody InventoryStockIn inventoryStockIn)
    {
        return toAjax(inventoryStockInService.insertInventoryStockIn(inventoryStockIn));
    }

    /**
     * 修改入库管理
     */
    @PreAuthorize("@ss.hasPermi('inventory:stockin:edit')")
    @Log(title = "入库管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody InventoryStockIn inventoryStockIn)
    {
        return toAjax(inventoryStockInService.updateInventoryStockIn(inventoryStockIn));
    }

    /**
     * 删除入库管理
     */
    @PreAuthorize("@ss.hasPermi('inventory:stockin:remove')")
    @Log(title = "入库管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(inventoryStockInService.deleteInventoryStockInByIds(ids));
    }
}
