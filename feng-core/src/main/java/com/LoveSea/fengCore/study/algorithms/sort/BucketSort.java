package com.LoveSea.fengCore.study.algorithms.sort;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiahaifeng
 */

public class BucketSort implements BaseSort {
    @Override
    public int[] sort(int[] arr, int len, boolean asc) {
        bucketSort(arr, len, asc);
        return arr;
    }

    private BaseSort baseSort = new HeapSort();
    private static final int n = 10;

    public void bucketSort(int[] arr, int len, boolean asc) {
        // 有n个桶
        int[][] buckets = new int[n][0];
        int max = arr[0];
        int min = arr[0];
        for (int i = 0; i < len; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        if (max == min) {
            return;
        }
        for (int i = 0; i < len; i++) {
            int index = (arr[i] - min) * n / (max - min);
            index = Math.min(index, n - 1);
            addBucket(buckets, index, arr[i]);
        }

        for (int i = 0; i < n; i++) {
            baseSort.sort(buckets[i], buckets[i].length, asc);
        }

        int index = 0;
        for (int i = 0; i < n; i++) {
            int length = buckets[i].length;
            System.arraycopy(buckets[i], 0, arr, index, length);
            index += length;
        }
    }

    void addBucket(int[][] buckets, int index, int value) {
        int[] bucket = buckets[index];
        int[] newBucket = new int[bucket.length + 1];
        System.arraycopy(bucket, 0, newBucket, 0, bucket.length);
        newBucket[bucket.length] = value;
        buckets[index] = newBucket;
    }

    // Driver program to test above function
    public static void main(String[] args) {
        int size = 100000;
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * size);
        }
        int n = arr.length;
        BucketSort ob = new BucketSort();
        int[] arr1 = new int[size];
        System.arraycopy(arr, 0, arr1, 0, size);
        long start1 = System.currentTimeMillis();
        ob.sort(arr1, n, true);
        long end1 = System.currentTimeMillis();
        System.out.println("Time cost:" + (end1 - start1) + "ms");

        HeapSort heapSort = new HeapSort();
        int[] arr2 = new int[size];
        System.arraycopy(arr, 0, arr2, 0, size);
        long start2 = System.currentTimeMillis();
        heapSort.sort(arr2, n, true);
        long end2 = System.currentTimeMillis();
        System.out.println("Time cost:" + (end2 - start2) + "ms");


        InsertionSort insertionSort = new InsertionSort();
        int[] arr3 = new int[size];
        System.arraycopy(arr, 0, arr3, 0, size);
        long start3 = System.currentTimeMillis();
        insertionSort.sort(arr3, n, true);
        long end3 = System.currentTimeMillis();
        System.out.println("Time cost:" + (end3 - start3) + "ms");
        List<Integer> list = new ArrayList<>();
        list.sort((o1, o2) -> o1 - o2);
    }
}