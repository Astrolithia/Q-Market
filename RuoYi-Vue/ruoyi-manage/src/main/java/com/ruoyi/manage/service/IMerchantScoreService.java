package com.ruoyi.manage.service;

import java.util.List;
import com.ruoyi.manage.domain.MerchantScore;

/**
 * 商家评级Service接口
 * 
 * @author shiro
 * @date 2025-03-27
 */
public interface IMerchantScoreService 
{
    /**
     * 查询商家评级
     * 
     * @param id 商家评级主键
     * @return 商家评级
     */
    public MerchantScore selectMerchantScoreById(Long id);

    /**
     * 查询商家评级列表
     * 
     * @param merchantScore 商家评级
     * @return 商家评级集合
     */
    public List<MerchantScore> selectMerchantScoreList(MerchantScore merchantScore);

    /**
     * 新增商家评级
     * 
     * @param merchantScore 商家评级
     * @return 结果
     */
    public int insertMerchantScore(MerchantScore merchantScore);

    /**
     * 修改商家评级
     * 
     * @param merchantScore 商家评级
     * @return 结果
     */
    public int updateMerchantScore(MerchantScore merchantScore);

    /**
     * 批量删除商家评级
     * 
     * @param ids 需要删除的商家评级主键集合
     * @return 结果
     */
    public int deleteMerchantScoreByIds(Long[] ids);

    /**
     * 删除商家评级信息
     * 
     * @param id 商家评级主键
     * @return 结果
     */
    public int deleteMerchantScoreById(Long id);
}
