package com.LoveSea.fengCore.study.algorithms.sort;

import java.util.Random;

/**
 * 快速排序
 * 最优情况： O(n log n)（当每次都选到中位数或者接近中位数作为基准时）。
 * 最坏情况： O(n^2)（当数组已经排好序或是接近有序时，效率最低，基准值选择不当）。
 *
 * @author xiahaifeng
 */

public class QuickSort implements BaseSort {
    //
    @Override
    public int[] sort(int[] arr, int len, boolean asc) {
        quickSort(arr, 0, len - 1, asc);
        return arr;
    }
    private void quickSort(int[] arr, int left, int right, boolean asc) {
        if (left < right) {
            int partitionIndex = partition(arr, left, right, asc);
            quickSort(arr, left, partitionIndex - 1, asc);
            quickSort(arr, partitionIndex + 1, right, asc);
        }
    }
    private final Random random = new Random();
    private int partition(int[] arr, int left, int right, boolean asc) {
        // 小于基准值的元素的最大index
        int i = left - 1;
        int pivotIndex = random.nextInt(right - left + 1) + left;
        int pivot = arr[pivotIndex];
        swap(arr, pivotIndex, right);
        for (int j = left; j < right; j++) {
            if ((asc && arr[j] < pivot) || (!asc && arr[j] > pivot)) {
                swap(arr, ++i, j);
            }
        }
        swap(arr, ++i, right);
        return i;
    }
    private void swap(int[] arr, int i, int j) {
        if (i == j) {
            return;
        }
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}