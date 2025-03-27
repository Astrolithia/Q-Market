package com.ruoyi.manage.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 满减活动管理对象 tb_promotion
 * 
 * @author shiro
 * @date 2025-03-27
 */
public class MarketingPromotion extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 活动ID */
    private Long id;

    /** 活动名称 */
    @Excel(name = "活动名称")
    private String name;

    /** 活动类型：1秒杀，2拼团，3满减，4限时折扣 */
    @Excel(name = "活动类型：1秒杀，2拼团，3满减，4限时折扣")
    private Integer type;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 状态：0未开始，1进行中，2已结束，3已取消 */
    @Excel(name = "状态：0未开始，1进行中，2已结束，3已取消")
    private Integer status;

    /** 活动规则，JSON格式 */
    @Excel(name = "活动规则，JSON格式")
    private String rules;

    /** 活动描述 */
    @Excel(name = "活动描述")
    private String description;

    /** 活动banner */
    @Excel(name = "活动banner")
    private String banner;

    /** 商家ID */
    @Excel(name = "商家ID")
    private Long merchantId;

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

    public void setType(Integer type) 
    {
        this.type = type;
    }

    public Integer getType() 
    {
        return type;
    }

    public void setStartTime(Date startTime) 
    {
        this.startTime = startTime;
    }

    public Date getStartTime() 
    {
        return startTime;
    }

    public void setEndTime(Date endTime) 
    {
        this.endTime = endTime;
    }

    public Date getEndTime() 
    {
        return endTime;
    }

    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }

    public void setRules(String rules) 
    {
        this.rules = rules;
    }

    public String getRules() 
    {
        return rules;
    }

    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }

    public void setBanner(String banner) 
    {
        this.banner = banner;
    }

    public String getBanner() 
    {
        return banner;
    }

    public void setMerchantId(Long merchantId) 
    {
        this.merchantId = merchantId;
    }

    public Long getMerchantId() 
    {
        return merchantId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("type", getType())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("status", getStatus())
            .append("rules", getRules())
            .append("description", getDescription())
            .append("banner", getBanner())
            .append("merchantId", getMerchantId())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .toString();
    }
}
