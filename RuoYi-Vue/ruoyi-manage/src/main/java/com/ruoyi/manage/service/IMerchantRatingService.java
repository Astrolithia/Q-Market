package com.ruoyi.manage.service;

import java.util.List;
import com.ruoyi.manage.domain.MerchantRating;

/**
 * 商家评级Service接口
 * 
 * @author shiro
 * @date 2025-03-28
 */
public interface IMerchantRatingService 
{
    /**
     * 查询商家评级
     * 
     * @param id 商家评级主键
     * @return 商家评级
     */
    public MerchantRating selectMerchantRatingById(Long id);

    /**
     * 查询商家评级列表
     * 
     * @param merchantRating 商家评级
     * @return 商家评级集合
     */
    public List<MerchantRating> selectMerchantRatingList(MerchantRating merchantRating);

    /**
     * 新增商家评级
     * 
     * @param merchantRating 商家评级
     * @return 结果
     */
    public int insertMerchantRating(MerchantRating merchantRating);

    /**
     * 修改商家评级
     * 
     * @param merchantRating 商家评级
     * @return 结果
     */
    public int updateMerchantRating(MerchantRating merchantRating);

    /**
     * 批量删除商家评级
     * 
     * @param ids 需要删除的商家评级主键集合
     * @return 结果
     */
    public int deleteMerchantRatingByIds(Long[] ids);

    /**
     * 删除商家评级信息
     * 
     * @param id 商家评级主键
     * @return 结果
     */
    public int deleteMerchantRatingById(Long id);
}
