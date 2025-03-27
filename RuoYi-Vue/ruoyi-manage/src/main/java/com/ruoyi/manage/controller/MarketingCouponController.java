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
import com.ruoyi.manage.domain.MarketingCoupon;
import com.ruoyi.manage.service.IMarketingCouponService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 优惠券管理Controller
 * 
 * @author shiro
 * @date 2025-03-27
 */
@RestController
@RequestMapping("/marketing/coupon")
public class MarketingCouponController extends BaseController
{
    @Autowired
    private IMarketingCouponService marketingCouponService;

    /**
     * 查询优惠券管理列表
     */
    @PreAuthorize("@ss.hasPermi('marketing:coupon:list')")
    @GetMapping("/list")
    public TableDataInfo list(MarketingCoupon marketingCoupon)
    {
        startPage();
        List<MarketingCoupon> list = marketingCouponService.selectMarketingCouponList(marketingCoupon);
        return getDataTable(list);
    }

    /**
     * 导出优惠券管理列表
     */
    @PreAuthorize("@ss.hasPermi('marketing:coupon:export')")
    @Log(title = "优惠券管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MarketingCoupon marketingCoupon)
    {
        List<MarketingCoupon> list = marketingCouponService.selectMarketingCouponList(marketingCoupon);
        ExcelUtil<MarketingCoupon> util = new ExcelUtil<MarketingCoupon>(MarketingCoupon.class);
        util.exportExcel(response, list, "优惠券管理数据");
    }

    /**
     * 获取优惠券管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('marketing:coupon:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(marketingCouponService.selectMarketingCouponById(id));
    }

    /**
     * 新增优惠券管理
     */
    @PreAuthorize("@ss.hasPermi('marketing:coupon:add')")
    @Log(title = "优惠券管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MarketingCoupon marketingCoupon)
    {
        return toAjax(marketingCouponService.insertMarketingCoupon(marketingCoupon));
    }

    /**
     * 修改优惠券管理
     */
    @PreAuthorize("@ss.hasPermi('marketing:coupon:edit')")
    @Log(title = "优惠券管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MarketingCoupon marketingCoupon)
    {
        return toAjax(marketingCouponService.updateMarketingCoupon(marketingCoupon));
    }

    /**
     * 删除优惠券管理
     */
    @PreAuthorize("@ss.hasPermi('marketing:coupon:remove')")
    @Log(title = "优惠券管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(marketingCouponService.deleteMarketingCouponByIds(ids));
    }
}
