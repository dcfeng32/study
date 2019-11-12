package com.feng.backstage.wechat.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.feng.backstage.wechat.dao.WeixinConfigDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * wx_config表对应的实体类
 * Create by east on 2019/11/7 10:43
 * @author Administrator
 */

@Component
public class WeixinConfig implements InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(WeixinConfig.class);

    @Autowired
    private WeixinConfigDao weixinConfigDao;

    public static String AppId;
    public static String AppSecret;

    public static String token;
    public static String SysAppId;

    public static String hostUrl;
    public static String productId;

    public WeixinConfig() {
    }

    public static String getAppId() {
        return AppId;
    }

    public static void setAppId(String appId) {
        AppId = appId;
    }

    public static String getAppSecret() {
        return AppSecret;
    }

    public static void setAppSecret(String appSecret) {
        AppSecret = appSecret;
    }

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        WeixinConfig.token = token;
    }

    public static String getSysAppId() {
        return SysAppId;
    }

    public static void setSysAppId(String sysAppId) {
        SysAppId = sysAppId;
    }

    public static String getHostUrl() {
        return hostUrl;
    }

    public static void setHostUrl(String hostUrl) {
        WeixinConfig.hostUrl = hostUrl;
    }

    public static String getProductId() {
        return productId;
    }

    public static void setProductId(String productId) {
        WeixinConfig.productId = productId;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

//        WxConfig wxConfig = weixinConfigDao.findOneByLimitOne();
//        if (wxConfig != null) {
//            logger.info("加载数据库中微信公众号信息配置...");
//            APPID = wxConfig.getAppId();
//            APPSECRET = wxConfig.getAppSecret();
//            TOKEN = wxConfig.getToken();
//            SYSAPPID = wxConfig.getSysAppId();
//            HOST_URL = wxConfig.getHostUrl();
//            PRODUCTID = wxConfig.getProductId().toString();
//        }

    }
}
