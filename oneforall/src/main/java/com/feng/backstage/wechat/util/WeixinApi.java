package com.feng.backstage.wechat.util;

import com.feng.backstage.utils.httputils.HttpUtil;
import com.feng.backstage.wechat.entity.WeixinConfig;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

/**
 * Create by east on 2019/11/6 17:18
 * @author Administrator
 */
public class WeixinApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeixinApi.class);

    private static final String AUTHORIZE_URL = "https://api.weixin.qq.com/device/authorize_device?access_token=ACCESS_TOKEN";

    private static final String GET_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
            + WeixinConfig.AppId + "&secret=" + WeixinConfig.AppSecret;

    private static final String JSAPI_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";

    private static final String CREATE_QR_CODE = "https://api.weixin.qq.com/device/create_qrcode?access_token=ACCESS_TOKEN";

    /**
     * 微信绑定设备，用于微信获取回调
     *
     * @param body
     * @return 绑定结果
     */
    public static String getAuthorizeDeviceUrl(String body) {
        String url = AUTHORIZE_URL.replace("ACCESS_TOKEN", AccessTokenUtil.refreshAndGetToken());
        return HttpUtil.executePost(url, body);
    }

    /**
     * 将数据返回给微信服务器
     * @param str
     * @param response
     */
    public static void out(String str, HttpServletResponse response) {
        Writer out = null;
        response.setContentType("text/xml;charset=UTF-8");
        try {
            out = response.getWriter();
            out.append(str);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 获取访问凭证
     * 正常情况下access_token有效期为7200秒，重复获取将导致上次获取的access_token失效
     * 由于access_token的api调用次数非常有限，需要全局存储与更新access_token
     */
    public static WeixinAccessToken getAccessToken() {
        String resultContent = HttpUtil.executeGet(GET_ACCESS_TOKEN_URL);
        return WeixinAccessToken.fromJson(resultContent);
    }

    /**
     * 获取JSAPITicket
     *
     * @param token
     */
    public static JsTicket getJsApiTicket(String token) {
        String realUrl = JSAPI_URL.replace("ACCESS_TOKEN", token);
        String rs = HttpUtil.executeGet(realUrl);
        return JsTicket.fromJson(rs);
    }


    public static String createQrcode(String accessToken, String body) {
        if (StringUtils.isEmpty(accessToken)) {
            LOGGER.error("getQrcodeTicket 缺少accesstoken");
        }
        String readUrl = CREATE_QR_CODE.replace("ACCESS_TOKEN", accessToken);
        String rs = HttpUtil.executePost(readUrl, body);
        return rs;

    }
}

