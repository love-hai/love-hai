package com.lovehai.leetcode._0001_0100;

import java.util.Arrays;

/**
 *
 * @author xiahaifeng
 */

public class Solution4 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 更长的数组肯定会占据前后两部分
        if (nums1.length < nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }
        if (nums1.length == 0) {
            return 0;
        }
        // num1 .length >= num2.length
        int mid1 = (nums1.length + nums2.length + 1) / 2 - 1;
        int mid2 = (nums1.length + nums2.length + 2) / 2 - 1;
        if (nums2.length == 0) {
            return (nums1[mid1] + nums1[mid2]) / 2.0;
        }
        if (nums1[nums1.length - 1] <= nums2[0]) {
            return (nums1[mid1] + (mid2 > nums1.length - 1 ? nums2[0] : nums1[mid2])) / 2.0;
        } else if (nums2[nums2.length - 1] < nums1[0]) {
            return ((mid1 - nums2.length < 0 ? nums2[nums2.length - 1] : nums1[mid1 - nums2.length]) + nums1[mid2 - nums2.length]) / 2.0;
        }
        int sum = nums1.length + nums2.length;
        int half = sum / 2;
        int left = 0;
        int right = nums1.length - 1;
        int expectedSeparator = 0;
        int num2Sep = 0;
        while (left <= right) {
            expectedSeparator = (right + left) / 2;
            int num1PreCount = expectedSeparator - (-1);
            int num2PreCount = half - num1PreCount;
            num2Sep = num2PreCount - 1;
            if (Math.max(nums1[expectedSeparator], getValue(nums2, num2Sep)) > Math.min(nums1[expectedSeparator + 1], getValue(nums2, num2Sep + 1))) {
                if (nums1[expectedSeparator] > getValue(nums2, num2Sep)) {
                    right = expectedSeparator - 1;
                } else {
                    left = expectedSeparator + 1;
                }
            } else {
                break;
            }
        }
        if (sum % 2 == 1) {
            return Math.min(nums1[expectedSeparator + 1], getValue(nums2, num2Sep + 1));
        } else {
            return (Math.max(nums1[expectedSeparator], getValue(nums2, num2Sep)) + Math.min(nums1[expectedSeparator + 1], getValue(nums2, num2Sep + 1))) / 2.0;
        }
    }
    // 再来好好的理一下
    // 如果 在更长的数组中找到 一个分界点，这个分界点两边肯定是有数的，因为两种极端的情况已经率先考虑了
    // 所有 默认这个分界点是 属于前段数据的。

    int getValue(int[] nums, int index) {
        if (index < 0) {
            return Integer.MIN_VALUE;
        } else if (index >= nums.length) {
            return Integer.MAX_VALUE;
        }
        return nums[index];
    }


}