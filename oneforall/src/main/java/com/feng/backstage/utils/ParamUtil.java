package com.feng.backstage.utils;

import org.springframework.util.StringUtils;

import java.awt.*;

/**
 * 判断参数异常的情况
 * Create on 2019/10/24 9:43
 * @author Administrator
 */
public class ParamUtil {

    public static boolean isNullOrEmptyOrZero(Object object) {
        if (object == null) {
            return true;
        }
        if (object instanceof Long) {
            Long value = (Long) object;
            if (value <= 0) {
                return true;
            }
        } else if (object instanceof Integer) {
            Integer value = (Integer) object;
            if (value <= 0) {
                return true;
            }

        } else if (object instanceof Double) {
            Double value = (Double) object;
            if (value <= 0) {
                return true;
            }

        } else if (object instanceof String) {
            String value = (String) object;
            if (StringUtils.isEmpty(value)) {
                return true;
            }

        } else if (object instanceof List) {
            java.util.List<?> value = (java.util.List<?>) object;
            if (value.size() <= 0) {
                return true;
            }

        }
        return false;
    }

}
