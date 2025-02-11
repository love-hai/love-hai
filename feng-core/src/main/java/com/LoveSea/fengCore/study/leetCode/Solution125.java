package com.LoveSea.fengCore.study.leetCode;

/**
 * 125. 验证回文串
 * 如果在将所有大写字符转换为小写字符、并移除所有非字母数字字符之后，短语正着读和反着读都一样。则可以认为该短语是一个 回文串 。
 * 字母和数字都属于字母数字字符。
 * 给你一个字符串 s，如果它是 回文串 ，返回 true ；否则，返回 false 。
 *
 * @author xiahaifeng
 */

public class Solution125 {
    public boolean isPalindrome(String s) {
        char[] chars = s.toCharArray();
        int left = 0;
        int right = chars.length - 1;
        char leftChar = 0;
        char rightChar = 0;
        while (left <= right) {
            while (left <= right) {
                if ((chars[left] >= 'a' && chars[left] <= 'z') || (chars[left] >= '0' && chars[left] <= '9')) {
                    leftChar = chars[left];
                    break;
                }
                if (chars[left] >= 'A' && chars[left] <= 'Z') {
                    leftChar = (char) (chars[left] + 32);
                    break;
                }
                left++;
            }
            while (left <= right) {
                if ((chars[right] >= 'a' && chars[right] <= 'z') || (chars[right] >= '0' && chars[right] <= '9')) {
                    rightChar = chars[right];
                    break;
                }
                if (chars[right] >= 'A' && chars[right] <= 'Z') {
                    rightChar = (char) (chars[right] + 32);
                    break;
                }
                right--;
            }
            if (leftChar != rightChar) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}