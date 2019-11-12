package com.feng.backstage.wechat.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Create on 2019/11/6 17:21
 * @author Administrator
 */
public class AccessTokenUtil {

    private static Logger logger = LoggerFactory.getLogger("AccessTokenUtil");

    /**
     * 刷新Token并返回凭证
     * @return accessToken
     */
    public static synchronized String refreshAndGetToken() {
        WeixinAccessToken tk = queryAccessToken();
        // 10秒之内只刷新一次，防止并发引起的多次刷新
        if (tk == null
                || (System.currentTimeMillis() - tk.getCreateTime() > 10000)) {
            refreshToken();
        }
        return getTokenStr();
    }

    /**
     * 获取凭证
     *
     * @return
     */
    private static String getTokenStr() {
        return queryAccessToken().getAccessToken();
    }

    /**
     * 刷新凭证更新全局凭证，token JSTicket
     */
    private static void refreshToken() {
        try {
            logger.info("refresh token... jsTocket...");
            WeixinAccessToken accessToken = WeixinApi.getAccessToken();

            JsTicket jsTicket = WeixinApi.getJsApiTicket(accessToken.getAccessToken());
            saveAccessToken(accessToken);
            saveJsTicket(jsTicket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存Token
     *
     * @param accessToken
     */
    private static void saveAccessToken(WeixinAccessToken accessToken) {
        token = accessToken;
    }

    private static WeixinAccessToken queryAccessToken() {
        return token;
    }


    /**
     * 凭证的存储需要全局唯一
     * <p>
     * 单机部署情况下可以存在内存中 <br>
     * 分布式情况需要存在集中缓存或DB中
     */
    private static WeixinAccessToken token;

    private static JsTicket jsTicket;


    /**
     * 获取存储的JSAPITicket
     */
    public static JsTicket queryJsticket() {
        return jsTicket;
    }

    /**
     * 保存JSTicket
     */
    private static void saveJsTicket(JsTicket ticket) {
        jsTicket = ticket;
    }


}
