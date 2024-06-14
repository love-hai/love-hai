package com.LoveSea.fengCore.study.leetCode;

/**
 * @author xiahaifeng
 */

/*
给你一个下标从 0 开始的整数数组 nums 和一个正整数 x 。

你 一开始 在数组的位置 0 处，你可以按照下述规则访问数组中的其他位置：

如果你当前在位置 i ，那么你可以移动到满足 i < j 的 任意 位置 j 。
对于你访问的位置 i ，你可以获得分数 nums[i] 。
如果你从位置 i 移动到位置 j 且 nums[i] 和 nums[j] 的 奇偶性 不同，那么你将失去分数 x 。
请你返回你能得到的 最大 得分之和。

注意 ，你一开始的分数为 nums[0] 。
 */

public class Solution2786 {
    public long maxScore(int[] nums, int x) {
        boolean firstOdd = (nums[0] & 1) == 1;
        long maxOdd = firstOdd ? 0 : -x;
        long maxEven = firstOdd ? -x : 0;
        for (int num : nums) {
            if ((num & 1) == 1) {
                maxOdd = Math.max(maxOdd, Math.max(maxEven + num - x, maxOdd + num));
            } else {
                maxEven = Math.max(maxEven, Math.max(maxOdd + num - x, maxEven + num));
            }
        }
        return Math.max(maxEven, maxOdd);
    }

    public static void main(String[] args) {
        // [8,50,65,85,8,73,55,50,29,95,5,68,52,79]
        Solution2786 solution2786 = new Solution2786();
        int[] nums = {8, 50, 65, 85, 8, 73, 55, 50, 29, 95, 5, 68, 52, 79};
        int x = 74;
        System.out.println(solution2786.maxScore(nums, x));
    }
}