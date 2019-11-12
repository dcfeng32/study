package com.feng.backstage.base;

import com.feng.backstage.exceptions.ErrorMsgEnum;
import com.feng.backstage.exceptions.SystemExceptionEnum;

/**
 * 回复发出的请求
 *
 * @param <T>
 * @author east
 * @date 19/10/22
 */
public class ResponseObject<T> {

    private String code = "200";

    private String message = "本次请求成功";

    private T data = null;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     * 空构造方法
     */
    public ResponseObject() {
    }

    public ResponseObject(T data) {
        this.data = data;
    }

    public ResponseObject(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseObject(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 回复错误事件
     *
     * @param errorMsgEnum
     * @param <T>
     * @return
     */
    public static <T> ResponseObject<T> response(ErrorMsgEnum errorMsgEnum) {
        return new ResponseObject(errorMsgEnum.getCode(), errorMsgEnum.getMessage());
    }

    public static <T> ResponseObject<T> response(ErrorMsgEnum errorMsgEnum, T data) {
        return new ResponseObject(errorMsgEnum.getCode(), errorMsgEnum.getMessage(), data);
    }

    /**
     * 回复异常代码和事件信息
     *
     * @param systemExceptionEnum
     * @param <T>
     * @return
     */
    public static <T> ResponseObject<T> response(SystemExceptionEnum systemExceptionEnum) {
        ResponseObject responseObject = new ResponseObject(systemExceptionEnum.getCode(), systemExceptionEnum.getMessage());
        return responseObject;
    }

    public static <T> ResponseObject<T> response(SystemExceptionEnum systemExceptionEnum, T data) {
        ResponseObject responseObject = new ResponseObject(systemExceptionEnum.getCode(), systemExceptionEnum.getMessage(), data);
        return responseObject;
    }

    /**
     * 回复成功的请求
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResponseObject<T> ok(T data) {
        ResponseObject<T> result = new ResponseObject<>("200", "本次请求成功", data);
        return result;
    }


}
