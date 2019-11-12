package com.feng.backstage.exceptions;

/**
 * @author Administrator
 */

public enum SystemExceptionEnum {
    /**
     * 系统内部异常
     */
    INTERNAL_ERROR("500", "系统内部异常"),
    /**
     * 用户未登录
     */
    NO_LOGIN("501", "未登录"),
    /**
     * 请求参数不合法
     */
    ILLEGAL_PARAM("505", "请求参数不合法"),
    /**
     * 数据库异常
     */
    SQL_ERROR("508", "数据库操作异常,字段不合格或者主键冲突等!"),
    /**
     * 用户名或者密码错误
     */
    ILLEGAL_USER("509", "用户名或者密码有误!"),
    /**
     * 原密码错误
     */
    PASSWORD_ERROR("515", "原密码错误"),
    /**
     * 账户停用
     */
    USER_DISABLED("518", "您的账号已暂停使用，请联系客服"),
    /**
     * 账号不存在
     */
    USER_NOT_EXIST("519", "账号不存在");

    private String code;
    private String message;

    /**
     * 构造方法后才能定义枚举量
     *
     * @param code
     * @param message
     */
    SystemExceptionEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 不需要set方法
     *
     * @return
     */
    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
