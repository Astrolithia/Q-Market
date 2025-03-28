package com.ruoyi.manage.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.manage.mapper.MerchantSettlementMapper;
import com.ruoyi.manage.domain.MerchantSettlement;
import com.ruoyi.manage.service.IMerchantSettlementService;

/**
 * 商家结算管理Service业务层处理
 * 
 * @author shiro
 * @date 2025-03-28
 */
@Service
public class MerchantSettlementServiceImpl implements IMerchantSettlementService 
{
    @Autowired
    private MerchantSettlementMapper merchantSettlementMapper;

    /**
     * 查询商家结算管理
     * 
     * @param id 商家结算管理主键
     * @return 商家结算管理
     */
    @Override
    public MerchantSettlement selectMerchantSettlementById(Long id)
    {
        return merchantSettlementMapper.selectMerchantSettlementById(id);
    }

    /**
     * 查询商家结算管理列表
     * 
     * @param merchantSettlement 商家结算管理
     * @return 商家结算管理
     */
    @Override
    public List<MerchantSettlement> selectMerchantSettlementList(MerchantSettlement merchantSettlement)
    {
        return merchantSettlementMapper.selectMerchantSettlementList(merchantSettlement);
    }

    /**
     * 新增商家结算管理
     * 
     * @param merchantSettlement 商家结算管理
     * @return 结果
     */
    @Override
    public int insertMerchantSettlement(MerchantSettlement merchantSettlement)
    {
        merchantSettlement.setCreateTime(DateUtils.getNowDate());
        return merchantSettlementMapper.insertMerchantSettlement(merchantSettlement);
    }

    /**
     * 修改商家结算管理
     * 
     * @param merchantSettlement 商家结算管理
     * @return 结果
     */
    @Override
    public int updateMerchantSettlement(MerchantSettlement merchantSettlement)
    {
        merchantSettlement.setUpdateTime(DateUtils.getNowDate());
        return merchantSettlementMapper.updateMerchantSettlement(merchantSettlement);
    }

    /**
     * 批量删除商家结算管理
     * 
     * @param ids 需要删除的商家结算管理主键
     * @return 结果
     */
    @Override
    public int deleteMerchantSettlementByIds(Long[] ids)
    {
        return merchantSettlementMapper.deleteMerchantSettlementByIds(ids);
    }

    /**
     * 删除商家结算管理信息
     * 
     * @param id 商家结算管理主键
     * @return 结果
     */
    @Override
    public int deleteMerchantSettlementById(Long id)
    {
        return merchantSettlementMapper.deleteMerchantSettlementById(id);
    }
}
