package com.LoveSea.fengCore.study.leetCode;

import java.util.Arrays;

/**
 * <H1>2270. 分割数组的方案数</H1>
 * 给你一个下标从 0 开始长度为 n 的整数数组 nums 。
 * 如果以下描述为真，那么 nums 在下标 i 处有一个 合法的分割 ：
 * <ul>
 * <li>前 i + 1 个元素的和 大于等于 剩下的 n - i - 1 个元素的和。</li>
 * <li>下标 i 的右边 至少有一个 元素，也就是说下标 i 满足 0 <= i < n - 1 。</li>
 * </ul>
 * 请你返回 nums 中的 合法分割 方案数。
 */

public class Solution2270 {
    public int waysToSplitArray(int[] nums) {
        long sum = 0;
        for (int num : nums) {
            sum += num;
        }
        long divide = (sum + (sum > 0 ? 1 : 0)) / 2;
        int result = 0;
        long prefixSum = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            prefixSum += nums[i];
            if (prefixSum >= divide) {
                result += 1;
            }
        }
        return result;
    }
}