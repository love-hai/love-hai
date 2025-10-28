package com.LoveSea.fengCore.commons.utils;

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


    public static boolean isNumber(CharSequence c) {
        if(isBlank(c)){
            return false;
        }
        String str = c.toString();
        return str.matches("[-+]?((\\d+(\\.\\d*)?)|(\\.\\d+))([eE][-+]?\\d+)?");
    }


    /**
     * format : 将msg中的{}转化成args
     *
     * @param msg  信息
     * @param args 需要替换的参数
     */
    public static String format(String msg, Object... args) {
        StringBuilder sb = new StringBuilder();
        char[] chars = msg.toCharArray();
        int count = 0;
        for (int i = 0; i < chars.length; i++) {
            if (count >= args.length) {
                sb.append(chars, i, chars.length - i);
                break;
            }
            if (chars[i] == '\\') {
                if (i < chars.length - 2 && chars[i + 1] == '{' && chars[i + 2] == '}') {
                    sb.append("{}");
                    i += 2;
                    continue;
                } else if (i < chars.length - 1 && chars[i + 1] == '\\') {
                    sb.append(chars[i]);
                    i++;
                    continue;
                }
            } else if (chars[i] == '{') {
                if (i < chars.length - 1 && chars[i + 1] == '}') {
                    sb.append(args[count++]);
                    i++;
                    continue;
                }
            }
            sb.append(chars[i]);
        }
        return sb.toString();
    }
}
