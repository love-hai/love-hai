package com.LoveSea.fengCore.study.algorithms.sort;

/**
 * 基数排序
 *
 * @author xiahaifeng
 */

public class RadixSort implements BaseSort {

    @Override
    public int[] sort(int[] arr, int len, boolean asc) {
        int max = arr[0];
        for (int i = 1; i < len; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        int exp = 1;
        int[] temp = new int[len];
        while (max / exp > 0) {
            int[] count = new int[10];
            for (int i = 0; i < len; i++) {
                count[(arr[i] / exp) % 10]++;
            }
            for (int i = 1; i < 10; i++) {
                count[i] += count[i - 1];
            }
            for (int i = len - 1; i >= 0; i--) {
                temp[--count[(arr[i] / exp) % 10]] = arr[i];
            }
            System.arraycopy(temp, 0, arr, 0, len);
            exp *= 10;
        }
        return arr;
    }
}