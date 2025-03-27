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
import com.ruoyi.manage.domain.MarketingPromotion;
import com.ruoyi.manage.service.IMarketingPromotionService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 满减活动管理Controller
 * 
 * @author shiro
 * @date 2025-03-27
 */
@RestController
@RequestMapping("/marketing/promotion")
public class MarketingPromotionController extends BaseController
{
    @Autowired
    private IMarketingPromotionService marketingPromotionService;

    /**
     * 查询满减活动管理列表
     */
    @PreAuthorize("@ss.hasPermi('marketing:promotion:list')")
    @GetMapping("/list")
    public TableDataInfo list(MarketingPromotion marketingPromotion)
    {
        startPage();
        List<MarketingPromotion> list = marketingPromotionService.selectMarketingPromotionList(marketingPromotion);
        return getDataTable(list);
    }

    /**
     * 导出满减活动管理列表
     */
    @PreAuthorize("@ss.hasPermi('marketing:promotion:export')")
    @Log(title = "满减活动管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MarketingPromotion marketingPromotion)
    {
        List<MarketingPromotion> list = marketingPromotionService.selectMarketingPromotionList(marketingPromotion);
        ExcelUtil<MarketingPromotion> util = new ExcelUtil<MarketingPromotion>(MarketingPromotion.class);
        util.exportExcel(response, list, "满减活动管理数据");
    }

    /**
     * 获取满减活动管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('marketing:promotion:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(marketingPromotionService.selectMarketingPromotionById(id));
    }

    /**
     * 新增满减活动管理
     */
    @PreAuthorize("@ss.hasPermi('marketing:promotion:add')")
    @Log(title = "满减活动管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MarketingPromotion marketingPromotion)
    {
        return toAjax(marketingPromotionService.insertMarketingPromotion(marketingPromotion));
    }

    /**
     * 修改满减活动管理
     */
    @PreAuthorize("@ss.hasPermi('marketing:promotion:edit')")
    @Log(title = "满减活动管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MarketingPromotion marketingPromotion)
    {
        return toAjax(marketingPromotionService.updateMarketingPromotion(marketingPromotion));
    }

    /**
     * 删除满减活动管理
     */
    @PreAuthorize("@ss.hasPermi('marketing:promotion:remove')")
    @Log(title = "满减活动管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(marketingPromotionService.deleteMarketingPromotionByIds(ids));
    }
}
