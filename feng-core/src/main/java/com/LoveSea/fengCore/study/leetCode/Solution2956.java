package com.LoveSea.fengCore.study.leetCode;

/**
 * @author xiahaifeng
 */

public class Solution2956 {
    /*
    给你两个下标从 0 开始的整数数组 nums1 和 nums2 ，
    它们分别含有 n 和 m 个元素。请你计算以下两个数值：
    nums1的值，在nums2中存在几个，answer1就是存在总和
    同理，nums2的值，在nums1中存在几个，就是answer2的答案
    返回 [answer1, answer2]。
     */
    public int[] findIntersectionValues(int[] nums1, int[] nums2) {


        return null;
    }

    int[] arraySort(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }
            }
        }
        return nums;
    }
}