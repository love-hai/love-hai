package com.lovehai.leetcode._3001_3100;

import java.util.Arrays;

/**
 *
 * @author xiahaifeng
 */

public class Solution3074 {
    public int minimumBoxes(int[] apple, int[] capacity) {
        int sum = 0;
        for (int a : apple) {
            sum += a;
        }
        Arrays.sort(capacity);
        int cap = 0;
        for (int i = capacity.length - 1; i >= 0; i--) {
            cap += capacity[i];
            if (cap >= sum) {
                return capacity.length - i;
            }
        }
        return capacity.length;
    }
}