package com.LoveSea.fengCore.study.leetCode;

/**
 * 922. 按奇偶排序数组 II
 * 给定一个非负整数数组 nums，  nums 中一半整数是 奇数 ，一半整数是 偶数 。
 * 对数组进行排序，以便当 nums[i] 为奇数时，i 也是 奇数 ；当 nums[i] 为偶数时， i 也是 偶数 。
 * 你可以返回 任何满足上述条件的数组作为答案 。
 *
 * @author xiahaifeng
 */

public class Solution922 {
    public int[] sortArrayByParityII(int[] nums) {
        int length = nums.length;
        for (int i = 0, odd = 1; i < length; i += 2) {
            // 判断 i 上面如果是偶数就跳过
            if ((nums[i] & 1) == 0) {
                continue;
            }
            while((nums[odd] & 1) == 1) {
                odd += 2;
            }
            int temp = nums[i];
            nums[i] = nums[odd];
            nums[odd] = temp;
        }
        return nums;
    }
}