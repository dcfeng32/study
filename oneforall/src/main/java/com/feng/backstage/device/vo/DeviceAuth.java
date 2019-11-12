package com.feng.backstage.device.vo;

/**
 * 设备授权属性
 * Create on 2019/11/6 16:49
 * @author Administrator
 */
public class DeviceAuth {
    /**
     *  设备device_id
     */
    private String id;

    /**
     * 设备mac(48bit)格式采用16进制串的方式（长度为12字节），不需要0X前缀，如： 1234567890AB
     */
    private String mac;

    /**
     * android classic bluetooth – 1 ios classic bluetooth – 2 ble – 3 wifi -- 4
     * 一个设备可以支持多种连接类型，用符号"|"做分割， 客户端优先选择靠前的连接方式（优先级按|关系的排序依次降低）
     */
    private String connectProtocol;

    /**
     * auth及通信的加密key，第三方需要将key烧制在设备上（128bit）， 格式采用16进制串的方式（长度为32字节），
     * 不需要0X前缀，如：1234567890ABCDEF1234567890ABCDEF
     */
    private String authKey;

    /**
     * 断开策略，目前支持： 1：退出公众号页面时即断开连接 2：退出公众号之后保持连接不断开
     * 3：退出公众号之后一直保持连接（设备主动断开连接后，微信尝试重连）
     */
    private String closeStrategy;

    /**
     * 连接策略，32位整型，按bit位置位，目前仅第1bit和第3bit位有效（bit置0为无效，1为有效；第2bit已被废弃），且bit位可以按或置位
     * （如1|4=5），各bit置位含义说明如下：<br/>
     * 1：（第1bit置位）在公众号对话页面，不停的尝试连接设备<br/>
     * 4：（第3bit置位）处于非公众号页面（如主界面等），微信自动连接。当用户切换微信到前台时，可能尝试去连接设备，连上后一定时间会断开<br/>
     * 8：（第4bit置位），进入微信后即刻开始连接。只要微信进程在运行就不会主动断开
     */
    private String connStrategy;

    /**
     * auth加密方法，目前支持两种取值： 0：不加密 1：AES加密（CBC模式，PKCS7填充方式）
     */
    private String cryptMethod = "0";

    /**
     * 0：不加密的version 1：version 1
     */
    private String authVer;

    /**
     * 表示mac地址在厂商广播manufature data里含有mac地址的偏移，取值如下： -1：在尾部、 -2：表示不包含mac地址
     * 其他：非法偏移
     */
    private String manuMacPos;
    /**
     * 表示mac地址在厂商serial number里含有mac地址的偏移，取值如下： -1：表示在尾部 -2：表示不包含mac地址 其他：非法偏移
     */
    private String serMacPos;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public DeviceAuth(String id, String mac) {
        this.id = id;
        this.mac = mac;
        this.connectProtocol = "4";
        this.closeStrategy = "1";
        this.connStrategy = "1";
        this.cryptMethod = "0";
        this.authVer = "0";
        this.manuMacPos = "-2";
        this.serMacPos = "-2";
    }
}
