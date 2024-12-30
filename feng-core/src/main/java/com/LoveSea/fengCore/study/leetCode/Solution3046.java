package com.LoveSea.fengCore.study.leetCode;

/**
 * 分割数组
 * 给你一个长度为 偶数 的整数数组 nums 。你需要将这个数组分割成 nums1 和 nums2 两部分，要求：
 * nums1.length == nums2.length == nums.length / 2 。
 * nums1 应包含 互不相同 的元素。
 * nums2也应包含 互不相同 的元素。
 * 如果能够分割数组就返回 true ，否则返回 false 。
 * @author xiahaifeng
 */

public class Solution3046 {
    public boolean isPossibleToSplit(int[] nums) {
        int[] count = new int[101];
        for (int num : nums) {
            count[num]++;
            if(count[num]>2){
                return false;
            }
        }
        return true;
    }
}