package com.LoveSea.fengCore.study.algorithms.sort;

/**
 * 冒泡排序
 * @author xiahaifeng
 */

public class BubbleSort implements BaseSort {
    @Override
    public int[] sort(int[] arr,int len, boolean asc){
        // 已经排好序的元素最小index
        int sortedMinIndex = len;
        // 上次交换的index
        int lastSwappedIndex = -1;
        boolean swapped;
        for (int i = 0; i < len - 1; i++) {
            swapped = false;
            for (int j = 0; j < sortedMinIndex - 1; j++) {
                if ((asc && arr[j] > arr[j + 1]) || (!asc && arr[j] < arr[j + 1])) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                    lastSwappedIndex = j;
                }
            }
            if (!swapped) {
                break;
            }
            sortedMinIndex = lastSwappedIndex;
        }
        return arr;
    }
}