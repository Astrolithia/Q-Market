package com.ruoyi.manage.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.manage.mapper.MarketingPromotionMapper;
import com.ruoyi.manage.domain.MarketingPromotion;
import com.ruoyi.manage.service.IMarketingPromotionService;

/**
 * 满减活动管理Service业务层处理
 * 
 * @author shiro
 * @date 2025-03-27
 */
@Service
public class MarketingPromotionServiceImpl implements IMarketingPromotionService 
{
    @Autowired
    private MarketingPromotionMapper marketingPromotionMapper;

    /**
     * 查询满减活动管理
     * 
     * @param id 满减活动管理主键
     * @return 满减活动管理
     */
    @Override
    public MarketingPromotion selectMarketingPromotionById(Long id)
    {
        return marketingPromotionMapper.selectMarketingPromotionById(id);
    }

    /**
     * 查询满减活动管理列表
     * 
     * @param marketingPromotion 满减活动管理
     * @return 满减活动管理
     */
    @Override
    public List<MarketingPromotion> selectMarketingPromotionList(MarketingPromotion marketingPromotion)
    {
        return marketingPromotionMapper.selectMarketingPromotionList(marketingPromotion);
    }

    /**
     * 新增满减活动管理
     * 
     * @param marketingPromotion 满减活动管理
     * @return 结果
     */
    @Override
    public int insertMarketingPromotion(MarketingPromotion marketingPromotion)
    {
        marketingPromotion.setCreateTime(DateUtils.getNowDate());
        return marketingPromotionMapper.insertMarketingPromotion(marketingPromotion);
    }

    /**
     * 修改满减活动管理
     * 
     * @param marketingPromotion 满减活动管理
     * @return 结果
     */
    @Override
    public int updateMarketingPromotion(MarketingPromotion marketingPromotion)
    {
        marketingPromotion.setUpdateTime(DateUtils.getNowDate());
        return marketingPromotionMapper.updateMarketingPromotion(marketingPromotion);
    }

    /**
     * 批量删除满减活动管理
     * 
     * @param ids 需要删除的满减活动管理主键
     * @return 结果
     */
    @Override
    public int deleteMarketingPromotionByIds(Long[] ids)
    {
        return marketingPromotionMapper.deleteMarketingPromotionByIds(ids);
    }

    /**
     * 删除满减活动管理信息
     * 
     * @param id 满减活动管理主键
     * @return 结果
     */
    @Override
    public int deleteMarketingPromotionById(Long id)
    {
        return marketingPromotionMapper.deleteMarketingPromotionById(id);
    }
}
