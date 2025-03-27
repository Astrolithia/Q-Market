package com.ruoyi.manage.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.manage.mapper.MerchantMapper;
import com.ruoyi.manage.domain.Merchant;
import com.ruoyi.manage.service.IMerchantService;

/**
 * 商家信息管理Service业务层处理
 * 
 * @author shiro
 * @date 2025-03-27
 */
@Service
public class MerchantServiceImpl implements IMerchantService 
{
    @Autowired
    private MerchantMapper merchantMapper;

    /**
     * 查询商家信息管理
     * 
     * @param id 商家信息管理主键
     * @return 商家信息管理
     */
    @Override
    public Merchant selectMerchantById(Long id)
    {
        return merchantMapper.selectMerchantById(id);
    }

    /**
     * 查询商家信息管理列表
     * 
     * @param merchant 商家信息管理
     * @return 商家信息管理
     */
    @Override
    public List<Merchant> selectMerchantList(Merchant merchant)
    {
        return merchantMapper.selectMerchantList(merchant);
    }

    /**
     * 新增商家信息管理
     * 
     * @param merchant 商家信息管理
     * @return 结果
     */
    @Override
    public int insertMerchant(Merchant merchant)
    {
        merchant.setCreateTime(DateUtils.getNowDate());
        return merchantMapper.insertMerchant(merchant);
    }

    /**
     * 修改商家信息管理
     * 
     * @param merchant 商家信息管理
     * @return 结果
     */
    @Override
    public int updateMerchant(Merchant merchant)
    {
        merchant.setUpdateTime(DateUtils.getNowDate());
        return merchantMapper.updateMerchant(merchant);
    }

    /**
     * 批量删除商家信息管理
     * 
     * @param ids 需要删除的商家信息管理主键
     * @return 结果
     */
    @Override
    public int deleteMerchantByIds(Long[] ids)
    {
        return merchantMapper.deleteMerchantByIds(ids);
    }

    /**
     * 删除商家信息管理信息
     * 
     * @param id 商家信息管理主键
     * @return 结果
     */
    @Override
    public int deleteMerchantById(Long id)
    {
        return merchantMapper.deleteMerchantById(id);
    }
}
