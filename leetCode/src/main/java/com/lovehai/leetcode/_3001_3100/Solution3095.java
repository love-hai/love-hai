package com.lovehai.leetcode._3001_3100;

/**
 * 3095. 或值至少 K 的最短子数组 I
 * 给你一个非负整数数组 nums 和一个整数 k 。
 * 如果一个数组中所有元素的按位或运算 OR 的值 至少 为 k ，那么我们称这个数组是 特别的 。
 * 请你返回 nums 中 最短特别非空子数组的长度，如果特别子数组不存在，那么返回 -1 。
 *
 * @author xiahaifeng
 */

public class Solution3095 {
    public int minimumSubarrayLength(int[] nums, int k) {
        int[] sumB = new int[6];
        boolean[] kb = new boolean[6];
        for (int i = 0; i < 6; i++) {
            int b = bs[i];
            if ((b & k) == b) {
                kb[i] = true;
            }
        }
        int start = 0;
        int curLen = 0;
        int minLen = Integer.MAX_VALUE;
        for (int end = 0; end < nums.length; end++) {
            int num = nums[end];
            this.andNumber(sumB, num);
            curLen++;
            if (!this.compare(sumB, kb)) {
                continue;
            }
            do {
                this.noNumber(sumB, nums[start++]);
                curLen--;
            } while (this.compare(sumB, kb) && start <= end);
            if (curLen + 1 < minLen) {
                minLen = curLen + 1;
            }
        }
        if (Integer.MAX_VALUE == minLen) {
            minLen = -1;
        }
        return minLen;
    }

    int[] bs = {1, 2, 4, 8, 16, 32};

    private void noNumber(int[] sumB, int num) {
        for (int i = 0; i < 6; i++) {
            int b = bs[i];
            if ((b & num) == b) {
                sumB[i]--;
            }
        }
    }

    private void andNumber(int[] sumB, int num) {
        for (int i = 0; i < 6; i++) {
            int b = bs[i];
            if ((b & num) == b) {
                sumB[i]++;
            }
        }
    }

    private boolean compare(int[] sumB, boolean[] kB) {
        for (int i = 5; i >= 0; i--) {
            if (kB[i] && sumB[i] < 1) {
                return false;
            } else if (!kB[i] && sumB[i] > 0) {
                return true;
            }
        }
        return true;
    }
}