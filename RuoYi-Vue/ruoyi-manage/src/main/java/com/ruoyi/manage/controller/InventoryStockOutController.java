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
import com.ruoyi.manage.domain.InventoryStockOut;
import com.ruoyi.manage.service.IInventoryStockOutService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 出库管理Controller
 * 
 * @author shiro
 * @date 2025-03-28
 */
@RestController
@RequestMapping("/inventory/stockout")
public class InventoryStockOutController extends BaseController
{
    @Autowired
    private IInventoryStockOutService inventoryStockOutService;

    /**
     * 查询出库管理列表
     */
    @PreAuthorize("@ss.hasPermi('inventory:stockout:list')")
    @GetMapping("/list")
    public TableDataInfo list(InventoryStockOut inventoryStockOut)
    {
        startPage();
        List<InventoryStockOut> list = inventoryStockOutService.selectInventoryStockOutList(inventoryStockOut);
        return getDataTable(list);
    }

    /**
     * 导出出库管理列表
     */
    @PreAuthorize("@ss.hasPermi('inventory:stockout:export')")
    @Log(title = "出库管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, InventoryStockOut inventoryStockOut)
    {
        List<InventoryStockOut> list = inventoryStockOutService.selectInventoryStockOutList(inventoryStockOut);
        ExcelUtil<InventoryStockOut> util = new ExcelUtil<InventoryStockOut>(InventoryStockOut.class);
        util.exportExcel(response, list, "出库管理数据");
    }

    /**
     * 获取出库管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('inventory:stockout:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(inventoryStockOutService.selectInventoryStockOutById(id));
    }

    /**
     * 新增出库管理
     */
    @PreAuthorize("@ss.hasPermi('inventory:stockout:add')")
    @Log(title = "出库管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody InventoryStockOut inventoryStockOut)
    {
        return toAjax(inventoryStockOutService.insertInventoryStockOut(inventoryStockOut));
    }

    /**
     * 修改出库管理
     */
    @PreAuthorize("@ss.hasPermi('inventory:stockout:edit')")
    @Log(title = "出库管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody InventoryStockOut inventoryStockOut)
    {
        return toAjax(inventoryStockOutService.updateInventoryStockOut(inventoryStockOut));
    }

    /**
     * 删除出库管理
     */
    @PreAuthorize("@ss.hasPermi('inventory:stockout:remove')")
    @Log(title = "出库管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(inventoryStockOutService.deleteInventoryStockOutByIds(ids));
    }
}
