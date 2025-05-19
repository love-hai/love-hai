package com.lovehai.leetcode._2500_2600;

/**
 * 2545. 根据第 K 场考试的分数排序
 * 班里有 m 位学生，共计划组织 n 场考试。给你一个下标从 0 开始、大小为 m x n 的整数矩阵 score，
 * 其中每一行对应一位学生，而 score[i][j] 表示第 i 位学生在第 j 场考试取得的分数。矩阵 score 包含的整数 互不相同 。
 * 另给你一个整数 k 。请你按第 k 场考试分数从高到低完成对这些学生（矩阵中的行）的排序。
 * 返回排序后的矩阵。
 *
 * @author xiahaifeng
 */

public class Solution2545 {
    public int[][] sortTheStudents(int[][] score, int k) {
        this.heapSort(score, k);
        return score;
    }

    // 堆排序
    public void heapSort(int[][] score, int k) {
        int n = score.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(score, n, i, k);
        }
        for (int i = n - 1; i > 0; i--) {
            swap(score, 0, i);
            heapify(score, i, 0, k);
        }
    }

    public void heapify(int[][] score, int n, int i, int k) {
        int minIndex = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && score[left][k] < score[minIndex][k]) {
            minIndex = left;
        }
        if (right < n && score[right][k] < score[minIndex][k]) {
            minIndex = right;
        }
        if (minIndex != i) {
            swap(score, i, minIndex);
            heapify(score, n, minIndex, k);
        }
    }

    public void swap(int[][] score, int i, int j) {
        int[] temp = score[i];
        score[i] = score[j];
        score[j] = temp;
    }

    public static void main(String[] args) {
        Solution2545 solution2545 = new Solution2545();
        int[][] score = {{10, 6, 9, 1}, {7, 5, 11, 2}, {4, 8, 3, 15}};
        int k = 2;
        int[][] result = solution2545.sortTheStudents(score, k);
        for (int[] arr : result) {
            for (int num : arr) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }

}