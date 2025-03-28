package com.ruoyi.manage.service;

import java.util.List;
import com.ruoyi.manage.domain.MerchantAudit;

/**
 * 商家审核Service接口
 * 
 * @author shiro
 * @date 2025-03-28
 */
public interface IMerchantAuditService 
{
    /**
     * 查询商家审核
     * 
     * @param id 商家审核主键
     * @return 商家审核
     */
    public MerchantAudit selectMerchantAuditById(Long id);

    /**
     * 查询商家审核列表
     * 
     * @param merchantAudit 商家审核
     * @return 商家审核集合
     */
    public List<MerchantAudit> selectMerchantAuditList(MerchantAudit merchantAudit);

    /**
     * 新增商家审核
     * 
     * @param merchantAudit 商家审核
     * @return 结果
     */
    public int insertMerchantAudit(MerchantAudit merchantAudit);

    /**
     * 修改商家审核
     * 
     * @param merchantAudit 商家审核
     * @return 结果
     */
    public int updateMerchantAudit(MerchantAudit merchantAudit);

    /**
     * 批量删除商家审核
     * 
     * @param ids 需要删除的商家审核主键集合
     * @return 结果
     */
    public int deleteMerchantAuditByIds(Long[] ids);

    /**
     * 删除商家审核信息
     * 
     * @param id 商家审核主键
     * @return 结果
     */
    public int deleteMerchantAuditById(Long id);
}
