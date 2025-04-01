package com.LoveSea.fengCore.study.leetCode;

/**
 * 6. Z 字形变换
 * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
 * 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
 * 请你实现这个将字符串进行指定行数变换的函数：
 * string convert(string s, int numRows);
 *
 * @author xiahaifeng
 */

public class Solution6 {
    public String convert(String s, int numRows) {
        int group = numRows * 2 - 2;
        if (group == 0) {
            return s;
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        int j = i;
        while (j < s.length()) {
            sb.append(s.charAt(j));
            j += group;
        }
        int j2;
        for (i = 1; i < numRows - 1; i++) {
            j = i;
            j2 = group - i;
            while (j2 < s.length()) {
                sb.append(s.charAt(j));
                sb.append(s.charAt(j2));
                j += group;
                j2 += group;
            }
            if (j < s.length()) {
                sb.append(s.charAt(j));
            }
        }
        i = numRows - 1;
        j = i;
        while (j < s.length()) {
            sb.append(s.charAt(j));
            j += group;
        }
        return sb.toString();
    }
}