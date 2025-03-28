package com.ruoyi.manage.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 商家审核对象 tb_merchant
 * 
 * @author shiro
 * @date 2025-03-28
 */
public class MerchantAudit extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 商家ID */
    private Long id;

    /** 商家名称 */
    @Excel(name = "商家名称")
    private String name;

    /** 商家编码 */
    private String code;

    /** 联系人 */
    @Excel(name = "联系人")
    private String contactPerson;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String contactPhone;

    /** 邮箱 */
    private String email;

    /** 地址 */
    private String address;

    /** 营业执照 */
    private String businessLicense;

    /** 营业执照号 */
    private String licenseNumber;

    /** 法人代表 */
    private String legalRepresentative;

    /** 经营类目 */
    @Excel(name = "经营类目")
    private String category;

    /** 分成比例(%) */
    private Long profitRatio;

    /** 登录账号 */
    private String account;

    /** 登录密码 */
    private String password;

    /** 状态：0禁用，1启用 */
    private Integer status;

    /** 商家等级 */
    private Long level;

    /** 评分 */
    private Long score;

    /** 申请时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "申请时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date applyTime;

    /** 审核时间 */
    private Date auditTime;

    /** 审核人 */
    private String auditor;

    /** 审核状态：0待审核，1审核通过，2审核拒绝 */
    private Integer auditStatus;

    /** 拒绝原因 */
    private String rejectReason;

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

    public void setLicenseNumber(String licenseNumber) 
    {
        this.licenseNumber = licenseNumber;
    }

    public String getLicenseNumber() 
    {
        return licenseNumber;
    }

    public void setLegalRepresentative(String legalRepresentative) 
    {
        this.legalRepresentative = legalRepresentative;
    }

    public String getLegalRepresentative() 
    {
        return legalRepresentative;
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

    public void setApplyTime(Date applyTime) 
    {
        this.applyTime = applyTime;
    }

    public Date getApplyTime() 
    {
        return applyTime;
    }

    public void setAuditTime(Date auditTime) 
    {
        this.auditTime = auditTime;
    }

    public Date getAuditTime() 
    {
        return auditTime;
    }

    public void setAuditor(String auditor) 
    {
        this.auditor = auditor;
    }

    public String getAuditor() 
    {
        return auditor;
    }

    public void setAuditStatus(Integer auditStatus) 
    {
        this.auditStatus = auditStatus;
    }

    public Integer getAuditStatus() 
    {
        return auditStatus;
    }

    public void setRejectReason(String rejectReason) 
    {
        this.rejectReason = rejectReason;
    }

    public String getRejectReason() 
    {
        return rejectReason;
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
            .append("licenseNumber", getLicenseNumber())
            .append("legalRepresentative", getLegalRepresentative())
            .append("category", getCategory())
            .append("profitRatio", getProfitRatio())
            .append("account", getAccount())
            .append("password", getPassword())
            .append("status", getStatus())
            .append("level", getLevel())
            .append("score", getScore())
            .append("createTime", getCreateTime())
            .append("applyTime", getApplyTime())
            .append("auditTime", getAuditTime())
            .append("auditor", getAuditor())
            .append("auditStatus", getAuditStatus())
            .append("rejectReason", getRejectReason())
            .append("updateTime", getUpdateTime())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .toString();
    }
}
