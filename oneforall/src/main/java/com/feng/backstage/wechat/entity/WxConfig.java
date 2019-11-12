package com.feng.backstage.wechat.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * 表wx_config对应的Java类
 * Create on 2019/11/9 13:57
 * @author Administrator
 */
@TableName("wx_config")
public class WxConfig extends Model<WxConfig> {

    private Integer id;

    @TableField("app_id")
    private String appId;

    @TableField("app_secret")
    private String appSecret;
    /**
     * 设备产品id：用于设备生成二维码
     */
    @TableField("product_id")
    private Integer productId;

    @TableField("sys_app_id")
    private String sysAppId;

    private String token;
    /**
     * 回调的url
     */
    @TableField("host_url")
    private String hostUrl;
    /**
     * 环境类型：dev开发  qa测试  prod生产
     */
    @TableField("path_type")
    private String pathType;

    private Integer deleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getSysAppId() {
        return sysAppId;
    }

    public void setSysAppId(String sysAppId) {
        this.sysAppId = sysAppId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getHostUrl() {
        return hostUrl;
    }

    public void setHostUrl(String hostUrl) {
        this.hostUrl = hostUrl;
    }

    public String getPathType() {
        return pathType;
    }

    public void setPathType(String pathType) {
        this.pathType = pathType;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
