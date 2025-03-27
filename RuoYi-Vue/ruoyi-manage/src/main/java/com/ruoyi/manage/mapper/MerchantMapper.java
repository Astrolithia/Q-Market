package com.ruoyi.manage.mapper;

import java.util.List;
import com.ruoyi.manage.domain.Merchant;

/**
 * 商家信息管理Mapper接口
 * 
 * @author shiro
 * @date 2025-03-27
 */
public interface MerchantMapper 
{
    /**
     * 查询商家信息管理
     * 
     * @param id 商家信息管理主键
     * @return 商家信息管理
     */
    public Merchant selectMerchantById(Long id);

    /**
     * 查询商家信息管理列表
     * 
     * @param merchant 商家信息管理
     * @return 商家信息管理集合
     */
    public List<Merchant> selectMerchantList(Merchant merchant);

    /**
     * 新增商家信息管理
     * 
     * @param merchant 商家信息管理
     * @return 结果
     */
    public int insertMerchant(Merchant merchant);

    /**
     * 修改商家信息管理
     * 
     * @param merchant 商家信息管理
     * @return 结果
     */
    public int updateMerchant(Merchant merchant);

    /**
     * 删除商家信息管理
     * 
     * @param id 商家信息管理主键
     * @return 结果
     */
    public int deleteMerchantById(Long id);

    /**
     * 批量删除商家信息管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMerchantByIds(Long[] ids);
}
