package com.ruoyi.manage.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 商品属性管理对象 tb_product_attribute
 * 
 * @author shiro
 * @date 2025-03-29
 */
public class ProductAttribute extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 属性ID */
    private Long id;

    /** 属性名称 */
    @Excel(name = "属性名称")
    private String name;

    /** 所属分类ID */
    @Excel(name = "所属分类ID")
    private Long categoryId;

    /** 录入方式：0手工录入，1从列表选取，2多选 */
    private Integer inputType;

    /** 值类型：0字符串，1数字，2日期 */
    @Excel(name = "值类型：0字符串，1数字，2日期")
    private Integer valueType;

    /** 单位 */
    private String unit;

    /** 是否支持搜索：0否，1是 */
    @Excel(name = "是否支持搜索：0否，1是")
    private Integer isSearch;

    /** 是否必填：0否，1是 */
    @Excel(name = "是否必填：0否，1是")
    private Integer isRequired;

    /** 是否用于SKU：0否，1是 */
    private Integer isSku;

    /** 排序 */
    @Excel(name = "排序")
    private Long sort;

    /** 状态：0禁用，1启用 */
    @Excel(name = "状态：0禁用，1启用")
    private Integer status;

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

    public void setCategoryId(Long categoryId) 
    {
        this.categoryId = categoryId;
    }

    public Long getCategoryId() 
    {
        return categoryId;
    }

    public void setInputType(Integer inputType) 
    {
        this.inputType = inputType;
    }

    public Integer getInputType() 
    {
        return inputType;
    }

    public void setValueType(Integer valueType) 
    {
        this.valueType = valueType;
    }

    public Integer getValueType() 
    {
        return valueType;
    }

    public void setUnit(String unit) 
    {
        this.unit = unit;
    }

    public String getUnit() 
    {
        return unit;
    }

    public void setIsSearch(Integer isSearch) 
    {
        this.isSearch = isSearch;
    }

    public Integer getIsSearch() 
    {
        return isSearch;
    }

    public void setIsRequired(Integer isRequired) 
    {
        this.isRequired = isRequired;
    }

    public Integer getIsRequired() 
    {
        return isRequired;
    }

    public void setIsSku(Integer isSku) 
    {
        this.isSku = isSku;
    }

    public Integer getIsSku() 
    {
        return isSku;
    }

    public void setSort(Long sort) 
    {
        this.sort = sort;
    }

    public Long getSort() 
    {
        return sort;
    }

    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("categoryId", getCategoryId())
            .append("inputType", getInputType())
            .append("valueType", getValueType())
            .append("unit", getUnit())
            .append("isSearch", getIsSearch())
            .append("isRequired", getIsRequired())
            .append("isSku", getIsSku())
            .append("sort", getSort())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .toString();
    }
}
