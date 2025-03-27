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
import com.ruoyi.manage.domain.ProductSku;
import com.ruoyi.manage.service.IProductSkuService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 商品SKUController
 * 
 * @author shiro
 * @date 2025-03-27
 */
@RestController
@RequestMapping("/sku/sku")
public class ProductSkuController extends BaseController
{
    @Autowired
    private IProductSkuService productSkuService;

    /**
     * 查询商品SKU列表
     */
    @PreAuthorize("@ss.hasPermi('sku:sku:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProductSku productSku)
    {
        startPage();
        List<ProductSku> list = productSkuService.selectProductSkuList(productSku);
        return getDataTable(list);
    }

    /**
     * 导出商品SKU列表
     */
    @PreAuthorize("@ss.hasPermi('sku:sku:export')")
    @Log(title = "商品SKU", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProductSku productSku)
    {
        List<ProductSku> list = productSkuService.selectProductSkuList(productSku);
        ExcelUtil<ProductSku> util = new ExcelUtil<ProductSku>(ProductSku.class);
        util.exportExcel(response, list, "商品SKU数据");
    }

    /**
     * 获取商品SKU详细信息
     */
    @PreAuthorize("@ss.hasPermi('sku:sku:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(productSkuService.selectProductSkuById(id));
    }

    /**
     * 新增商品SKU
     */
    @PreAuthorize("@ss.hasPermi('sku:sku:add')")
    @Log(title = "商品SKU", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProductSku productSku)
    {
        return toAjax(productSkuService.insertProductSku(productSku));
    }

    /**
     * 修改商品SKU
     */
    @PreAuthorize("@ss.hasPermi('sku:sku:edit')")
    @Log(title = "商品SKU", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProductSku productSku)
    {
        return toAjax(productSkuService.updateProductSku(productSku));
    }

    /**
     * 删除商品SKU
     */
    @PreAuthorize("@ss.hasPermi('sku:sku:remove')")
    @Log(title = "商品SKU", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(productSkuService.deleteProductSkuByIds(ids));
    }
}
