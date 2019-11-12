package com.feng.backstage.product.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * 产品表对应的Java实体类
 * Create on 2019/11/6 14:17
 * @author Administrator
 */
public class Product extends Model<Product> {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 产品名称
     */
    @TableField("product_name")
    private String productName;

    /**
     * 产品key
     */
    @TableField("product_key")
    private String productKey;

    /**
     * 产品secret
     */
    @TableField("product_secret")
    private String productSecret;

    /**
     * auth_id(每个产品都有唯一的)
     */
    @TableField("auth_id")
    private String authId;

    /**
     * auth_secret(每个产品都有唯一的)
     */
    @TableField("auth_secret")
    private String authSecret;

    /**
     * snotis标识
     */
    private String subkey;

    /**
     * 产品logo
     */
    @TableField("product_logo_url")
    private String productLogoUrl;

    @TableField("modify_id")
    private Integer modifyId;

    /**
     * 创建者Id
     */
    @TableField("sys_user_id")
    private Integer sysUserId;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField("modify_time")
    private Date modifyTime;
    private Integer deleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductKey() {
        return productKey;
    }

    public void setProductKey(String productKey) {
        this.productKey = productKey;
    }

    public String getProductSecret() {
        return productSecret;
    }

    public void setProductSecret(String productSecret) {
        this.productSecret = productSecret;
    }

    public String getAuthId() {
        return authId;
    }

    public void setAuthId(String authId) {
        this.authId = authId;
    }

    public String getAuthSecret() {
        return authSecret;
    }

    public void setAuthSecret(String authSecret) {
        this.authSecret = authSecret;
    }

    public String getSubkey() {
        return subkey;
    }

    public void setSubkey(String subkey) {
        this.subkey = subkey;
    }

    public String getProductLogoUrl() {
        return productLogoUrl;
    }

    public void setProductLogoUrl(String productLogoUrl) {
        this.productLogoUrl = productLogoUrl;
    }

    public Integer getModifyId() {
        return modifyId;
    }

    public void setModifyId(Integer modifyId) {
        this.modifyId = modifyId;
    }

    public Integer getSysUserId() {
        return sysUserId;
    }

    public void setSysUserId(Integer sysUserId) {
        this.sysUserId = sysUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", productKey='" + productKey + '\'' +
                ", productSecret='" + productSecret + '\'' +
                ", authId='" + authId + '\'' +
                ", authSecret='" + authSecret + '\'' +
                ", subkey='" + subkey + '\'' +
                ", productLogoUrl='" + productLogoUrl + '\'' +
                ", modifyId=" + modifyId +
                ", sysUserId=" + sysUserId +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                ", deleted=" + deleted +
                '}';
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
