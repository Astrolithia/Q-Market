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
import com.ruoyi.manage.domain.MerchantRating;
import com.ruoyi.manage.service.IMerchantRatingService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 商家评级Controller
 * 
 * @author shiro
 * @date 2025-03-28
 */
@RestController
@RequestMapping("/merchant/rating")
public class MerchantRatingController extends BaseController
{
    @Autowired
    private IMerchantRatingService merchantRatingService;

    /**
     * 查询商家评级列表
     */
    @PreAuthorize("@ss.hasPermi('merchant:rating:list')")
    @GetMapping("/list")
    public TableDataInfo list(MerchantRating merchantRating)
    {
        startPage();
        List<MerchantRating> list = merchantRatingService.selectMerchantRatingList(merchantRating);
        return getDataTable(list);
    }

    /**
     * 导出商家评级列表
     */
    @PreAuthorize("@ss.hasPermi('merchant:rating:export')")
    @Log(title = "商家评级", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MerchantRating merchantRating)
    {
        List<MerchantRating> list = merchantRatingService.selectMerchantRatingList(merchantRating);
        ExcelUtil<MerchantRating> util = new ExcelUtil<MerchantRating>(MerchantRating.class);
        util.exportExcel(response, list, "商家评级数据");
    }

    /**
     * 获取商家评级详细信息
     */
    @PreAuthorize("@ss.hasPermi('merchant:rating:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(merchantRatingService.selectMerchantRatingById(id));
    }

    /**
     * 新增商家评级
     */
    @PreAuthorize("@ss.hasPermi('merchant:rating:add')")
    @Log(title = "商家评级", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MerchantRating merchantRating)
    {
        return toAjax(merchantRatingService.insertMerchantRating(merchantRating));
    }

    /**
     * 修改商家评级
     */
    @PreAuthorize("@ss.hasPermi('merchant:rating:edit')")
    @Log(title = "商家评级", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MerchantRating merchantRating)
    {
        return toAjax(merchantRatingService.updateMerchantRating(merchantRating));
    }

    /**
     * 删除商家评级
     */
    @PreAuthorize("@ss.hasPermi('merchant:rating:remove')")
    @Log(title = "商家评级", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(merchantRatingService.deleteMerchantRatingByIds(ids));
    }
}
