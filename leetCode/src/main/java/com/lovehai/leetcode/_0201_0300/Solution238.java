package com.LoveSea.fengCore.study.leetCode;

/**
 * 238. 除自身以外数组的乘积
 * 给你一个整数数组 nums，返回 数组 answer ，其中 answer[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积 。
 * 题目数据 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内。
 * 请 不要使用除法，且在 O(n) 时间复杂度内完成此题。
 * @author xiahaifeng
 */

public class Solution238 {
    public int[] productExceptSelf(int[] nums) {
        int[]  result= new int[nums.length];
        result[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            result[i] = result[i-1] * nums[i-1];
        }
        System.arraycopy(nums, 1, nums, 0, nums.length - 1);
        nums[nums.length - 1] = 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            nums[i] = nums[i] * nums[i+1];
            result[i] = result[i] * nums[i];

        }
        return result;
    }
    public int[] productExceptSelf1(int[] nums) {
        int[]  result= new int[nums.length];
        result[0] = 1;
        int temp = nums[1];
        for (int i = 1; i < nums.length-1; i++) {
            result[i] = result[i-1] * nums[i-1];
            nums[i-1] = nums[i];
        }
        result[nums.length-1] = result[nums.length-2] * nums[nums.length-2];
        nums[nums.length-2] = nums[nums.length-1];
        nums[nums.length-1] = 1;
        nums[0] = temp;
        for (int i = nums.length - 2; i >= 0; i--) {
            nums[i] = nums[i] * nums[i + 1];
            result[i] = result[i] * nums[i];
        }
        return result;
    }
}