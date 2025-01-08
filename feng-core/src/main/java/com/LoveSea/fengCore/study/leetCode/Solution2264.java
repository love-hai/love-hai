package com.LoveSea.fengCore.study.leetCode;

/**
 * 2264. 字符串中最大的 3 位相同数字
 * 给你一个字符串 num ，表示一个大整数。如果一个整数满足下述所有条件，则认为该整数是一个 优质整数 ：
 * 该整数是 num 的一个长度为 3 的 子字符串 。
 * 该整数由唯一一个数字重复 3 次组成。
 * 以字符串形式返回 最大的优质整数 。如果不存在满足要求的整数，则返回一个空字符串 "" 。
 * 注意：
 * 子字符串 是字符串中的一个连续字符序列。
 * num 或优质整数中可能存在 前导零 。
 * @author xiahaifeng
 */

public class Solution2264 {
    public String largestGoodInteger(String num) {
        int n = num.length();
        if (n < 3) {
            return "";
        }
        char last = ' ';
        byte equalCount = 1;
        char[] chars = num.toCharArray();
        Character max = null;
        for (char c : chars) {
            if (c == last) {
                equalCount++;
                if (equalCount == 3) {
                    if (max == null || c > max) {
                        max = c;
                    }
                }
            } else {
                last = c;
                equalCount = 1;
            }
        }
        if (max == null) {
            return "";
        }
        return String.valueOf(max).repeat(3);
    }
}