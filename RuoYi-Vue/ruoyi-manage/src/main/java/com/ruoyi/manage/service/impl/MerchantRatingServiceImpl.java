package com.ruoyi.manage.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.manage.mapper.MerchantRatingMapper;
import com.ruoyi.manage.domain.MerchantRating;
import com.ruoyi.manage.service.IMerchantRatingService;

/**
 * 商家评级Service业务层处理
 * 
 * @author shiro
 * @date 2025-03-28
 */
@Service
public class MerchantRatingServiceImpl implements IMerchantRatingService 
{
    @Autowired
    private MerchantRatingMapper merchantRatingMapper;

    /**
     * 查询商家评级
     * 
     * @param id 商家评级主键
     * @return 商家评级
     */
    @Override
    public MerchantRating selectMerchantRatingById(Long id)
    {
        return merchantRatingMapper.selectMerchantRatingById(id);
    }

    /**
     * 查询商家评级列表
     * 
     * @param merchantRating 商家评级
     * @return 商家评级
     */
    @Override
    public List<MerchantRating> selectMerchantRatingList(MerchantRating merchantRating)
    {
        return merchantRatingMapper.selectMerchantRatingList(merchantRating);
    }

    /**
     * 新增商家评级
     * 
     * @param merchantRating 商家评级
     * @return 结果
     */
    @Override
    public int insertMerchantRating(MerchantRating merchantRating)
    {
        merchantRating.setCreateTime(DateUtils.getNowDate());
        return merchantRatingMapper.insertMerchantRating(merchantRating);
    }

    /**
     * 修改商家评级
     * 
     * @param merchantRating 商家评级
     * @return 结果
     */
    @Override
    public int updateMerchantRating(MerchantRating merchantRating)
    {
        merchantRating.setUpdateTime(DateUtils.getNowDate());
        return merchantRatingMapper.updateMerchantRating(merchantRating);
    }

    /**
     * 批量删除商家评级
     * 
     * @param ids 需要删除的商家评级主键
     * @return 结果
     */
    @Override
    public int deleteMerchantRatingByIds(Long[] ids)
    {
        return merchantRatingMapper.deleteMerchantRatingByIds(ids);
    }

    /**
     * 删除商家评级信息
     * 
     * @param id 商家评级主键
     * @return 结果
     */
    @Override
    public int deleteMerchantRatingById(Long id)
    {
        return merchantRatingMapper.deleteMerchantRatingById(id);
    }
}
