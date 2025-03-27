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
import com.ruoyi.manage.domain.MerchantSettlement;
import com.ruoyi.manage.service.IMerchantSettlementService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 商家结算管理Controller
 * 
 * @author shiro
 * @date 2025-03-27
 */
@RestController
@RequestMapping("/merchant/settlement")
public class MerchantSettlementController extends BaseController
{
    @Autowired
    private IMerchantSettlementService merchantSettlementService;

    /**
     * 查询商家结算管理列表
     */
    @PreAuthorize("@ss.hasPermi('merchant:settlement:list')")
    @GetMapping("/list")
    public TableDataInfo list(MerchantSettlement merchantSettlement)
    {
        startPage();
        List<MerchantSettlement> list = merchantSettlementService.selectMerchantSettlementList(merchantSettlement);
        return getDataTable(list);
    }

    /**
     * 导出商家结算管理列表
     */
    @PreAuthorize("@ss.hasPermi('merchant:settlement:export')")
    @Log(title = "商家结算管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MerchantSettlement merchantSettlement)
    {
        List<MerchantSettlement> list = merchantSettlementService.selectMerchantSettlementList(merchantSettlement);
        ExcelUtil<MerchantSettlement> util = new ExcelUtil<MerchantSettlement>(MerchantSettlement.class);
        util.exportExcel(response, list, "商家结算管理数据");
    }

    /**
     * 获取商家结算管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('merchant:settlement:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(merchantSettlementService.selectMerchantSettlementById(id));
    }

    /**
     * 新增商家结算管理
     */
    @PreAuthorize("@ss.hasPermi('merchant:settlement:add')")
    @Log(title = "商家结算管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MerchantSettlement merchantSettlement)
    {
        return toAjax(merchantSettlementService.insertMerchantSettlement(merchantSettlement));
    }

    /**
     * 修改商家结算管理
     */
    @PreAuthorize("@ss.hasPermi('merchant:settlement:edit')")
    @Log(title = "商家结算管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MerchantSettlement merchantSettlement)
    {
        return toAjax(merchantSettlementService.updateMerchantSettlement(merchantSettlement));
    }

    /**
     * 删除商家结算管理
     */
    @PreAuthorize("@ss.hasPermi('merchant:settlement:remove')")
    @Log(title = "商家结算管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(merchantSettlementService.deleteMerchantSettlementByIds(ids));
    }
}
