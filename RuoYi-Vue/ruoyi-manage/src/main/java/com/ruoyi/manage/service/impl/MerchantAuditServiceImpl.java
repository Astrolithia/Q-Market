package com.ruoyi.manage.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.manage.mapper.MerchantAuditMapper;
import com.ruoyi.manage.domain.MerchantAudit;
import com.ruoyi.manage.service.IMerchantAuditService;

/**
 * 商家审核Service业务层处理
 * 
 * @author shiro
 * @date 2025-03-28
 */
@Service
public class MerchantAuditServiceImpl implements IMerchantAuditService 
{
    @Autowired
    private MerchantAuditMapper merchantAuditMapper;

    /**
     * 查询商家审核
     * 
     * @param id 商家审核主键
     * @return 商家审核
     */
    @Override
    public MerchantAudit selectMerchantAuditById(Long id)
    {
        return merchantAuditMapper.selectMerchantAuditById(id);
    }

    /**
     * 查询商家审核列表
     * 
     * @param merchantAudit 商家审核
     * @return 商家审核
     */
    @Override
    public List<MerchantAudit> selectMerchantAuditList(MerchantAudit merchantAudit)
    {
        return merchantAuditMapper.selectMerchantAuditList(merchantAudit);
    }

    /**
     * 新增商家审核
     * 
     * @param merchantAudit 商家审核
     * @return 结果
     */
    @Override
    public int insertMerchantAudit(MerchantAudit merchantAudit)
    {
        merchantAudit.setCreateTime(DateUtils.getNowDate());
        return merchantAuditMapper.insertMerchantAudit(merchantAudit);
    }

    /**
     * 修改商家审核
     * 
     * @param merchantAudit 商家审核
     * @return 结果
     */
    @Override
    public int updateMerchantAudit(MerchantAudit merchantAudit)
    {
        merchantAudit.setUpdateTime(DateUtils.getNowDate());
        return merchantAuditMapper.updateMerchantAudit(merchantAudit);
    }

    /**
     * 批量删除商家审核
     * 
     * @param ids 需要删除的商家审核主键
     * @return 结果
     */
    @Override
    public int deleteMerchantAuditByIds(Long[] ids)
    {
        return merchantAuditMapper.deleteMerchantAuditByIds(ids);
    }

    /**
     * 删除商家审核信息
     * 
     * @param id 商家审核主键
     * @return 结果
     */
    @Override
    public int deleteMerchantAuditById(Long id)
    {
        return merchantAuditMapper.deleteMerchantAuditById(id);
    }
}
