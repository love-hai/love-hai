package com.LoveSea.fengCore.study.leetCode;

/**
 * 11. 盛最多水的容器
 * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * 返回容器可以储存的最大水量。
 * 说明：你不能倾斜容器。
 *
 * @author xiahaifeng
 */

public class Solution11 {
    // 这是一个双指针问题
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int ans = Math.min(height[left], height[right]) * (right - left);
        while (left < right) {
            if (height[left] < height[right]) {
                int temp = height[left];
                do {
                    left++;
                } while (left < right && height[left] <= temp);
            } else {
                int temp = height[right];
                do {
                    right--;
                } while (left < right && height[right] <= temp);
            }
            int area = Math.min(height[left], height[right])*(right - left);
            if(area > ans){
                ans = area;
            }
        }
        return ans;
    }
}