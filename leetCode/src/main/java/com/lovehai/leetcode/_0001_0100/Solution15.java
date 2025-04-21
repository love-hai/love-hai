package com.lovehai.leetcode._0001_0100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 15. 三数之和
 * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，
 * 同时还满足 nums[i] + nums[j] + nums[k] == 0 。
 * 请你返回所有和为 0 且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 *
 * @author xiahaifeng
 */

public class Solution15 {
    public List<List<Integer>> threeSum(int[] nums) {
        // 1. 排序
        Arrays.sort(nums);
        int curIndex;
        int leftIndex;
        int rightIndex;
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            if(i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            curIndex = i;
            leftIndex = i + 1;
            rightIndex = nums.length - 1;
            while (leftIndex < rightIndex) {
                int sum = nums[curIndex] + nums[leftIndex] + nums[rightIndex];
                if (sum == 0) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[curIndex]);
                    list.add(nums[leftIndex]);
                    list.add(nums[rightIndex]);
                    result.add(list);
                    while (leftIndex < rightIndex && nums[leftIndex] == nums[leftIndex + 1]) {
                        leftIndex++;
                    }
                    while (leftIndex < rightIndex && nums[rightIndex] == nums[rightIndex - 1]) {
                        rightIndex--;
                    }
                    leftIndex++;
                    rightIndex--;
                } else if (sum > 0) {
                    rightIndex--;
                } else {
                    leftIndex++;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Solution15 solution15 = new Solution15();
        int[] nums = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> result = solution15.threeSum(nums);
        for (List<Integer> list : result) {
            System.out.println(list);
        }
    }
}