package com.lovehai.leetcode._2201_2300;

/**
 *2239. 找到最接近 0 的数字
 * 给你一个长度为 n 的整数数组 nums ，请你返回 nums 中最 接近 0 的数字。如果有多个答案，请你返回它们中的 最大值 。
 * @author xiahaifeng
 */

public class Solution2239 {
    public int findClosestNumber(int[] nums) {
        int result =  nums[0];
        for (int num : nums) {
            if (Math.abs(num) < Math.abs(result) || (Math.abs(num) == Math.abs(result) && num > result)) {
                result = num;
            }
        }
        return result;
    }
}