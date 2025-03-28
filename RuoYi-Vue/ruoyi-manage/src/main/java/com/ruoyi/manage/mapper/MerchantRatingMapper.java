package com.ruoyi.manage.mapper;

import java.util.List;
import com.ruoyi.manage.domain.MerchantRating;

/**
 * 商家评级Mapper接口
 * 
 * @author shiro
 * @date 2025-03-28
 */
public interface MerchantRatingMapper 
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
     * 删除商家评级
     * 
     * @param id 商家评级主键
     * @return 结果
     */
    public int deleteMerchantRatingById(Long id);

    /**
     * 批量删除商家评级
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMerchantRatingByIds(Long[] ids);
}
