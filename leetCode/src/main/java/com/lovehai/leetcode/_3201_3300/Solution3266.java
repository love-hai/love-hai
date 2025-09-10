package com.lovehai.leetcode._3201_3300;

/**
 * K 次乘运算后的最终数组 II
 *
 * @author xiahaifeng
 */

public class Solution3266 {
    /* 给你一个整数数组 nums ，一个整数 k  和一个整数 multiplier 。
    你需要对 nums 执行 k 次操作，每次操作中：找到 nums 中的 最小 值 x ，如果存在多个最小值，
    选择最 前面 的一个。将 x 替换为 x * multiplier 。
    k 次操作以后，你需要将 nums 中每一个数值对 10^9 + 7 取余。
    请你返回执行完 k 次乘运算以及取余运算之后，最终的 nums 数组。
     */
    public int[] getFinalState(int[] nums, int k, int multiplier) {
        int n = nums.length;
        int[] indexs = new int[k];
        for (int i = 0; i < n; i++) {
            indexs[i] = i;
        }
        return null;
    }
}