package com.lovehai.leetcode._3001_3100;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * 3095. 或值至少 K 的最短子数组 I
 * 给你一个非负整数数组 nums 和一个整数 k 。
 * 如果一个数组中所有元素的按位或运算 OR 的值 至少 为 k ，那么我们称这个数组是 特别的 。
 * 请你返回 nums 中 最短特别非空子数组的长度，如果特别子数组不存在，那么返回 -1 。
 *
 * @author xiahaifeng
 */

public class Solution3095Test {
    @Test
    public void testMinimumSubarrayLength() {
        Solution3095 solution = new Solution3095();
        int[] nums = {1, 2, 3};
        int ans = solution.minimumSubarrayLength(nums, 2);
        Assertions.assertEquals(1, ans);
        nums = new int[]{2, 1, 8};
        ans = solution.minimumSubarrayLength(nums, 10);
        Assertions.assertEquals(3, ans);
        nums = new int[]{1, 2};
        ans = solution.minimumSubarrayLength(nums, 0);
        Assertions.assertEquals(1, ans);
        nums = new int[]{16, 1, 2, 20, 32};
        ans = solution.minimumSubarrayLength(nums, 45);
        Assertions.assertEquals(2, ans);
    }
}