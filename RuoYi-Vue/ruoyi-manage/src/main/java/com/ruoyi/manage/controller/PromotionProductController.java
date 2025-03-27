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
import com.ruoyi.manage.domain.PromotionProduct;
import com.ruoyi.manage.service.IPromotionProductService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 活动商品关联Controller
 * 
 * @author shiro
 * @date 2025-03-27
 */
@RestController
@RequestMapping("/marketing/product")
public class PromotionProductController extends BaseController
{
    @Autowired
    private IPromotionProductService promotionProductService;

    /**
     * 查询活动商品关联列表
     */
    @PreAuthorize("@ss.hasPermi('marketing:product:list')")
    @GetMapping("/list")
    public TableDataInfo list(PromotionProduct promotionProduct)
    {
        startPage();
        List<PromotionProduct> list = promotionProductService.selectPromotionProductList(promotionProduct);
        return getDataTable(list);
    }

    /**
     * 导出活动商品关联列表
     */
    @PreAuthorize("@ss.hasPermi('marketing:product:export')")
    @Log(title = "活动商品关联", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PromotionProduct promotionProduct)
    {
        List<PromotionProduct> list = promotionProductService.selectPromotionProductList(promotionProduct);
        ExcelUtil<PromotionProduct> util = new ExcelUtil<PromotionProduct>(PromotionProduct.class);
        util.exportExcel(response, list, "活动商品关联数据");
    }

    /**
     * 获取活动商品关联详细信息
     */
    @PreAuthorize("@ss.hasPermi('marketing:product:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(promotionProductService.selectPromotionProductById(id));
    }

    /**
     * 新增活动商品关联
     */
    @PreAuthorize("@ss.hasPermi('marketing:product:add')")
    @Log(title = "活动商品关联", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PromotionProduct promotionProduct)
    {
        return toAjax(promotionProductService.insertPromotionProduct(promotionProduct));
    }

    /**
     * 修改活动商品关联
     */
    @PreAuthorize("@ss.hasPermi('marketing:product:edit')")
    @Log(title = "活动商品关联", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PromotionProduct promotionProduct)
    {
        return toAjax(promotionProductService.updatePromotionProduct(promotionProduct));
    }

    /**
     * 删除活动商品关联
     */
    @PreAuthorize("@ss.hasPermi('marketing:product:remove')")
    @Log(title = "活动商品关联", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(promotionProductService.deletePromotionProductByIds(ids));
    }
}
