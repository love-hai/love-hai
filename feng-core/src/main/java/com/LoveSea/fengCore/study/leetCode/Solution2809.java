package com.LoveSea.fengCore.study.leetCode;

import java.util.Arrays;
import java.util.List;

/**
 * @author xiahaifeng
 */
/*
给你两个长度相等下标从 0 开始的整数数组 nums1 和 nums2 。每一秒，对于所有下标 0 <= i < nums1.length ，nums1[i] 的值都增加 nums2[i] 。操作 完成后 ，你可以进行如下操作：

选择任一满足 0 <= i < nums1.length 的下标 i ，并使 nums1[i] = 0 。
同时给你一个整数 x 。

请你返回使 nums1 中所有元素之和 小于等于 x 所需要的 最少 时间，如果无法实现，那么返回 -1 。
 */

public class Solution2809 {
    // FIXME: 2024/6/17 未完成
    public int minimumTime(List<Integer> nums1, List<Integer> nums2, int x) {
        quickSort(nums1,nums2);
        int minNums2 = nums2.get(0);
        int minNums2Index = 0;
        for (int i = 1; i < nums2.size(); i++) {
            if (nums2.get(i) < minNums2) {
                minNums2 = nums2.get(i);
                minNums2Index = i;
            }
        }
        int sum = Arrays.stream(nums1.toArray(new Integer[0])).mapToInt(Integer::intValue).sum();
        int res = 0;
        for (int i = 0; i < nums1.size(); i++) {
            if (sum <= x) {
                return res;
            }
            if (nums1.get(i) == 0) {
                continue;
            }
            if(i == minNums2Index) {
                continue;
            }
            res++;
            nums1.set(minNums2Index, nums1.get(minNums2Index) + nums2.get(minNums2Index));
            if(sum -nums1.get(minNums2Index) <= x) {
                return res;
            }
            sum -= nums1.get(i);
        }
        return -1;
    }

    public static void quickSort(List<Integer> nums1, List<Integer> nums2) {
        if (nums1 == null || nums1.isEmpty()) {
            return;
        }
        quickSort(nums1,nums2, 0, nums1.size() - 1);
    }

    private static void quickSort(List<Integer> nums1,List<Integer> nums2, int low, int high) {
        if (low < high) {
            int pi = partition(nums1,nums2, low, high);
            quickSort(nums1,nums2, low, pi - 1);
            quickSort(nums1,nums2, pi + 1, high);
        }
    }

    private static int partition(List<Integer> nums1,List<Integer> nums2, int low, int high) {
        int pivot = nums1.get(high);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (nums1.get(j) <= pivot) {
                i++;
                swap(nums1,nums2, i, j);
            }
        }
        swap(nums1, nums2,i + 1, high);
        return i + 1;
    }

    private static void swap(List<Integer> nums1,List<Integer> nums2, int i, int j) {
        int temp = nums1.get(i);
        nums1.set(i, nums1.get(j));
        nums1.set(j, temp);
        temp = nums2.get(i);
        nums2.set(i, nums2.get(j));
        nums2.set(j, temp);
    }

}