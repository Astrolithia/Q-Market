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
import com.ruoyi.manage.domain.Merchant;
import com.ruoyi.manage.service.IMerchantService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 商家信息管理Controller
 * 
 * @author shiro
 * @date 2025-03-27
 */
@RestController
@RequestMapping("/merchant/merchant")
public class MerchantController extends BaseController
{
    @Autowired
    private IMerchantService merchantService;

    /**
     * 查询商家信息管理列表
     */
    @PreAuthorize("@ss.hasPermi('merchant:merchant:list')")
    @GetMapping("/list")
    public TableDataInfo list(Merchant merchant)
    {
        startPage();
        List<Merchant> list = merchantService.selectMerchantList(merchant);
        return getDataTable(list);
    }

    /**
     * 导出商家信息管理列表
     */
    @PreAuthorize("@ss.hasPermi('merchant:merchant:export')")
    @Log(title = "商家信息管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Merchant merchant)
    {
        List<Merchant> list = merchantService.selectMerchantList(merchant);
        ExcelUtil<Merchant> util = new ExcelUtil<Merchant>(Merchant.class);
        util.exportExcel(response, list, "商家信息管理数据");
    }

    /**
     * 获取商家信息管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('merchant:merchant:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(merchantService.selectMerchantById(id));
    }

    /**
     * 新增商家信息管理
     */
    @PreAuthorize("@ss.hasPermi('merchant:merchant:add')")
    @Log(title = "商家信息管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Merchant merchant)
    {
        return toAjax(merchantService.insertMerchant(merchant));
    }

    /**
     * 修改商家信息管理
     */
    @PreAuthorize("@ss.hasPermi('merchant:merchant:edit')")
    @Log(title = "商家信息管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Merchant merchant)
    {
        return toAjax(merchantService.updateMerchant(merchant));
    }

    /**
     * 删除商家信息管理
     */
    @PreAuthorize("@ss.hasPermi('merchant:merchant:remove')")
    @Log(title = "商家信息管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(merchantService.deleteMerchantByIds(ids));
    }
}
