package com.ruoyi.manage.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 商家信息管理对象 tb_merchant
 * 
 * @author shiro
 * @date 2025-03-27
 */
public class Merchant extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 商家ID */
    private Long id;

    /** 商家名称 */
    @Excel(name = "商家名称")
    private String name;

    /** 商家编码 */
    @Excel(name = "商家编码")
    private String code;

    /** 联系人 */
    @Excel(name = "联系人")
    private String contactPerson;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String contactPhone;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String email;

    /** 地址 */
    @Excel(name = "地址")
    private String address;

    /** 营业执照 */
    @Excel(name = "营业执照")
    private String businessLicense;

    /** 经营类目 */
    @Excel(name = "经营类目")
    private String category;

    /** 分成比例(%) */
    @Excel(name = "分成比例(%)")
    private Long profitRatio;

    /** 登录账号 */
    private String account;

    /** 登录密码 */
    private String password;

    /** 状态：0禁用，1启用 */
    @Excel(name = "状态：0禁用，1启用")
    private Integer status;

    /** 商家等级 */
    @Excel(name = "商家等级")
    private Long level;

    /** 评分 */
    @Excel(name = "评分")
    private Long score;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }

    public void setCode(String code) 
    {
        this.code = code;
    }

    public String getCode() 
    {
        return code;
    }

    public void setContactPerson(String contactPerson) 
    {
        this.contactPerson = contactPerson;
    }

    public String getContactPerson() 
    {
        return contactPerson;
    }

    public void setContactPhone(String contactPhone) 
    {
        this.contactPhone = contactPhone;
    }

    public String getContactPhone() 
    {
        return contactPhone;
    }

    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }

    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }

    public void setBusinessLicense(String businessLicense) 
    {
        this.businessLicense = businessLicense;
    }

    public String getBusinessLicense() 
    {
        return businessLicense;
    }

    public void setCategory(String category) 
    {
        this.category = category;
    }

    public String getCategory() 
    {
        return category;
    }

    public void setProfitRatio(Long profitRatio) 
    {
        this.profitRatio = profitRatio;
    }

    public Long getProfitRatio() 
    {
        return profitRatio;
    }

    public void setAccount(String account) 
    {
        this.account = account;
    }

    public String getAccount() 
    {
        return account;
    }

    public void setPassword(String password) 
    {
        this.password = password;
    }

    public String getPassword() 
    {
        return password;
    }

    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }

    public void setLevel(Long level) 
    {
        this.level = level;
    }

    public Long getLevel() 
    {
        return level;
    }

    public void setScore(Long score) 
    {
        this.score = score;
    }

    public Long getScore() 
    {
        return score;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("code", getCode())
            .append("contactPerson", getContactPerson())
            .append("contactPhone", getContactPhone())
            .append("email", getEmail())
            .append("address", getAddress())
            .append("businessLicense", getBusinessLicense())
            .append("category", getCategory())
            .append("profitRatio", getProfitRatio())
            .append("account", getAccount())
            .append("password", getPassword())
            .append("status", getStatus())
            .append("level", getLevel())
            .append("score", getScore())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .toString();
    }
}
