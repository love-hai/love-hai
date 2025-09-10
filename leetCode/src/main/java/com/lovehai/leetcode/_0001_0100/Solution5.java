package com.lovehai.leetcode._0001_0100;

/**
 *
 * @author xiahaifeng
 */

public class Solution5 {
    // 动态规划
    public String longestPalindrome(String s) {
        char[] chars = s.toCharArray();
        boolean[][] dp = new boolean[chars.length][chars.length];
        for (int i = 0; i < chars.length; i++) {
            dp[i][i] = true;
        }
        for (int i = 0; i < chars.length - 1; i++) {
            dp[i][i + 1] = chars[i] == chars[i + 1];
        }
        for (int i = chars.length - 3; i >= 0; i--) {
            for (int j = i + 2; j < chars.length; j++) {
                dp[i][j] = chars[i] == chars[j] && dp[i + 1][j - 1];
            }
        }
        String result = "";
        for (int i = 0; i < chars.length; i++) {
            for (int j = i; j < chars.length; j++) {
                if (dp[i][j] && j - i + 1 > result.length()) {
                    result = s.substring(i, j + 1);
                }
            }
        }
        return result;
    }

    // 双指针
    public String longestPalindrome2(String s) {
        if (null == s || s.isEmpty()) {
            return "";
        }
        if (s.length() == 1) {
            return s;
        }
        // 从中心向两边扩展
        char[] chars = s.toCharArray();
        String ans = "";
        int maxLen = 0;
        for (int i = 0; i <= s.length() - 2; i++) {
            int len1 = expand(chars, i, i);
            int len2 = expand(chars, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > maxLen) {
                maxLen = len;
                int start = i - (len - 1) / 2;
                int end = i + len / 2;
                ans = s.substring(start, end + 1);
            }
        }
        return ans;
    }

    private int expand(char[] chars, int left, int right) {
        while (left >= 0 && right < chars.length && chars[left] == chars[right]) {
            left--;
            right++;
        }
        return right - left - 1;
    }
}