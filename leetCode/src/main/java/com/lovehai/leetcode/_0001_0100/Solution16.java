package com.lovehai.leetcode._0001_0100;

/**
 *
 * @author xiahaifeng
 */

public class Solution16 {
    public int threeSumClosest(int[] nums, int target) {
        int[] threeSum = new int[4];
        int[] dist = new int[4];
        threeSum[0] = nums[0];
        threeSum[1] = nums[1];
        threeSum[2] = nums[2];
        int free = 3;
        int sum = threeSum[0] + threeSum[1] + threeSum[2];
        for (int i = 3; i < nums.length; i++) {
            threeSum[free] = nums[i];
            sum += threeSum[free];
            int expected = sum - target;
            for (int j = 0; j < threeSum.length; j++) {
                dist[j] = Math.abs(expected - threeSum[j]);
            }
            int minIndex = 3;
            for (int j = 1; j < dist.length - 1; j++) {
                if (dist[j] < dist[minIndex]) {
                    minIndex = j;
                }
            }
            free = minIndex;
            sum -= threeSum[free];
        }
        return sum;
    }
}