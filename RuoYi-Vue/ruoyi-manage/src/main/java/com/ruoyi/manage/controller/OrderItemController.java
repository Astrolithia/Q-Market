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
import com.ruoyi.manage.domain.OrderItem;
import com.ruoyi.manage.service.IOrderItemService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 订单明细管理Controller
 * 
 * @author shiro
 * @date 2025-03-27
 */
@RestController
@RequestMapping("/order/item")
public class OrderItemController extends BaseController
{
    @Autowired
    private IOrderItemService orderItemService;

    /**
     * 查询订单明细管理列表
     */
    @PreAuthorize("@ss.hasPermi('order:item:list')")
    @GetMapping("/list")
    public TableDataInfo list(OrderItem orderItem)
    {
        startPage();
        List<OrderItem> list = orderItemService.selectOrderItemList(orderItem);
        return getDataTable(list);
    }

    /**
     * 导出订单明细管理列表
     */
    @PreAuthorize("@ss.hasPermi('order:item:export')")
    @Log(title = "订单明细管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, OrderItem orderItem)
    {
        List<OrderItem> list = orderItemService.selectOrderItemList(orderItem);
        ExcelUtil<OrderItem> util = new ExcelUtil<OrderItem>(OrderItem.class);
        util.exportExcel(response, list, "订单明细管理数据");
    }

    /**
     * 获取订单明细管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('order:item:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(orderItemService.selectOrderItemById(id));
    }

    /**
     * 新增订单明细管理
     */
    @PreAuthorize("@ss.hasPermi('order:item:add')")
    @Log(title = "订单明细管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OrderItem orderItem)
    {
        return toAjax(orderItemService.insertOrderItem(orderItem));
    }

    /**
     * 修改订单明细管理
     */
    @PreAuthorize("@ss.hasPermi('order:item:edit')")
    @Log(title = "订单明细管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OrderItem orderItem)
    {
        return toAjax(orderItemService.updateOrderItem(orderItem));
    }

    /**
     * 删除订单明细管理
     */
    @PreAuthorize("@ss.hasPermi('order:item:remove')")
    @Log(title = "订单明细管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(orderItemService.deleteOrderItemByIds(ids));
    }
}
