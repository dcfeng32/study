package com.feng.backstage.utils.httputils;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * web工具类，获取请求数据header信息
 * Create on 2019/11/5 8:48
 * @author Administrator
 */
public class WebUtil {

    private WebUtil() {
    }

    public static String getHeader(String key) {
        return getHttpServletRequest().getHeader(key);
    }

    /**
     * 看不懂 ？？？
     * @return
     */
    private static HttpServletRequest getHttpServletRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes attributes = (ServletRequestAttributes) requestAttributes;
        HttpServletRequest request = attributes.getRequest();
        return request;
    }


}
