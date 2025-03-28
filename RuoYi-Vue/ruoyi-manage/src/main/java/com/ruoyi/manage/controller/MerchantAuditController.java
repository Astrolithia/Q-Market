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
import com.ruoyi.manage.domain.MerchantAudit;
import com.ruoyi.manage.service.IMerchantAuditService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 商家审核Controller
 * 
 * @author shiro
 * @date 2025-03-28
 */
@RestController
@RequestMapping("/merchant/audit")
public class MerchantAuditController extends BaseController
{
    @Autowired
    private IMerchantAuditService merchantAuditService;

    /**
     * 查询商家审核列表
     */
    @PreAuthorize("@ss.hasPermi('merchant:audit:list')")
    @GetMapping("/list")
    public TableDataInfo list(MerchantAudit merchantAudit)
    {
        startPage();
        List<MerchantAudit> list = merchantAuditService.selectMerchantAuditList(merchantAudit);
        return getDataTable(list);
    }

    /**
     * 导出商家审核列表
     */
    @PreAuthorize("@ss.hasPermi('merchant:audit:export')")
    @Log(title = "商家审核", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MerchantAudit merchantAudit)
    {
        List<MerchantAudit> list = merchantAuditService.selectMerchantAuditList(merchantAudit);
        ExcelUtil<MerchantAudit> util = new ExcelUtil<MerchantAudit>(MerchantAudit.class);
        util.exportExcel(response, list, "商家审核数据");
    }

    /**
     * 获取商家审核详细信息
     */
    @PreAuthorize("@ss.hasPermi('merchant:audit:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(merchantAuditService.selectMerchantAuditById(id));
    }

    /**
     * 新增商家审核
     */
    @PreAuthorize("@ss.hasPermi('merchant:audit:add')")
    @Log(title = "商家审核", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MerchantAudit merchantAudit)
    {
        return toAjax(merchantAuditService.insertMerchantAudit(merchantAudit));
    }

    /**
     * 修改商家审核
     */
    @PreAuthorize("@ss.hasPermi('merchant:audit:edit')")
    @Log(title = "商家审核", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MerchantAudit merchantAudit)
    {
        return toAjax(merchantAuditService.updateMerchantAudit(merchantAudit));
    }

    /**
     * 删除商家审核
     */
    @PreAuthorize("@ss.hasPermi('merchant:audit:remove')")
    @Log(title = "商家审核", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(merchantAuditService.deleteMerchantAuditByIds(ids));
    }
}
