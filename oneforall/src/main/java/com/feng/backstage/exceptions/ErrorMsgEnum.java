package com.feng.backstage.exceptions;

/**
 * Createon 2019/11/6 13:36
 * @author Administrator
 */
public enum ErrorMsgEnum {
    /**
     * 用户不存在
     */
    USER_DONT_EXISTS("10000", "用户不存在"),

    /**
     * 产品不存在
     */
    PRODUCT_DONT_EXISTS("40001", "产品不存在");

    private String code;
    private String message;

    ErrorMsgEnum(String code, String msg) {
        this.code = code;
        this.message = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
