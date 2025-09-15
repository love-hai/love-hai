package com.lovehai.leetcode._0001_0100;

/**
 *
 * @author xiahaifeng
 */

public class Solution8 {
    public int myAtoi(String s) {
        char[] chars = s.toCharArray();
        boolean isNegative = false;
        int stage = 0;
        long ans = 0;
        outer:
        for (char c : chars) {
            switch (stage) {
                case 0:
                    if (c == ' ') {
                        continue;
                    } else if (c == '-') {
                        isNegative = true;
                        stage = 1;
                    } else if (c == '+') {
                        stage = 1;
                        continue;
                    } else if (c >= '0' && c <= '9') {
                        stage = 1;
                        ans = c - '0';
                    }else {
                       return 0;
                    }
                    break;
                case 1:
                    if (c >= '0' && c <= '9') {
                        ans = ans * 10 + c - '0';
                        if (!isNegative && ans > Integer.MAX_VALUE) {
                            return Integer.MAX_VALUE;
                        } else if (isNegative && -ans < Integer.MIN_VALUE) {
                            return Integer.MIN_VALUE;
                        }
                    } else {
                        break outer;
                    }
                    break;
            }
        }
        if (isNegative) {
            ans = -ans;
        }
        if (ans > Integer.MAX_VALUE) {
            ans = Integer.MAX_VALUE;
        } else if (ans < Integer.MIN_VALUE) {
            ans = Integer.MIN_VALUE;
        }
        return Math.toIntExact(ans);
    }
}