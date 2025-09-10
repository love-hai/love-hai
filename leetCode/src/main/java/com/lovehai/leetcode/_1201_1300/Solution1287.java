package com.lovehai.leetcode._1201_1300;

/**
 * 1287. 有序数组中出现次数超过25%的元素
 * 给你一个非递减的 有序 整数数组，已知这个数组中恰好有一个整数，它的出现次数超过数组元素总数的 25%。
 * 请你找到并返回这个整数
 *
 * @author xiahaifeng
 */

public class Solution1287 {
    public int findSpecialInteger(int[] arr) {
        int[] possibleIndex ={arr.length / 4, arr.length / 2, arr.length * 3 / 4};
        for (int i : possibleIndex) {
            // 先往前找
            int j = i - 1;
            int count = 1;
            while (j >= 0 && arr[j] == arr[i]) {
                j--;
                count++;
            }
            j=i+1;
            while (j < arr.length && arr[j] == arr[i]) {
                j++;
                count++;
            }
            if(count>arr.length/4){
                return arr[i];
            }
        }
        return -1;
    }
}