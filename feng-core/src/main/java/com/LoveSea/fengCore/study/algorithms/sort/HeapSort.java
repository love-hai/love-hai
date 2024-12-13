package com.LoveSea.fengCore.study.algorithms.sort;

/**
 * 堆排序
 *
 * @author xiahaifeng
 */

public class HeapSort implements BaseSort {
    @Override
    public int[] sort(int[] arr, int len, boolean asc) {
        // 第一步先构建asc为true 大顶堆,asc为false 小顶堆
        for (int i = len / 2 - 1; i >= 0; i--) {
            heapify(arr, len, i, asc);
        }
        // 第二步，将堆顶元素与末尾元素交换，然后重新调整堆
        int heapTail = len - 1;
        while (heapTail > 0) {
            swap(arr, 0, heapTail);
            heapify(arr, heapTail, 0, asc);
            heapTail--;
        }
        return arr;
    }

    private void heapify(int[] arr, int len, int i, boolean asc) {
        int lIndex = 2 * i + 1;
        int rIndex = 2 * i + 2;
        int extremumIndex = i;
        int extremum = arr[i];
        if (lIndex < len && ((asc && arr[lIndex] > extremum) || (!asc && arr[lIndex] < extremum))) {
            extremumIndex = lIndex;
            extremum = arr[lIndex];
        }
        if (rIndex < len && ((asc && arr[rIndex] > extremum) || (!asc && arr[rIndex] < extremum))) {
            extremumIndex = rIndex;
        }
        if (extremumIndex != i) {
            swap(arr, i, extremumIndex);
            heapify(arr, len, extremumIndex, asc);
        }
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