package com.LoveSea.fengCore.study.leetCode;

/**
 * 14. 最长公共前缀
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 * @author xiahaifeng
 */

public class Solution14 {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        String result = strs[0];
        for (int i = 1; i < strs.length; i++) {
            String s = strs[i];
            if (s.isEmpty()) {
                return "";
            }
            if (s.indexOf(result) != 0) {
                char[] chars = result.toCharArray();
                char[] chars1 = s.toCharArray();
                int length = Math.min(chars.length, chars1.length);
                result = result.substring(0, length);
                for (int j = 0; j < length; j++) {
                    if (chars[j] != chars1[j]) {
                        result = result.substring(0, j);
                        break;
                    }
                }
            }
        }
        return result;
    }
}