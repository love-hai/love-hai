package com.lovehai.leetcode._0201_0300;

/**
 * 209. 长度最小的子数组
 * 给定一个含有 n 个正整数的数组和一个正整数 target 。
 * 找出该数组中满足其总和大于等于 target 的长度最小的 子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，
 * 并返回其长度。如果不存在符合条件的子数组，返回 0 。
 *
 * @author xiahaifeng
 */

public class Solution209 {
    public int minSubArrayLen(int target, int[] nums) {
        int left = 0;
        int right = 0;
        int ans = Integer.MAX_VALUE;
        int sum = 0;
        while (right < nums.length) {
            sum += nums[right++];
            while (sum >= target) {
                ans = Math.min(ans, right - left);
                sum -= nums[left];
                left++;
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }
}