package com.LoveSea.fengCore.study.algorithms.sort;

/**
 * 插入排序
 *
 * @author xiahaifeng
 */

public class InsertionSort implements BaseSort {
    /*
    | 已排序区间 | 未排序区间 |
    从未排序区间取出一个元素，插入到已排序区间的合适位置
    逻辑比较简单，但是交换位置
     */
    @Override
    public int[] sort(int[] arr, int len, boolean asc) {
        for (int i = 1; i < len; i++) {
            int insertIndex = findInsertIndex(arr, 0, i - 1, arr[i], asc);
            if (insertIndex != i) {
                shiftRightSwap(arr, insertIndex, i);
            }
        }
        return arr;
    }

    private void shiftRightSwap(int[] arr, int startIndex, int endIndex) {
        int temp = arr[endIndex];
        System.arraycopy(arr, startIndex, arr, startIndex + 1, endIndex - startIndex);
        arr[startIndex] = temp;
    }


    private int findInsertIndex(int[] arr, int startIndex, int endIndex, int value, boolean asc) {
        if (asc) {
            if (value <= arr[startIndex]) {
                return startIndex;
            } else if (value >= arr[endIndex]) {
                return endIndex + 1;
            }
            while (startIndex <= endIndex) {
                int midIndex = (startIndex + endIndex) / 2;
                if (arr[midIndex] == value) {
                    return midIndex;
                } else if (arr[midIndex] > value) {
                    endIndex = midIndex - 1;
                } else {
                    startIndex = midIndex + 1;
                }
            }
            return startIndex;
        } else {
            if (value >= arr[startIndex]) {
                return startIndex;
            } else if (value <= arr[endIndex]) {
                return endIndex + 1;
            }
            while (startIndex <= endIndex) {
                int midIndex = (startIndex + endIndex) / 2;
                if (arr[midIndex] == value) {
                    return midIndex;
                } else if (arr[midIndex] < value) {
                    endIndex = midIndex - 1;
                } else {
                    startIndex = midIndex + 1;
                }
            }
            return startIndex;
        }
    }
}