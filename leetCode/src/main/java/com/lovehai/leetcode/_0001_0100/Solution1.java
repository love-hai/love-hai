package com.lovehai.leetcode._0001_0100;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.IntStream;

/**
 *
 * @author xiahaifeng
 */

public class Solution1 {
    public int[] twoSum(int[] nums, int target) {
        Integer[] index = new Integer[nums.length];
        for (int i = 0; i < nums.length; i++) index[i] = i;
        Arrays.sort(index, (a, b) -> Integer.compare(nums[a], nums[b]));
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int sum = nums[index[left]] + nums[index[right]];
            if (sum == target) {
                return new int[]{index[left], index[right]};
            } else if (sum > target) {
                right--;
            } else {
                left++;
            }
        }
        return new int[]{-1, -1};
    }
}