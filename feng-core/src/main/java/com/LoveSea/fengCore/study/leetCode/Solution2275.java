package com.LoveSea.fengCore.study.leetCode;

/**
 * <H1>2275. 按位与结果大于零的最长组合</H1>
 * 对数组 nums 执行 按位与 相当于对数组 nums 中的所有整数执行 按位与 。
 * <ul>
 * <li>例如，对 nums = [1, 5, 3] 来说，按位与等于 1 & 5 & 3 = 1 。</li>
 * <li>同样，对 nums = [7] 而言，按位与等于 7 。</li>
 * </ul>
 * 给你一个正整数数组 candidates 。计算 candidates 中的数字每种组合下 按位与 的结果。
 * 返回按位与结果大于 0 的 最长 组合的长度。
 *
 * @author xiahaifeng
 */

public class Solution2275 {
    public int largestCombination(int[] candidates) {
        int[] dp = new int[24];
        for (int candidate : candidates) {
            for (int i = 0; i < 24; i++) {
                dp[i] += (candidate >> i) & 1;
            }
        }
        int result = 0;
        for (int i = 0; i < 24; i++) {
            if (dp[i] > result) {
                result = dp[i];
            }
        }
        return result;
    }

    public int largestCombinationPlus(int[] candidates) {
        int max = 0;
        for (int num : candidates) {
            max = Math.max(num, max);
        }
        int m = 32 - Integer.numberOfLeadingZeros(max);
        int result = 0;
        for (int i = 0; i < m; i++) {
            int temp = 0;
            for (int candidate : candidates) {
                temp += (candidate >> i) & 1;
            }
            result = Math.max(result, temp);
        }
        return result;
    }


}