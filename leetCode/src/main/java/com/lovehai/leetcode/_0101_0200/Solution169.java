package com.LoveSea.fengCore.study.leetCode;

import java.util.Arrays;

/**
 * 169. 多数元素
 * 给定一个大小为 n 的数组 nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 *
 * @author xiahaifeng
 */

public class Solution169 {
    public int majorityElement(int[] nums) {
        // 同归于尽消杀法
        int count = 0;
        int winner = nums[0];
        for (int num : nums) {
            if (num == winner) {
                count++;
            } else {
                count--;
                if (count == 0) {
                    winner = num;
                    count = 1;
                }
            }
        }
        return winner;
    }

    public int majorityElement1(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }
}