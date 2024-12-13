package com.LoveSea.fengCore.study.algorithms.sort;

/**
 * 循环排序
 *
 * @author xiahaifeng
 */

public class CycleSort implements BaseSort {
    @Override
    public int[] sort(int[] arr, int len, boolean asc) {
        return arr;
    }

    public void cycleSort(int[] arr, int len, boolean asc) {
        int writes = 0;
        for (int cycle_start = 0; cycle_start <= len - 2; cycle_start++) {
            int item = arr[cycle_start];
            // pos 为这个元素应该在的位置
            int pos = cycle_start;
            for (int i = cycle_start + 1; i < len; i++) {
                if ((asc && arr[i] < item) || (!asc && arr[i] > item)) {
                    pos++;
                }
            }
            if (pos == cycle_start) {
                continue;
            }
            // 这个是item应该在的位置已经有了跟他一样的元素，它就一次向后排
            while (item == arr[pos]) {
                pos += 1;
            }
            if (pos != cycle_start) {
                int temp = arr[pos];
                arr[pos] = item;
                // 原来的item已经在正确的位置上了，就相当于item 和 arr[pos] 交换了位置
                item = temp;
                writes++;
            }
            // 开始一个循环，直到找到本应该在cycle_start位置上的元素
            while (pos != cycle_start) {
                pos = cycle_start;
                for (int i = cycle_start + 1; i < len; i++) {
                    if ((asc && arr[i] < item) || (!asc && arr[i] > item)) {
                        pos += 1;
                    }
                }
                while (item == arr[pos]) {
                    pos += 1;
                }
                if (item != arr[pos]) {
                    int temp = arr[pos];
                    arr[pos] = item;
                    item = temp;
                    writes++;
                }
            }
        }
    }
}