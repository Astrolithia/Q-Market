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
import com.ruoyi.manage.domain.CustomerLevel;
import com.ruoyi.manage.service.ICustomerLevelService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 会员等级管理Controller
 * 
 * @author shiro
 * @date 2025-03-27
 */
@RestController
@RequestMapping("/level/level")
public class CustomerLevelController extends BaseController
{
    @Autowired
    private ICustomerLevelService customerLevelService;

    /**
     * 查询会员等级管理列表
     */
    @PreAuthorize("@ss.hasPermi('level:level:list')")
    @GetMapping("/list")
    public TableDataInfo list(CustomerLevel customerLevel)
    {
        startPage();
        List<CustomerLevel> list = customerLevelService.selectCustomerLevelList(customerLevel);
        return getDataTable(list);
    }

    /**
     * 导出会员等级管理列表
     */
    @PreAuthorize("@ss.hasPermi('level:level:export')")
    @Log(title = "会员等级管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CustomerLevel customerLevel)
    {
        List<CustomerLevel> list = customerLevelService.selectCustomerLevelList(customerLevel);
        ExcelUtil<CustomerLevel> util = new ExcelUtil<CustomerLevel>(CustomerLevel.class);
        util.exportExcel(response, list, "会员等级管理数据");
    }

    /**
     * 获取会员等级管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('level:level:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(customerLevelService.selectCustomerLevelById(id));
    }

    /**
     * 新增会员等级管理
     */
    @PreAuthorize("@ss.hasPermi('level:level:add')")
    @Log(title = "会员等级管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CustomerLevel customerLevel)
    {
        return toAjax(customerLevelService.insertCustomerLevel(customerLevel));
    }

    /**
     * 修改会员等级管理
     */
    @PreAuthorize("@ss.hasPermi('level:level:edit')")
    @Log(title = "会员等级管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CustomerLevel customerLevel)
    {
        return toAjax(customerLevelService.updateCustomerLevel(customerLevel));
    }

    /**
     * 删除会员等级管理
     */
    @PreAuthorize("@ss.hasPermi('level:level:remove')")
    @Log(title = "会员等级管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(customerLevelService.deleteCustomerLevelByIds(ids));
    }
}
