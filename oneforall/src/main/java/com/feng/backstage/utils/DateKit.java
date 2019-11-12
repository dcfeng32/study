package com.feng.backstage.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间转格式
 * Create on 2019/11/6 14:37
 * @author Administrator
 */
public class DateKit {

    /**
     * 日期格式化为字符串 yyyy-MM-dd HH:mm:ss , 如果data为null,返回当前时间
     * @param date
     * @return
     */
    public static String getTimestampString(Date date) {
        String str = "";
        if (null == date) {
            str = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date());
        } else {
            str = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(date);
        }
        return str;
    }
}
