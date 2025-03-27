package com.ruoyi.manage.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.manage.mapper.CustomerLevelMapper;
import com.ruoyi.manage.domain.CustomerLevel;
import com.ruoyi.manage.service.ICustomerLevelService;

/**
 * 会员等级管理Service业务层处理
 * 
 * @author shiro
 * @date 2025-03-27
 */
@Service
public class CustomerLevelServiceImpl implements ICustomerLevelService 
{
    @Autowired
    private CustomerLevelMapper customerLevelMapper;

    /**
     * 查询会员等级管理
     * 
     * @param id 会员等级管理主键
     * @return 会员等级管理
     */
    @Override
    public CustomerLevel selectCustomerLevelById(Long id)
    {
        return customerLevelMapper.selectCustomerLevelById(id);
    }

    /**
     * 查询会员等级管理列表
     * 
     * @param customerLevel 会员等级管理
     * @return 会员等级管理
     */
    @Override
    public List<CustomerLevel> selectCustomerLevelList(CustomerLevel customerLevel)
    {
        return customerLevelMapper.selectCustomerLevelList(customerLevel);
    }

    /**
     * 新增会员等级管理
     * 
     * @param customerLevel 会员等级管理
     * @return 结果
     */
    @Override
    public int insertCustomerLevel(CustomerLevel customerLevel)
    {
        customerLevel.setCreateTime(DateUtils.getNowDate());
        return customerLevelMapper.insertCustomerLevel(customerLevel);
    }

    /**
     * 修改会员等级管理
     * 
     * @param customerLevel 会员等级管理
     * @return 结果
     */
    @Override
    public int updateCustomerLevel(CustomerLevel customerLevel)
    {
        customerLevel.setUpdateTime(DateUtils.getNowDate());
        return customerLevelMapper.updateCustomerLevel(customerLevel);
    }

    /**
     * 批量删除会员等级管理
     * 
     * @param ids 需要删除的会员等级管理主键
     * @return 结果
     */
    @Override
    public int deleteCustomerLevelByIds(Long[] ids)
    {
        return customerLevelMapper.deleteCustomerLevelByIds(ids);
    }

    /**
     * 删除会员等级管理信息
     * 
     * @param id 会员等级管理主键
     * @return 结果
     */
    @Override
    public int deleteCustomerLevelById(Long id)
    {
        return customerLevelMapper.deleteCustomerLevelById(id);
    }
}
