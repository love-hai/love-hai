package com.LoveSea.fengCore.study.algorithms.sort;

/**
 * 归并排序
 *
 * @author xiahaifeng
 */

public class MergeSort implements BaseSort {
    @Override
    public int[] sort(int[] arr, int len, boolean asc) {
        sort(arr, 0, len - 1, asc);
        return arr;
    }

    private void sort(int[] arr, int s, int e, boolean asc) {
        // 子数组长度为1时，递归结束
        if (s < e) {
            int m = s + (e - s) / 2;
            sort(arr, s, m, asc);
            sort(arr, m + 1, e, asc);
            merge(arr, s, m, e, asc);
        }
    }

    private void merge(int[] arr, int s, int m, int e, boolean asc) {
        // 计算两个子数组的长度
        int n1 = m - s + 1;
        int[] L = new int[n1];
        // 复制数据到临时左子数组。
        System.arraycopy(arr, s, L, 0, n1);
        // 右子数组就用原数组的右边部分代替
        // 左子数组的起始索引
        int ls = 0;
        // 右子数组的起始索引
        int rs = m + 1;
        int k = s;
        while (ls < n1 && rs <= e) {
            if ((asc && L[ls] <= arr[rs]) || (!asc && L[ls] >= arr[rs])) {
                arr[k++] = L[ls++];
            } else {
                arr[k++] = arr[rs++];
            }
        }
        // 如果左子数组还有剩余，直接复制到原数组
        if (ls < n1) {
            System.arraycopy(L, ls, arr, k, n1 - ls);
        }
    }

}