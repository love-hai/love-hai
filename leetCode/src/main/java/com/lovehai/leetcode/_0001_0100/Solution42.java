package com.LoveSea.fengCore.study.leetCode;

/**
 * 42. 接雨水
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 * @author xiahaifeng
 */

public class Solution42 {

    // 看了速度最快的解法，茅塞顿开。
    public int trap(int[] height) {
        int left = 1, right = height.length - 2;
        while (left < height.length) {
            if (height[left] < height[left - 1]) {
                left--;
                break;
            }
            left++;
        }
        while (right > 0) {
            if (height[right] < height[right +1]) {
                right++;
                break;
            }
            right--;
        }
        if(left >= right){
            return 0;
        }
        int res = 0;
        int leftTop  = left;
        int rightTop = right;
        while (left < right) {
            if(height[leftTop] < height[rightTop]) {
                left++;
                if(height[left] < height[leftTop]) {
                    res += height[leftTop] - height[left];
                } else {
                    leftTop = left;
                }
            } else {
                right--;
                if(height[right] < height[rightTop]) {
                    res += height[rightTop] - height[right];
                } else {
                    rightTop = right;
                }
            }
        }
        return res;
    }
    public int trapOriginal(int[] height) {
        int left = 1, right = height.length - 2;
        while (left < height.length) {
            if (height[left] < height[left - 1]) {
                left--;
                break;
            }
            left++;
        }
        while (right > 0) {
            if (height[right] < height[right +1]) {
                right++;
                break;
            }
            right--;
        }
        if(left >= right){
            return 0;
        }
        int leftTop  = left;
        int rightTop = right;
        int res = 0;
        while (left < right) {
            if (height[leftTop] < height[rightTop]) {
                left++;
                int clod = 0;
                while (left <= rightTop ){
                    if(height[left] < height[leftTop]) {
                        clod += height[left];
                    }else {
                        res += (left - leftTop - 1) * height[leftTop] - clod;
                        break;
                    }
                    left++;
                }
                // 继续洗衣歌寻找左边的最高点
                leftTop = rightTop;
                while (left <= rightTop) {
                    if (height[left] < height[left-1]) {
                        leftTop = --left;
                        break;
                    }
                    left++;
                }
            } else {
                right--;
                int clod = 0;
                while (right >= leftTop) {
                    if (height[right] < height[rightTop]) {
                        clod += height[right];
                    } else {
                        res += (rightTop - right - 1) * height[rightTop] - clod;
                        break;
                    }
                    right--;
                }
                // 继续向左寻找右边的最高点
                rightTop = leftTop;
                while (right > leftTop) {
                    if (height[right] < height[right + 1]) {
                        rightTop = ++right;
                        break;
                    }
                    right--;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        Solution42 solution42 = new Solution42();
        System.out.println(solution42.trap(height));
    }
}