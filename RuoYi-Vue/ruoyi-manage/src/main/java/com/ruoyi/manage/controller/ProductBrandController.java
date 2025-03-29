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
import com.ruoyi.manage.domain.ProductBrand;
import com.ruoyi.manage.service.IProductBrandService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 商品品牌管理Controller
 * 
 * @author shiro
 * @date 2025-03-29
 */
@RestController
@RequestMapping("/product/brand")
public class ProductBrandController extends BaseController
{
    @Autowired
    private IProductBrandService productBrandService;

    /**
     * 查询商品品牌管理列表
     */
    @PreAuthorize("@ss.hasPermi('product:brand:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProductBrand productBrand)
    {
        startPage();
        List<ProductBrand> list = productBrandService.selectProductBrandList(productBrand);
        return getDataTable(list);
    }

    /**
     * 导出商品品牌管理列表
     */
    @PreAuthorize("@ss.hasPermi('product:brand:export')")
    @Log(title = "商品品牌管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProductBrand productBrand)
    {
        List<ProductBrand> list = productBrandService.selectProductBrandList(productBrand);
        ExcelUtil<ProductBrand> util = new ExcelUtil<ProductBrand>(ProductBrand.class);
        util.exportExcel(response, list, "商品品牌管理数据");
    }

    /**
     * 获取商品品牌管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('product:brand:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(productBrandService.selectProductBrandById(id));
    }

    /**
     * 新增商品品牌管理
     */
    @PreAuthorize("@ss.hasPermi('product:brand:add')")
    @Log(title = "商品品牌管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProductBrand productBrand)
    {
        return toAjax(productBrandService.insertProductBrand(productBrand));
    }

    /**
     * 修改商品品牌管理
     */
    @PreAuthorize("@ss.hasPermi('product:brand:edit')")
    @Log(title = "商品品牌管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProductBrand productBrand)
    {
        return toAjax(productBrandService.updateProductBrand(productBrand));
    }

    /**
     * 删除商品品牌管理
     */
    @PreAuthorize("@ss.hasPermi('product:brand:remove')")
    @Log(title = "商品品牌管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(productBrandService.deleteProductBrandByIds(ids));
    }
}
