package com.LoveSea.fengCore.study.leetCode;

/**
 * 189. 轮转数组
 * 给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
 *
 * @author xiahaifeng
 */

public class Solution189 {
    public void rotate(int[] nums, int k) {
        k %= nums.length;
        int[] temp = new int[k];
        System.arraycopy(nums, nums.length - k, temp, 0, k);
        System.arraycopy(nums, 0, nums, k, nums.length - k);
        System.arraycopy(temp, 0, nums, 0, k);
    }
}