package com.LoveSea.fengCore.study.leetCode;

/**
 * 跳跃游戏 II
 * 给定一个长度为 n 的 0 索引整数数组 nums。初始位置为 nums[0]。
 * 每个元素 nums[i] 表示从索引 i 向后跳转的最大长度。换句话说，如果你在 nums[i] 处，你可以跳转到任意 nums[i + j] 处:
 * <ul>
 * <li>0 <= j <= nums[i]</li>
 * <li>i + j < n</li>
 * </ul>
 * 返回到达 nums[n - 1] 的最小跳跃次数。生成的测试用例可以到达 nums[n - 1]。
 *
 * @author xiahaifeng
 */

public class Solution45 {
    public int jump(int[] nums) {
        int[] dp = new int[nums.length];
        for (int i = 0; i < nums.length - 1; i++) {
            int maxIndex = Math.min(i + nums[i], nums.length - 1);
            int curStep = dp[i] + 1;
            for (int j = i + 1; j <= maxIndex; j++) {
                if (dp[j] == 0) {
                    dp[j] = curStep;
                } else {
                    if (dp[j] > curStep) {
                        dp[j] = curStep;
                    }
                }
            }
        }
        return dp[nums.length - 1];
    }

    public static void main(String[] args) {
        Solution45 solution45 = new Solution45();
        int[] nums = {3, 2, 1};
        System.out.println(solution45.jump(nums));
    }
}