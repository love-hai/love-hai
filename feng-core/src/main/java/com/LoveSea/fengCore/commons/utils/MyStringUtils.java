package com.LoveSea.fengCore.commons.utils;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

/**
 * @author xiahaifeng
 * @since 2024/4/11 17:02
 */

public class MyStringUtils extends StringUtils {

    // 创建一个百分比格式化对象
    static NumberFormat percentageFormat = NumberFormat.getPercentInstance();

    private static Integer getIntegerFromValue(String value) {
        if (isEmpty(value)) {
            return null;
        }
        value = value.trim();
        if (!isNumeric(value)) {
            return null;
        }
        return Integer.parseInt(value.trim());
    }

    private static BigDecimal getBigDecimalFromPercentage(String value) {
        if (isEmpty(value)) {
            return null;
        }
        value = value.trim();
        if (!value.endsWith("%")) {
            return null;
        }
        try {
            Number number = percentageFormat.parse(value);
            // 将解析得到的数字除以100，并设置四位小数
            return BigDecimal.valueOf(number.doubleValue()).setScale(4, RoundingMode.HALF_UP);
        } catch (Exception e) {
            return null;
        }
    }
}
