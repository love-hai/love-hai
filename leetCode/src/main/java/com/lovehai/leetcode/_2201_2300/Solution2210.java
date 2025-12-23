package com.lovehai.leetcode._2201_2300;

/**
 *
 * @author xiahaifeng
 */

public class Solution2210 {
    public int countHillValley(int[] nums) {
        int ans = 0;
        int dir = 0;
        int curDir;
        for (int i = 1; i < nums.length; i++) {
            curDir = Integer.compare(nums[i], nums[i - 1]);
            if (curDir == 0) {
                continue;
            }
            if (0 != dir && curDir != dir) {
                ans++;
            }
            dir = curDir;
        }
        return ans;
    }
}