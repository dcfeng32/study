package com.feng.backstage.utils.httputils;

/**
 * Create on 2019/11/9 9:39
 * @author Administrator
 */
public class HttpRespObject {

    private boolean success;

    private int statusCode;

    private Object content;

    public HttpRespObject(boolean success, int statusCode, Object content) {
        this.success = success;
        this.statusCode = statusCode;
        this.content = content;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
