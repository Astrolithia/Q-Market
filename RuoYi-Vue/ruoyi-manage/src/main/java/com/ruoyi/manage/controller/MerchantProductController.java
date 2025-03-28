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
import com.ruoyi.manage.domain.MerchantProduct;
import com.ruoyi.manage.service.IMerchantProductService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 商品信息管理Controller
 * 
 * @author shiro
 * @date 2025-03-28
 */
@RestController
@RequestMapping("/merchant/product")
public class MerchantProductController extends BaseController
{
    @Autowired
    private IMerchantProductService merchantProductService;

    /**
     * 查询商品信息管理列表
     */
    @PreAuthorize("@ss.hasPermi('merchant:product:list')")
    @GetMapping("/list")
    public TableDataInfo list(MerchantProduct merchantProduct)
    {
        startPage();
        List<MerchantProduct> list = merchantProductService.selectMerchantProductList(merchantProduct);
        return getDataTable(list);
    }

    /**
     * 导出商品信息管理列表
     */
    @PreAuthorize("@ss.hasPermi('merchant:product:export')")
    @Log(title = "商品信息管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MerchantProduct merchantProduct)
    {
        List<MerchantProduct> list = merchantProductService.selectMerchantProductList(merchantProduct);
        ExcelUtil<MerchantProduct> util = new ExcelUtil<MerchantProduct>(MerchantProduct.class);
        util.exportExcel(response, list, "商品信息管理数据");
    }

    /**
     * 获取商品信息管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('merchant:product:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(merchantProductService.selectMerchantProductById(id));
    }

    /**
     * 新增商品信息管理
     */
    @PreAuthorize("@ss.hasPermi('merchant:product:add')")
    @Log(title = "商品信息管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MerchantProduct merchantProduct)
    {
        return toAjax(merchantProductService.insertMerchantProduct(merchantProduct));
    }

    /**
     * 修改商品信息管理
     */
    @PreAuthorize("@ss.hasPermi('merchant:product:edit')")
    @Log(title = "商品信息管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MerchantProduct merchantProduct)
    {
        return toAjax(merchantProductService.updateMerchantProduct(merchantProduct));
    }

    /**
     * 删除商品信息管理
     */
    @PreAuthorize("@ss.hasPermi('merchant:product:remove')")
    @Log(title = "商品信息管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(merchantProductService.deleteMerchantProductByIds(ids));
    }
}
