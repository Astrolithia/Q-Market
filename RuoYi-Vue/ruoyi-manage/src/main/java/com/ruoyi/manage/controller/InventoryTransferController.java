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
import com.ruoyi.manage.domain.InventoryTransfer;
import com.ruoyi.manage.service.IInventoryTransferService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 库存调拨Controller
 * 
 * @author shiro
 * @date 2025-03-28
 */
@RestController
@RequestMapping("/inventory/transfer")
public class InventoryTransferController extends BaseController
{
    @Autowired
    private IInventoryTransferService inventoryTransferService;

    /**
     * 查询库存调拨列表
     */
    @PreAuthorize("@ss.hasPermi('inventory:transfer:list')")
    @GetMapping("/list")
    public TableDataInfo list(InventoryTransfer inventoryTransfer)
    {
        startPage();
        List<InventoryTransfer> list = inventoryTransferService.selectInventoryTransferList(inventoryTransfer);
        return getDataTable(list);
    }

    /**
     * 导出库存调拨列表
     */
    @PreAuthorize("@ss.hasPermi('inventory:transfer:export')")
    @Log(title = "库存调拨", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, InventoryTransfer inventoryTransfer)
    {
        List<InventoryTransfer> list = inventoryTransferService.selectInventoryTransferList(inventoryTransfer);
        ExcelUtil<InventoryTransfer> util = new ExcelUtil<InventoryTransfer>(InventoryTransfer.class);
        util.exportExcel(response, list, "库存调拨数据");
    }

    /**
     * 获取库存调拨详细信息
     */
    @PreAuthorize("@ss.hasPermi('inventory:transfer:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(inventoryTransferService.selectInventoryTransferById(id));
    }

    /**
     * 新增库存调拨
     */
    @PreAuthorize("@ss.hasPermi('inventory:transfer:add')")
    @Log(title = "库存调拨", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody InventoryTransfer inventoryTransfer)
    {
        return toAjax(inventoryTransferService.insertInventoryTransfer(inventoryTransfer));
    }

    /**
     * 修改库存调拨
     */
    @PreAuthorize("@ss.hasPermi('inventory:transfer:edit')")
    @Log(title = "库存调拨", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody InventoryTransfer inventoryTransfer)
    {
        return toAjax(inventoryTransferService.updateInventoryTransfer(inventoryTransfer));
    }

    /**
     * 删除库存调拨
     */
    @PreAuthorize("@ss.hasPermi('inventory:transfer:remove')")
    @Log(title = "库存调拨", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(inventoryTransferService.deleteInventoryTransferByIds(ids));
    }
}
