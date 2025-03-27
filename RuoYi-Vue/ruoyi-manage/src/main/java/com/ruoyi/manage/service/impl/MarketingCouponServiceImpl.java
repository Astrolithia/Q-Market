package com.ruoyi.manage.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.manage.mapper.MarketingCouponMapper;
import com.ruoyi.manage.domain.MarketingCoupon;
import com.ruoyi.manage.service.IMarketingCouponService;

/**
 * 优惠券管理Service业务层处理
 * 
 * @author shiro
 * @date 2025-03-27
 */
@Service
public class MarketingCouponServiceImpl implements IMarketingCouponService 
{
    @Autowired
    private MarketingCouponMapper marketingCouponMapper;

    /**
     * 查询优惠券管理
     * 
     * @param id 优惠券管理主键
     * @return 优惠券管理
     */
    @Override
    public MarketingCoupon selectMarketingCouponById(Long id)
    {
        return marketingCouponMapper.selectMarketingCouponById(id);
    }

    /**
     * 查询优惠券管理列表
     * 
     * @param marketingCoupon 优惠券管理
     * @return 优惠券管理
     */
    @Override
    public List<MarketingCoupon> selectMarketingCouponList(MarketingCoupon marketingCoupon)
    {
        return marketingCouponMapper.selectMarketingCouponList(marketingCoupon);
    }

    /**
     * 新增优惠券管理
     * 
     * @param marketingCoupon 优惠券管理
     * @return 结果
     */
    @Override
    public int insertMarketingCoupon(MarketingCoupon marketingCoupon)
    {
        marketingCoupon.setCreateTime(DateUtils.getNowDate());
        return marketingCouponMapper.insertMarketingCoupon(marketingCoupon);
    }

    /**
     * 修改优惠券管理
     * 
     * @param marketingCoupon 优惠券管理
     * @return 结果
     */
    @Override
    public int updateMarketingCoupon(MarketingCoupon marketingCoupon)
    {
        marketingCoupon.setUpdateTime(DateUtils.getNowDate());
        return marketingCouponMapper.updateMarketingCoupon(marketingCoupon);
    }

    /**
     * 批量删除优惠券管理
     * 
     * @param ids 需要删除的优惠券管理主键
     * @return 结果
     */
    @Override
    public int deleteMarketingCouponByIds(Long[] ids)
    {
        return marketingCouponMapper.deleteMarketingCouponByIds(ids);
    }

    /**
     * 删除优惠券管理信息
     * 
     * @param id 优惠券管理主键
     * @return 结果
     */
    @Override
    public int deleteMarketingCouponById(Long id)
    {
        return marketingCouponMapper.deleteMarketingCouponById(id);
    }
}
