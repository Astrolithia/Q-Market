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
import com.ruoyi.manage.domain.OrderLogistics;
import com.ruoyi.manage.service.IOrderLogisticsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 订单物流Controller
 * 
 * @author shiro
 * @date 2025-03-27
 */
@RestController
@RequestMapping("/order/logistics")
public class OrderLogisticsController extends BaseController
{
    @Autowired
    private IOrderLogisticsService orderLogisticsService;

    /**
     * 查询订单物流列表
     */
    @PreAuthorize("@ss.hasPermi('order:logistics:list')")
    @GetMapping("/list")
    public TableDataInfo list(OrderLogistics orderLogistics)
    {
        startPage();
        List<OrderLogistics> list = orderLogisticsService.selectOrderLogisticsList(orderLogistics);
        return getDataTable(list);
    }

    /**
     * 导出订单物流列表
     */
    @PreAuthorize("@ss.hasPermi('order:logistics:export')")
    @Log(title = "订单物流", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, OrderLogistics orderLogistics)
    {
        List<OrderLogistics> list = orderLogisticsService.selectOrderLogisticsList(orderLogistics);
        ExcelUtil<OrderLogistics> util = new ExcelUtil<OrderLogistics>(OrderLogistics.class);
        util.exportExcel(response, list, "订单物流数据");
    }

    /**
     * 获取订单物流详细信息
     */
    @PreAuthorize("@ss.hasPermi('order:logistics:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(orderLogisticsService.selectOrderLogisticsById(id));
    }

    /**
     * 新增订单物流
     */
    @PreAuthorize("@ss.hasPermi('order:logistics:add')")
    @Log(title = "订单物流", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OrderLogistics orderLogistics)
    {
        return toAjax(orderLogisticsService.insertOrderLogistics(orderLogistics));
    }

    /**
     * 修改订单物流
     */
    @PreAuthorize("@ss.hasPermi('order:logistics:edit')")
    @Log(title = "订单物流", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OrderLogistics orderLogistics)
    {
        return toAjax(orderLogisticsService.updateOrderLogistics(orderLogistics));
    }

    /**
     * 删除订单物流
     */
    @PreAuthorize("@ss.hasPermi('order:logistics:remove')")
    @Log(title = "订单物流", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(orderLogisticsService.deleteOrderLogisticsByIds(ids));
    }
}
