package com.LoveSea.fengCore.study.algorithms.sort;

/**
 * 三路归并排序
 *
 * @author xiahaifeng
 */

public class ThreeWayMergeSort implements BaseSort {
    @Override
    public int[] sort(int[] arr, int len, boolean asc) {
        int[] destArr = new int[len];
        System.arraycopy(arr, 0, destArr, 0, len);
        mergeSort3Way(arr, destArr, 0, len - 1, asc);
        return destArr;
    }

    public void mergeSort3Way(int[] arr, int[] destArr, int startIndex, int endIndex, boolean asc) {
        if (startIndex >= endIndex) {
            return;
        }
        int len = endIndex - startIndex + 1;
        int dis1 = len / 3;
        int start1 = startIndex + dis1;
        int dis2 = (len - dis1) / 2;
        int start2 = startIndex + dis1 + dis2;
        mergeSort3Way(destArr, arr, startIndex, start1 - 1, asc);
        mergeSort3Way(destArr, arr, start1, start2 - 1, asc);
        mergeSort3Way(destArr, arr, start2, endIndex, asc);
        merge(arr, destArr, startIndex, start1, start2, endIndex, asc);
    }


    public static void merge(int[] arr, int[] destArr, int start, int start1, int start2, int end, boolean asc) {
        int lIndex = start;
        int mIndex = start1;
        int rIndex = start2;
        int index = start;
        while ((lIndex < start1) && (mIndex < start2) && (rIndex <= end)) {
            // 升序
            if (asc) {
                if (arr[lIndex] <= arr[mIndex]) {
                    if (arr[lIndex] <= arr[rIndex]) {
                        destArr[index++] = arr[lIndex++];
                    } else {
                        destArr[index++] = arr[rIndex++];
                    }
                } else {
                    if (arr[mIndex] <= arr[rIndex]) {
                        destArr[index++] = arr[mIndex++];
                    } else {
                        destArr[index++] = arr[rIndex++];
                    }
                }
            }else {
                if (arr[lIndex] > arr[mIndex]) {
                    if (arr[lIndex] > arr[rIndex]) {
                        destArr[index++] = arr[lIndex++];
                    } else {
                        destArr[index++] = arr[rIndex++];
                    }
                } else {
                    if (arr[mIndex] > arr[rIndex]) {
                        destArr[index++] = arr[mIndex++];
                    } else {
                        destArr[index++] = arr[rIndex++];
                    }
                }
            }
        }
        while (lIndex < start1 && mIndex < start2) {
            if (asc) {
                destArr[index++] = arr[lIndex] <= arr[mIndex] ? arr[lIndex++] : arr[mIndex++];
            } else {
                destArr[index++] = arr[lIndex] > arr[mIndex] ? arr[lIndex++] : arr[mIndex++];
            }
        }
        while (lIndex < start1 && rIndex <= end) {
            if (asc) {
                destArr[index++] = arr[lIndex] <= arr[rIndex] ? arr[lIndex++] : arr[rIndex++];
            } else {
                destArr[index++] = arr[lIndex] > arr[rIndex] ? arr[lIndex++] : arr[rIndex++];
            }
        }
        while (mIndex < start2 && rIndex <= end) {
            if (asc) {
                destArr[index++] = arr[mIndex] <= arr[rIndex] ? arr[mIndex++] : arr[rIndex++];
            } else {
                destArr[index++] = arr[mIndex] > arr[rIndex] ? arr[mIndex++] : arr[rIndex++];
            }
        }
        while (lIndex < start1) {
            destArr[index++] = arr[lIndex++];
        }
        while (mIndex < start2) {
            destArr[index++] = arr[mIndex++];
        }
        while (rIndex <= end) {
            destArr[index++] = arr[rIndex++];
        }
    }
}