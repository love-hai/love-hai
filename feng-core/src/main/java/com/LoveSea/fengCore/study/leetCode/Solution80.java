package com.LoveSea.fengCore.study.leetCode;

/**
 * 80. 删除有序数组中的重复项 II
 * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使得出现次数超过两次的元素只出现两次 ，返回删除后数组的新长度。
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 *
 * @author xiahaifeng
 */

public class Solution80 {
    public int removeDuplicates(int[] nums) {
        // 有序数组
        int left = 2;
        for (int i = 2; i < nums.length; i++) {
            if (nums[i] != nums[left - 2]) {
                nums[left++] = nums[i];
            }
        }
        return left;
    }
}