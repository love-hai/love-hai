package com.LoveSea.fengCore.study.algorithms.sort;

/**
 * 计数排序
 *
 * @author xiahaifeng
 */

public class CountSort implements BaseSort {
    @Override
    public int[] sort(int[] arr, int len, boolean asc) {
        int max = arr[0];
        int min = arr[0];
        for (int i = 1; i < len; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        int[] count = new int[max - min + 1];
        for (int i = 0; i < len; i++) {
            count[arr[i] - min]++;
        }
        int index = 0;
        for (int i = 0; i < count.length; i++) {
            for (int j = 0; j < count[i]; j++) {
                arr[index++] = i + min;
            }
        }
        return arr;
    }
}