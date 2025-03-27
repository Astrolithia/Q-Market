package com.ruoyi.manage.service;

import java.util.List;
import com.ruoyi.manage.domain.CustomerLevel;

/**
 * 会员等级管理Service接口
 * 
 * @author shiro
 * @date 2025-03-27
 */
public interface ICustomerLevelService 
{
    /**
     * 查询会员等级管理
     * 
     * @param id 会员等级管理主键
     * @return 会员等级管理
     */
    public CustomerLevel selectCustomerLevelById(Long id);

    /**
     * 查询会员等级管理列表
     * 
     * @param customerLevel 会员等级管理
     * @return 会员等级管理集合
     */
    public List<CustomerLevel> selectCustomerLevelList(CustomerLevel customerLevel);

    /**
     * 新增会员等级管理
     * 
     * @param customerLevel 会员等级管理
     * @return 结果
     */
    public int insertCustomerLevel(CustomerLevel customerLevel);

    /**
     * 修改会员等级管理
     * 
     * @param customerLevel 会员等级管理
     * @return 结果
     */
    public int updateCustomerLevel(CustomerLevel customerLevel);

    /**
     * 批量删除会员等级管理
     * 
     * @param ids 需要删除的会员等级管理主键集合
     * @return 结果
     */
    public int deleteCustomerLevelByIds(Long[] ids);

    /**
     * 删除会员等级管理信息
     * 
     * @param id 会员等级管理主键
     * @return 结果
     */
    public int deleteCustomerLevelById(Long id);
}
