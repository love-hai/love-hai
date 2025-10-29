package com.lovehai.leetcode._3301_3400;

import java.util.Arrays;

/**
 *
 * @author xiahaifeng
 */

public class Solution3354 {
    public int countValidSelections(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        int ans = 0;
        for (int num : nums) {
            sum -= 2 * num;
            if (sum > 1) {
                continue;
            } else if (sum < -1) {
                break;
            }
            int abs = Math.abs(sum);
            if (abs == 1) {
                if (num == 0) {
                    ans += 1;
                }
            } else {
                if (num == 0) {
                    ans += 2;
                }
            }
        }
        return ans;
    }
}