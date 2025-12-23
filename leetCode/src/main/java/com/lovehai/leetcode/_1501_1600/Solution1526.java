package com.lovehai.leetcode._1501_1600;

/**
 *
 * @author xiahaifeng
 */

public class Solution1526 {
    public int minNumberOperations(int[] target) {
        int n = target.length;
        int ans = target[0];
        for (int i = 1; i < n; ++i) {
            ans += Math.max(target[i] - target[i - 1], 0);
        }
        return ans;
    }
}