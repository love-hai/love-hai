package com.LoveSea.fengCore.study.leetCode;

import java.util.*;

/**
 * 2080. 区间内查询数字的频率
 * 请你设计一个数据结构，它能求出给定子数组内一个给定值的 频率 。
 * 子数组中一个值的 频率 指的是这个子数组中这个值的出现次数。
 * 请你实现 RangeFreqQuery 类：
 * RangeFreqQuery(int[] arr) 用下标从 0 开始的整数数组 arr 构造一个类的实例。
 * int query(int left, int right, int value) 返回子数组 arr[left...right]中value的频率 。
 * 一个子数组指的是数组中一段连续的元素。arr[left...right] 指的是nums中包含下标left和right在内的中间一段连续元素。
 *
 * @author xiahaifeng
 */

public class Solution2080 {
    public static class RangeFreqQuery {
        private Map<Integer, List<Integer>> map;

        public RangeFreqQuery(int[] arr) {
            map = new HashMap<>();
            for (int index = 0; index < arr.length; index++) {
                map.computeIfAbsent(arr[index], k -> new ArrayList<>()).add(index);
            }
        }

        public int query(int left, int right, int value) {
            List<Integer> list = map.get(value);
            if (list == null) {
                return 0;
            }
            int leftIndex = Collections.binarySearch(list, left);
            binarySearch(list, 0, list.size() - 1, left);
            if (leftIndex < 0) {
                leftIndex = -leftIndex - 1;
            }
            int rightIndex = binarySearch(list, leftIndex, list.size() - 1, right);
            if (rightIndex < 0) {
                rightIndex = -rightIndex - 2;
            }
            return rightIndex - leftIndex + 1;
        }

        private int binarySearch(List<Integer> list, int left, int right, int target) {
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (list.get(mid) == target) {
                    return mid;
                } else if (list.get(mid) < target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            return -left - 1;
        }
    }
}