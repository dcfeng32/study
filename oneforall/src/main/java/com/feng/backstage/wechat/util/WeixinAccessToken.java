package com.feng.backstage.wechat.util;

import com.alibaba.fastjson.JSONObject;

/**
 * Create on 2019/11/6 17:22
 * @author Administrator
 */
public class WeixinAccessToken {

    /**
     * 令牌
     */
    private String accessToken;

    /**
     *  有效时长 单位秒
     */
    private long expiresIn;

    /**
     * 创建时间 单位毫秒
     */
    private long createTime;

    public String getAccessToken() {
        return accessToken;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public static WeixinAccessToken fromJson(String json) {
        WeixinAccessToken token = JSONObject.parseObject(json, WeixinAccessToken.class);
        token.setCreateTime(System.currentTimeMillis());
        return token;
    }


}
