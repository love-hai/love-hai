package com.LoveSea.fengCore.commons.utils;

import java.text.NumberFormat;

/**
 * @author xiahaifeng
 * @since 2024/4/11 17:02
 */

public class StringUtils {


    public static int length(CharSequence c) {
        if (isEmpty(c)) {
            return 0;
        }
        return c.length();
    }

    public static boolean isEmpty(CharSequence c) {
        return c == null || c.isEmpty();
    }

    public static boolean isBlank(CharSequence c) {
        int cLen = length(c);
        if (0 == cLen) {
            return true;
        } else {
            for (int i = 0; i < cLen; i++) {
                if (!Character.isWhitespace(c.charAt(i))) {
                    return false;
                }
            }
        }
        return true;
    }

    public static int parseToInteger(CharSequence cs) {
        if (isBlank(cs) || !isNumeric(cs)) {
            throw new IllegalArgumentException("value is not a number");
        }
        return Integer.parseInt(cs.toString());
    }

    public static boolean isNumeric(CharSequence cs) {
        if (isBlank(cs)) {
            return false;
        }
        int len = length(cs);
        for (int i = 0; i < len; i++) {
            char c = cs.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

    // 创建一个百分比格式化对象
    static NumberFormat percentageFormat = NumberFormat.getPercentInstance();
}
