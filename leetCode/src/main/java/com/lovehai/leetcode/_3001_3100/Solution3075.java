package com.lovehai.leetcode._3001_3100;

import java.util.Arrays;

/**
 *
 * @author xiahaifeng
 */

public class Solution3075 {
    public long maximumHappinessSum(int[] happiness, int k) {
        Arrays.sort(happiness);
        long ans = 0;
        for (int index = happiness.length - 1, i = 0; i < k; i++, index--) {
            int h = happiness[index];
            h-= i;
            if (h > 0) {
                ans += h;
            }else {
                break;
            }
        }
        return ans;
    }
}