package com.feng.backstage.exceptions;

/**
 * Create on 2019/10/22 17:16
 * @author Administrator
 */
public class SystemException extends RuntimeException {

    /**
     * 异常代码
     */
    private String code;
    /**
     * 异常信息
     */
    private String message;

    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public SystemException(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
