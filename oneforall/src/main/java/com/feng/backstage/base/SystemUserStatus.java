package com.feng.backstage.base;

/**
 * 注意枚举类的关键修饰字
 * Create on 2019/10/24 9:38
 *
 * @author Administrator
 */
public enum SystemUserStatus {
    /**
     * 禁用
     */
    DISABLE(0, "禁用"),

    /**
     * 启用
     */
    ENABLE(1, "启动");

    private Integer code;
    private String describe;

    SystemUserStatus(Integer code, String describe) {
        this.code = code;
        this.describe = describe;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescribe() {
        return describe;
    }
}
