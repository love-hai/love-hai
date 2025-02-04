package com.LoveSea.fengCore.study.leetCode;

import java.util.Arrays;

/**
 * 977. 有序数组的平方
 *
 * @author xiahaifeng
 */

public class Solution977 {
    public int[] sortedSquares(int[] nums) {
        int minIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            nums[i] *= nums[i];
            if (nums[i] < nums[minIndex]) {
                minIndex = i;
            }
        }
        if (minIndex == 0) {
            return nums;
        }
        int[] prefix = new int[minIndex];
        System.arraycopy(nums, 0, prefix, 0, minIndex);
        int index1 = prefix.length - 1;
        int index2 = minIndex;
        int index = 0;
        while (index1 >= 0 && index2 < nums.length) {
            if ((prefix[index1] < nums[index2])) {
                nums[index] = prefix[index1];
                index1--;
            } else {
                nums[index] = nums[index2];
                index2++;
            }
            index++;
        }

        while (index1 >= 0) {
            nums[index++] = prefix[index1--];
        }
        while (index2 < nums.length) {
            nums[index++] = nums[index2++];
        }
        return nums;
    }

    public int[] sortedSquares1(int[] nums) {
        // 暴力解法
        for (int i = 0; i < nums.length; i++) {
            nums[i] *= nums[i];
        }
        Arrays.sort(nums);
        return nums;
    }
}