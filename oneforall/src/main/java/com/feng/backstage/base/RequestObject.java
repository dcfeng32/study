package com.feng.backstage.base;

import javax.validation.Valid;

/**
 * 请求数据
 *
 * @author east
 * @date 19/10/22
 *
 * @param <T>
 */
public class RequestObject<T> {

    private String appKey;

    private String version;

    @Valid
    private T data;

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
