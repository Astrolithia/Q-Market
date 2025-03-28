package com.ruoyi.manage.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 商家评级对象 tb_merchant
 * 
 * @author shiro
 * @date 2025-03-28
 */
public class MerchantRating extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 商家ID */
    @Excel(name = "商家ID")
    private Long id;

    /** 商家名称 */
    @Excel(name = "商家名称")
    private String name;

    /** 商家编码 */
    @Excel(name = "商家编码")
    private String code;

    /** 联系人 */
    private String contactPerson;

    /** 联系电话 */
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
    @Excel(name = "商家等级")
    private Long level;

    /** 评分 */
    @Excel(name = "评分")
    private Long score;

    /** 申请时间 */
    private Date applyTime;

    /** 审核时间 */
    private Date auditTime;

    /** 审核人 */
    private String auditor;

    /** 审核状态：0待审核，1审核通过，2审核拒绝 */
    private Integer auditStatus;

    /** 拒绝原因 */
    private String rejectReason;

    /** 交付率(%) */
    private BigDecimal deliveryRate;

    /** 准时率(%) */
    private BigDecimal onTimeRate;

    /** 订单完成率(%) */
    @Excel(name = "订单完成率(%)")
    private BigDecimal completionRate;

    /** 服务评分(1-5分) */
    private BigDecimal serviceScore;

    /** 物流评分(1-5分) */
    private BigDecimal logisticsScore;

    /** 商品评分(1-5分) */
    private BigDecimal productScore;

    /** 评价总数 */
    private Long ratingCount;

    /** 等级更新时间 */
    private Date levelUpdateTime;

    /** 平均发货时间(小时) */
    @Excel(name = "平均发货时间(小时)")
    private Long avgShippingTime;

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

    public void setDeliveryRate(BigDecimal deliveryRate) 
    {
        this.deliveryRate = deliveryRate;
    }

    public BigDecimal getDeliveryRate() 
    {
        return deliveryRate;
    }

    public void setOnTimeRate(BigDecimal onTimeRate) 
    {
        this.onTimeRate = onTimeRate;
    }

    public BigDecimal getOnTimeRate() 
    {
        return onTimeRate;
    }

    public void setCompletionRate(BigDecimal completionRate) 
    {
        this.completionRate = completionRate;
    }

    public BigDecimal getCompletionRate() 
    {
        return completionRate;
    }

    public void setServiceScore(BigDecimal serviceScore) 
    {
        this.serviceScore = serviceScore;
    }

    public BigDecimal getServiceScore() 
    {
        return serviceScore;
    }

    public void setLogisticsScore(BigDecimal logisticsScore) 
    {
        this.logisticsScore = logisticsScore;
    }

    public BigDecimal getLogisticsScore() 
    {
        return logisticsScore;
    }

    public void setProductScore(BigDecimal productScore) 
    {
        this.productScore = productScore;
    }

    public BigDecimal getProductScore() 
    {
        return productScore;
    }

    public void setRatingCount(Long ratingCount) 
    {
        this.ratingCount = ratingCount;
    }

    public Long getRatingCount() 
    {
        return ratingCount;
    }

    public void setLevelUpdateTime(Date levelUpdateTime) 
    {
        this.levelUpdateTime = levelUpdateTime;
    }

    public Date getLevelUpdateTime() 
    {
        return levelUpdateTime;
    }

    public void setAvgShippingTime(Long avgShippingTime) 
    {
        this.avgShippingTime = avgShippingTime;
    }

    public Long getAvgShippingTime() 
    {
        return avgShippingTime;
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
            .append("deliveryRate", getDeliveryRate())
            .append("onTimeRate", getOnTimeRate())
            .append("completionRate", getCompletionRate())
            .append("serviceScore", getServiceScore())
            .append("logisticsScore", getLogisticsScore())
            .append("productScore", getProductScore())
            .append("ratingCount", getRatingCount())
            .append("levelUpdateTime", getLevelUpdateTime())
            .append("avgShippingTime", getAvgShippingTime())
            .toString();
    }
}
