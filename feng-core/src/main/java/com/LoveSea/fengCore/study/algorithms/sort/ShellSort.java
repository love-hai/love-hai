package com.LoveSea.fengCore.study.algorithms.sort;

/**
 * 希尔排序
 *
 * @author xiahaifeng
 */

public class ShellSort implements BaseSort {
    @Override
    public int[] sort(int[] arr, int len, boolean asc) {
        int temp;
        for (int step = len / 2; step >= 1; step /= 2) {
            // 从第step个元素开始，到len-1结束
            // 对shell规定的子数组穿插の进行排序
            for (int i = step; i < len; i++) {
                temp = arr[i];
                int j = i - step;
                while (j >= 0 && (asc ? temp < arr[j] : temp > arr[j])) {
                    arr[j + step] = arr[j];
                    j -= step;
                }
                arr[j + step] = temp;
            }
        }
        return arr;
    }
}