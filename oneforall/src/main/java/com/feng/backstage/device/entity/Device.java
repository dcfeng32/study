package com.feng.backstage.device.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * 数据库对应的Java实体类
 * Create on 2019/11/6 12:59
 * @author Administrator
 */
public class Device extends Model<Device> {

    /**
     * 主键id
     */
    @TableId(value="id", type= IdType.AUTO)
    private Integer id;

    /**
     * 设备名称
     */
    @TableField("device_name")
    private String deviceName;

    /**
     * mac地址
     */
    private String mac;

    /**
     * 机智云设备id
     */
    @TableField("giz_did")
    private String gizDid;

    /**
     * 机智云设备pass_code
     */
    @TableField("giz_pass_code")
    private String gizPassCode;

    /**
     * 微信设备id
     */
    @TableField("wx_did")
    private String wxDid;

    /**
     * 微信设备二维码
     */
    @TableField("wx_ticket")
    private String wxTicket;

    /**
     * 产品id
     */
    @TableField("product_id")
    private Integer productId;

    /**
     * 创建者id
     */
    @TableField("createor_id")
    private Integer createorId;

    /**
     * 产品pk
     */
    @TableField("product_key")
    private String productKey;

    /**
     * 产品名称
     */
    @TableField("product_name")
    private String productName;

    /**
     * 设备类型
     */
    @TableField("device_type")
    private String deviceType;

    /**
     * 设备在线状态：1为离线，2为在线
     */
    @TableField("online_status")
    private Integer onlineStatus;

    /**
     * 管理员id
     */
    @TableField("admin_id")
    private Integer adminId;

    /**
     * 绑定用户id
     */
    @TableField("terminal_user_id")
    private Integer terminalUserId;

    /**
     * 终端用户名称
     */
    @TableField("terminal_user_name")
    private String terminalUserName;

    /**
     * 激活时间：为mac第一注册通讯的时间
     */
    @TableField("activate_time")
    private Date activateTime;

    /**
     * 最后上线时间
     */
    @TableField("last_online_time")
    private Date lastOnlineTime;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 最后修改时间
     */
    @TableField("modify_time")
    private Date modifyTime;

    /**
     * 删除标识位
     */
    private Integer deleted;

    @TableField("qr_code")
    private String qrCode;

    @TableField("battery_level")
    private Integer batteryLevel;

    public Device() {

    }

    public Integer getCreateorId() {
        return createorId;
    }

    public void setCreateorId(Integer createorId) {
        this.createorId = createorId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getGizDid() {
        return gizDid;
    }

    public void setGizDid(String gizDid) {
        this.gizDid = gizDid;
    }

    public String getGizPassCode() {
        return gizPassCode;
    }

    public void setGizPassCode(String gizPassCode) {
        this.gizPassCode = gizPassCode;
    }

    public String getWxDid() {
        return wxDid;
    }

    public void setWxDid(String wxDid) {
        this.wxDid = wxDid;
    }

    public String getWxTicket() {
        return wxTicket;
    }

    public void setWxTicket(String wxTicket) {
        this.wxTicket = wxTicket;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductKey() {
        return productKey;
    }

    public void setProductKey(String productKey) {
        this.productKey = productKey;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public Integer getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(Integer onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public Integer getTerminalUserId() {
        return terminalUserId;
    }

    public void setTerminalUserId(Integer terminalUserId) {
        this.terminalUserId = terminalUserId;
    }

    public String getTerminalUserName() {
        return terminalUserName;
    }

    public void setTerminalUserName(String terminalUserName) {
        this.terminalUserName = terminalUserName;
    }

    public Date getActivateTime() {
        return activateTime;
    }

    public void setActivateTime(Date activateTime) {
        this.activateTime = activateTime;
    }

    public Date getLastOnlineTime() {
        return lastOnlineTime;
    }

    public void setLastOnlineTime(Date lastOnlineTime) {
        this.lastOnlineTime = lastOnlineTime;
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

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public Integer getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(Integer batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public Device(Integer id, String deviceName, String mac, String gizDid, String gizPassCode, String wxDid, String wxTicket, Integer productId, String productKey, String productName, String deviceType, Integer onlineStatus, Integer adminId, Integer terminalUserId, String terminalUserName, Date activateTime, Date lastOnlineTime, Date createTime, Date modifyTime, Integer deleted, String qrCode, Integer batteryLevel) {
        this.id = id;
        this.deviceName = deviceName;
        this.mac = mac;
        this.gizDid = gizDid;
        this.gizPassCode = gizPassCode;
        this.wxDid = wxDid;
        this.wxTicket = wxTicket;
        this.productId = productId;
        this.productKey = productKey;
        this.productName = productName;
        this.deviceType = deviceType;
        this.onlineStatus = onlineStatus;
        this.adminId = adminId;
        this.terminalUserId = terminalUserId;
        this.terminalUserName = terminalUserName;
        this.activateTime = activateTime;
        this.lastOnlineTime = lastOnlineTime;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
        this.deleted = deleted;
        this.qrCode = qrCode;
        this.batteryLevel = batteryLevel;
    }

    @Override
    public String toString() {
        return "Detector{" +
                "id=" + id +
                ", deviceName='" + deviceName + '\'' +
                ", mac='" + mac + '\'' +
                ", gizDid='" + gizDid + '\'' +
                ", gizPassCode='" + gizPassCode + '\'' +
                ", wxDid='" + wxDid + '\'' +
                ", wxTicket='" + wxTicket + '\'' +
                ", productId=" + productId +
                ", productKey='" + productKey + '\'' +
                ", productName='" + productName + '\'' +
                ", deviceType='" + deviceType + '\'' +
                ", onlineStatus=" + onlineStatus +
                ", adminId=" + adminId +
                ", terminalUserId=" + terminalUserId +
                ", terminalUserName='" + terminalUserName + '\'' +
                ", activateTime=" + activateTime +
                ", lastOnlineTime=" + lastOnlineTime +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                ", deleted=" + deleted +
                ", qrCode='" + qrCode + '\'' +
                ", batteryLevel=" + batteryLevel +
                '}';
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
