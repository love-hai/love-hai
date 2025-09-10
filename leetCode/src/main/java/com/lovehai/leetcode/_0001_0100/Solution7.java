package com.lovehai.leetcode._0001_0100;

/**
 *
 * @author xiahaifeng
 */

public class Solution7 {
    public int reverse(int x) {
        long res = 0;
        while (x != 0) {
            res = res * 10 + x % 10;
            x /= 10;
        }
        return res > Integer.MAX_VALUE || res < Integer.MIN_VALUE ? 0 : (int) res;
    }
}