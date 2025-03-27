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
import com.ruoyi.manage.domain.MerchantScore;
import com.ruoyi.manage.service.IMerchantScoreService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 商家评级Controller
 * 
 * @author shiro
 * @date 2025-03-27
 */
@RestController
@RequestMapping("/merchant/score")
public class MerchantScoreController extends BaseController
{
    @Autowired
    private IMerchantScoreService merchantScoreService;

    /**
     * 查询商家评级列表
     */
    @PreAuthorize("@ss.hasPermi('merchant:score:list')")
    @GetMapping("/list")
    public TableDataInfo list(MerchantScore merchantScore)
    {
        startPage();
        List<MerchantScore> list = merchantScoreService.selectMerchantScoreList(merchantScore);
        return getDataTable(list);
    }

    /**
     * 导出商家评级列表
     */
    @PreAuthorize("@ss.hasPermi('merchant:score:export')")
    @Log(title = "商家评级", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MerchantScore merchantScore)
    {
        List<MerchantScore> list = merchantScoreService.selectMerchantScoreList(merchantScore);
        ExcelUtil<MerchantScore> util = new ExcelUtil<MerchantScore>(MerchantScore.class);
        util.exportExcel(response, list, "商家评级数据");
    }

    /**
     * 获取商家评级详细信息
     */
    @PreAuthorize("@ss.hasPermi('merchant:score:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(merchantScoreService.selectMerchantScoreById(id));
    }

    /**
     * 新增商家评级
     */
    @PreAuthorize("@ss.hasPermi('merchant:score:add')")
    @Log(title = "商家评级", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MerchantScore merchantScore)
    {
        return toAjax(merchantScoreService.insertMerchantScore(merchantScore));
    }

    /**
     * 修改商家评级
     */
    @PreAuthorize("@ss.hasPermi('merchant:score:edit')")
    @Log(title = "商家评级", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MerchantScore merchantScore)
    {
        return toAjax(merchantScoreService.updateMerchantScore(merchantScore));
    }

    /**
     * 删除商家评级
     */
    @PreAuthorize("@ss.hasPermi('merchant:score:remove')")
    @Log(title = "商家评级", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(merchantScoreService.deleteMerchantScoreByIds(ids));
    }
}
