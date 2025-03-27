package com.ruoyi.manage.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.manage.mapper.MerchantScoreMapper;
import com.ruoyi.manage.domain.MerchantScore;
import com.ruoyi.manage.service.IMerchantScoreService;

/**
 * 商家评级Service业务层处理
 * 
 * @author shiro
 * @date 2025-03-27
 */
@Service
public class MerchantScoreServiceImpl implements IMerchantScoreService 
{
    @Autowired
    private MerchantScoreMapper merchantScoreMapper;

    /**
     * 查询商家评级
     * 
     * @param id 商家评级主键
     * @return 商家评级
     */
    @Override
    public MerchantScore selectMerchantScoreById(Long id)
    {
        return merchantScoreMapper.selectMerchantScoreById(id);
    }

    /**
     * 查询商家评级列表
     * 
     * @param merchantScore 商家评级
     * @return 商家评级
     */
    @Override
    public List<MerchantScore> selectMerchantScoreList(MerchantScore merchantScore)
    {
        return merchantScoreMapper.selectMerchantScoreList(merchantScore);
    }

    /**
     * 新增商家评级
     * 
     * @param merchantScore 商家评级
     * @return 结果
     */
    @Override
    public int insertMerchantScore(MerchantScore merchantScore)
    {
        merchantScore.setCreateTime(DateUtils.getNowDate());
        return merchantScoreMapper.insertMerchantScore(merchantScore);
    }

    /**
     * 修改商家评级
     * 
     * @param merchantScore 商家评级
     * @return 结果
     */
    @Override
    public int updateMerchantScore(MerchantScore merchantScore)
    {
        merchantScore.setUpdateTime(DateUtils.getNowDate());
        return merchantScoreMapper.updateMerchantScore(merchantScore);
    }

    /**
     * 批量删除商家评级
     * 
     * @param ids 需要删除的商家评级主键
     * @return 结果
     */
    @Override
    public int deleteMerchantScoreByIds(Long[] ids)
    {
        return merchantScoreMapper.deleteMerchantScoreByIds(ids);
    }

    /**
     * 删除商家评级信息
     * 
     * @param id 商家评级主键
     * @return 结果
     */
    @Override
    public int deleteMerchantScoreById(Long id)
    {
        return merchantScoreMapper.deleteMerchantScoreById(id);
    }
}
