package com.lovehai.leetcode._2501_2600;

import java.util.Arrays;

/**
 * 2576. 求出最多标记下标
 * 给你一个下标从 0 开始的整数数组 nums 。
 * 一开始，所有下标都没有被标记。你可以执行以下操作任意次：
 * 选择两个 互不相同且未标记 的下标 i 和 j ，满足 2 * nums[i] <= nums[j] ，标记下标 i 和 j 。
 * 请你执行上述操作任意次，返回 nums 中最多可以标记的下标数目。
 *
 * @author xiahaifeng
 */

public class Solution2576 {
    public int maxNumOfMarkedIndices(int[] nums) {
        Arrays.sort(nums);
        // 4 ：2 ， 3 ：1
        int mid = nums.length >> 1;
        int right =  mid;
        int left = 0;
        int res = 0;
        while (left < mid && right < nums.length) {
            if (nums[left] * 2 <= nums[right]) {
                res += 2;
                left++;
            }
            right++;
        }
        return res;
    }
}