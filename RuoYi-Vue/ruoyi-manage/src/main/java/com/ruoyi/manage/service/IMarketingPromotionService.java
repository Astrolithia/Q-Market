package com.ruoyi.manage.service;

import java.util.List;
import com.ruoyi.manage.domain.MarketingPromotion;

/**
 * 满减活动管理Service接口
 * 
 * @author shiro
 * @date 2025-03-27
 */
public interface IMarketingPromotionService 
{
    /**
     * 查询满减活动管理
     * 
     * @param id 满减活动管理主键
     * @return 满减活动管理
     */
    public MarketingPromotion selectMarketingPromotionById(Long id);

    /**
     * 查询满减活动管理列表
     * 
     * @param marketingPromotion 满减活动管理
     * @return 满减活动管理集合
     */
    public List<MarketingPromotion> selectMarketingPromotionList(MarketingPromotion marketingPromotion);

    /**
     * 新增满减活动管理
     * 
     * @param marketingPromotion 满减活动管理
     * @return 结果
     */
    public int insertMarketingPromotion(MarketingPromotion marketingPromotion);

    /**
     * 修改满减活动管理
     * 
     * @param marketingPromotion 满减活动管理
     * @return 结果
     */
    public int updateMarketingPromotion(MarketingPromotion marketingPromotion);

    /**
     * 批量删除满减活动管理
     * 
     * @param ids 需要删除的满减活动管理主键集合
     * @return 结果
     */
    public int deleteMarketingPromotionByIds(Long[] ids);

    /**
     * 删除满减活动管理信息
     * 
     * @param id 满减活动管理主键
     * @return 结果
     */
    public int deleteMarketingPromotionById(Long id);
}
