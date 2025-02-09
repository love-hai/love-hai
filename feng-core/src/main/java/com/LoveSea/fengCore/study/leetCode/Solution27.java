package com.LoveSea.fengCore.study.leetCode;

/**
 * 27. 移除元素
 * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素。
 * 元素的顺序可能发生改变。然后返回 nums 中与 val 不同的元素的数量。
 * 假设 nums 中不等于 val 的元素数量为 k，要通过此题，您需要执行以下操作：
 * <ul>
 *     <li>更改 nums 数组，使 nums 的前 k 个元素包含不等于 val 的元素。nums 的其余元素和 nums 的大小并不重要。</li>
 *     <li>返回 k。</li>
 * </ul>
 *
 * @author xiahaifeng
 */

public class Solution27 {
    public int removeElement(int[] nums, int val) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            if (nums[left] == val) {
                while (nums[right] == val) {
                    right--;
                    if (right <= left) {
                        return left;
                    }
                }
                nums[left] = nums[right--];
            }
            left++;
        }
        return left;
    }
}