package com.LoveSea.fengCore.study.leetCode;

import java.util.Arrays;

/**
 * @author xiahaifeng
 */

/*
给你一个下标从 0 开始的整数数组 nums 和一个 非负 整数 k 。
在一步操作中，你可以执行下述指令：
在范围 [0, nums.length - 1] 中选择一个 此前没有选过 的下标 i 。
将 nums[i] 替换为范围 [nums[i] - k, nums[i] + k] 内的任一整数。
数组的 美丽值 定义为数组中由相等元素组成的最长子序列的长度。
对数组 nums 执行上述操作任意次后，返回数组可能取得的 最大 美丽值。
注意：你 只 能对每个下标执行 一次 此操作。
数组的 子序列 定义是：经由原数组删除一些元素（也可能不删除）得到的一个新数组，且在此过程中剩余元素的顺序不发生改变。
 */

public class Solution2779 {

    /**
     * maximumBeauty
     * @param nums : int[]
     * @param k : int
     * @author xiahaifeng
     */
    public int maximumBeauty(int[] nums, int k) {
        int res = 0;
        Arrays.sort(nums);
        int n = nums.length;
        int l = 0, r = 0;
        while (r < n) {
            while (nums[r] - nums[l] <= 2*k) {
                r++;
                if (r == n) {
                    return Math.max(res, r - l);
                }
            }
            res = Math.max(res, r - l);
            l++;
        }
        return res;
    }

    public static void main(String[] args) {
        // [75,15,9]
        Solution2779 solution2779 = new Solution2779();
        int[] nums = {75, 15, 9};
        int k = 28;
        System.out.println(solution2779.maximumBeauty(nums, k));
    }
}