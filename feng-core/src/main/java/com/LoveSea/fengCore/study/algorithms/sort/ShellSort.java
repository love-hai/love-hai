package com.LoveSea.fengCore.study.algorithms.sort;

/**
 * 希尔排序
 *
 * @author xiahaifeng
 */

public class ShellSort {
    public static void shellSort(int[] arr, int len, boolean asc) {

        int temp;
        for (int step = len / 2; step >= 1; step /= 2) {
            for (int i = step; i < len; i++) {
                temp = arr[i];
                int j = i - step;
                while (j >= 0 && arr[j] > temp) {
                    arr[j + step] = arr[j];
                    j -= step;
                }
                arr[j + step] = temp;
            }
        }
    }
}