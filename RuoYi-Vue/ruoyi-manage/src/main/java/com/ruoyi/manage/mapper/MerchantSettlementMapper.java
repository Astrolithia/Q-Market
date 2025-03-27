package com.ruoyi.manage.mapper;

import java.util.List;
import com.ruoyi.manage.domain.MerchantSettlement;

/**
 * 商家结算管理Mapper接口
 * 
 * @author shiro
 * @date 2025-03-27
 */
public interface MerchantSettlementMapper 
{
    /**
     * 查询商家结算管理
     * 
     * @param id 商家结算管理主键
     * @return 商家结算管理
     */
    public MerchantSettlement selectMerchantSettlementById(Long id);

    /**
     * 查询商家结算管理列表
     * 
     * @param merchantSettlement 商家结算管理
     * @return 商家结算管理集合
     */
    public List<MerchantSettlement> selectMerchantSettlementList(MerchantSettlement merchantSettlement);

    /**
     * 新增商家结算管理
     * 
     * @param merchantSettlement 商家结算管理
     * @return 结果
     */
    public int insertMerchantSettlement(MerchantSettlement merchantSettlement);

    /**
     * 修改商家结算管理
     * 
     * @param merchantSettlement 商家结算管理
     * @return 结果
     */
    public int updateMerchantSettlement(MerchantSettlement merchantSettlement);

    /**
     * 删除商家结算管理
     * 
     * @param id 商家结算管理主键
     * @return 结果
     */
    public int deleteMerchantSettlementById(Long id);

    /**
     * 批量删除商家结算管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMerchantSettlementByIds(Long[] ids);
}
