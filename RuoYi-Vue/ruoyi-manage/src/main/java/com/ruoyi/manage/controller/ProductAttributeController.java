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
import com.ruoyi.manage.domain.ProductAttribute;
import com.ruoyi.manage.service.IProductAttributeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 商品属性管理Controller
 * 
 * @author shiro
 * @date 2025-03-29
 */
@RestController
@RequestMapping("/product/attribute")
public class ProductAttributeController extends BaseController
{
    @Autowired
    private IProductAttributeService productAttributeService;

    /**
     * 查询商品属性管理列表
     */
    @PreAuthorize("@ss.hasPermi('product:attribute:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProductAttribute productAttribute)
    {
        startPage();
        List<ProductAttribute> list = productAttributeService.selectProductAttributeList(productAttribute);
        return getDataTable(list);
    }

    /**
     * 导出商品属性管理列表
     */
    @PreAuthorize("@ss.hasPermi('product:attribute:export')")
    @Log(title = "商品属性管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProductAttribute productAttribute)
    {
        List<ProductAttribute> list = productAttributeService.selectProductAttributeList(productAttribute);
        ExcelUtil<ProductAttribute> util = new ExcelUtil<ProductAttribute>(ProductAttribute.class);
        util.exportExcel(response, list, "商品属性管理数据");
    }

    /**
     * 获取商品属性管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('product:attribute:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(productAttributeService.selectProductAttributeById(id));
    }

    /**
     * 新增商品属性管理
     */
    @PreAuthorize("@ss.hasPermi('product:attribute:add')")
    @Log(title = "商品属性管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProductAttribute productAttribute)
    {
        return toAjax(productAttributeService.insertProductAttribute(productAttribute));
    }

    /**
     * 修改商品属性管理
     */
    @PreAuthorize("@ss.hasPermi('product:attribute:edit')")
    @Log(title = "商品属性管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProductAttribute productAttribute)
    {
        return toAjax(productAttributeService.updateProductAttribute(productAttribute));
    }

    /**
     * 删除商品属性管理
     */
    @PreAuthorize("@ss.hasPermi('product:attribute:remove')")
    @Log(title = "商品属性管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(productAttributeService.deleteProductAttributeByIds(ids));
    }
}
