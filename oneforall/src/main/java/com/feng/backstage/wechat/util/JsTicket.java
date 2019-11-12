package com.feng.backstage.wechat.util;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * Create on 2019/11/9 10:50
 * @author Administrator
 */
public class JsTicket implements Serializable {

    private String errcode;

    private String errmsg;

    private String ticket;

    private long expiresIn;

    private long createTime;

    public static JsTicket fromJson(String json) {
        JsTicket ticket = JSONObject.parseObject(json, JsTicket.class);
        ticket.setCreateTime(System.currentTimeMillis());
        return ticket;
    }

    public JsTicket(String errcode, String errmsg, String ticket, long expiresIn, long createTime) {
        this.errcode = errcode;
        this.errmsg = errmsg;
        this.ticket = ticket;
        this.expiresIn = expiresIn;
        this.createTime = createTime;
    }

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
}
