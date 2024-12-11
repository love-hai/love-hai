package com.LoveSea.fengCore.study.leetCode;

/**
 * 半有序排列
 *
 * @author xiahaifeng
 */

public class Solution2717 {
    /*
    给你一个下标从 0 开始、长度为 n 的整数排列 nums 。
    如果排列的第一个数字等于 1 且最后一个数字等于 n ，则称其为 半有序排列 。
    你可以执行多次下述操作，直到将 nums 变成一个半有序排列 ：
    选择 nums 中相邻的两个元素，然后交换它们。
    返回使 nums 变成半有序排列 所需的最小操作次数。
    排列 是一个长度为 n 的整数序列，其中包含从 1 到 n 的每个数字恰好一次。
    */
    public int semiOrderedPermutation(int[] nums) {
        int zeroIndex = -1;
        int nIndex = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1 && -1 == zeroIndex) {
                zeroIndex = i;
            }
            if (nums[nums.length - 1 - i] == nums.length && -1 == nIndex) {
                nIndex = nums.length - 1 - i;
            }
            if (-1 != zeroIndex && -1 != nIndex) {
                break;
            }
        }
        if (zeroIndex > nIndex) {
            return zeroIndex + (nums.length - 1 - nIndex) - 1;
        } else{
            return zeroIndex + (nums.length - 1 - nIndex);
        }
    }
}