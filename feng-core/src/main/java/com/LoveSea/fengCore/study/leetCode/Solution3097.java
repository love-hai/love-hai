package com.LoveSea.fengCore.study.leetCode;

import java.util.Arrays;

/**
 * 3097. 或值至少为 K 的最短子数组 II
 * 给你一个 非负 整数数组 nums 和一个整数 k 。
 * 如果一个数组中所有元素的按位或运算 OR 的值 至少 为 k ，那么我们称这个数组是 特别的 。
 * 请你返回 nums 中 最短特别非空子数组的长度，如果特别子数组不存在，那么返回 -1 。
 *
 * @author xiahaifeng
 */

public class Solution3097 {
    public int minimumSubarrayLength(int[] nums, int k) {
        int maxNum = Arrays.stream(nums).max().getAsInt();
        if (maxNum >= k) {
            return 1;
        } else {
            maxNum = k;
        }
        int dpLength = bitNum(maxNum);
        int[] dp = new int[dpLength];
        boolean[] flags = new boolean[dpLength];
        int bit1Num = 0;
        for (int j = 0; j < dp.length; j++) {
            dp[j] += k >> j & 1;
            bit1Num += dp[j];
            if (dp[j] == 1) {
                flags[j] = true;
            }
        }
        int result = nums.length + 1;
        int left = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < dp.length && j < bitNum(nums[i]); j++) {
                int temp = nums[i] >> j & 1;
                dp[j] -= temp;
                if (dp[j] == 0 && temp == 1) {
                    bit1Num--;
                }
            }
            while (bit1Num == 0 || leadGetters(dp, flags)) {
                result = Math.min(result, i - left + 1);
                for (int j = 0; j < dp.length && j < bitNum(nums[left]); j++) {
                    int temp = nums[left] >> j & 1;
                    if (dp[j] == 0 && temp == 1) {
                        bit1Num++;
                    }
                    dp[j] += temp;
                }
                left++;
            }
        }
        return result == nums.length + 1 ? -1 : result;
    }

    public int bitNum(int num) {
        return 32 - Integer.numberOfLeadingZeros(num);
    }

    public boolean leadGetters(int[] dp, boolean[] flags) {
        for (int i = dp.length - 1; i >= 0; i--) {
            if (flags[i] && dp[i] > 0) {
                return false;
            } else if (!flags[i] && dp[i] < 0) {
                return true;
            }
        }
        throw new RuntimeException("error");
    }


    public static void main(String[] args) {
        Solution3097 solution3097 = new Solution3097();
        System.out.println(solution3097.minimumSubarrayLength(new int[]{1, 64, 32, 97, 15, 2}, 122));
    }

}