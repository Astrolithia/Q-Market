package com.ruoyi.manage.mapper;

import java.util.List;
import com.ruoyi.manage.domain.MarketingCoupon;

/**
 * 优惠券管理Mapper接口
 * 
 * @author shiro
 * @date 2025-03-27
 */
public interface MarketingCouponMapper 
{
    /**
     * 查询优惠券管理
     * 
     * @param id 优惠券管理主键
     * @return 优惠券管理
     */
    public MarketingCoupon selectMarketingCouponById(Long id);

    /**
     * 查询优惠券管理列表
     * 
     * @param marketingCoupon 优惠券管理
     * @return 优惠券管理集合
     */
    public List<MarketingCoupon> selectMarketingCouponList(MarketingCoupon marketingCoupon);

    /**
     * 新增优惠券管理
     * 
     * @param marketingCoupon 优惠券管理
     * @return 结果
     */
    public int insertMarketingCoupon(MarketingCoupon marketingCoupon);

    /**
     * 修改优惠券管理
     * 
     * @param marketingCoupon 优惠券管理
     * @return 结果
     */
    public int updateMarketingCoupon(MarketingCoupon marketingCoupon);

    /**
     * 删除优惠券管理
     * 
     * @param id 优惠券管理主键
     * @return 结果
     */
    public int deleteMarketingCouponById(Long id);

    /**
     * 批量删除优惠券管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMarketingCouponByIds(Long[] ids);
}
