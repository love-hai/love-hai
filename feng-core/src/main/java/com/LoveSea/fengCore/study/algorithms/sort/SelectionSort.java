package com.LoveSea.fengCore.study.algorithms.sort;

/**
 * 选择排序
 * 选择排序是一种简单直观的排序算法，它的基本思想是：首先在未排序的序列中找到最小（大）元素，存放到排序序列的起始位置，然后，再从剩余未排序的元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。以此类推，直到所有元素均排序完毕。
 *
 * @author xiahaifeng
 */

public class SelectionSort implements BaseSort {
    @Override
    public int[] sort(int[] arr, int len, boolean asc) {
        for (int i = 0; i < len - 1; i++) {
            int mIndex = i;
            for (int j = i + 1; j < len; j++) {
                if ((asc && arr[j] < arr[mIndex]) || (!asc && arr[j] > arr[mIndex])) {
                    if (arr[j] < arr[mIndex]) {
                        mIndex = j;
                    }
                }
            }
            if (mIndex != i) {
                int temp = arr[i];
                arr[i] = arr[mIndex];
                arr[mIndex] = temp;
            }
        }
        return arr;
    }
}