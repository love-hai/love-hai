package com.lovehai.leetcode._3101_3200;

/**
 * 给你一个二维 boolean 矩阵 grid 。
 * 如果 grid 的 3 个元素的集合中，一个元素与另一个元素在 同一行，并且与第三个元素在 同一列，
 * 则该集合是一个 直角三角形。3 个元素 不必 彼此相邻。
 * 请你返回使用 grid 中的 3 个元素可以构建的 直角三角形 数目，且满足 3 个元素值 都 为 1 。
 *
 * @author xiahaifeng
 */

public class Solution3128 {
    public long numberOfRightTriangles(int[][] grid) {
        long result = 0;
        int rowLen = grid.length;
        int colLen = grid[0].length;
      /*  int[][] colDp = new int[colLen][2];
        int[][] rowDp = new int[rowLen][2];
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                if (grid[i][j] == 1) {
                    rowDp[i][1]++;
                    colDp[j][1]++;
                }
            }
        }
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                if (grid[i][j] != 1) {
                    continue;
                }
                // 以(i,j)为直角三角形的直角点
                rowDp[i][1]--;
                colDp[j][1]--;
     *//*           result += (long) rowDp[i][0] * colDp[j][0];
                result += (long) rowDp[i][1] * colDp[j][1];
                result += (long) rowDp[i][0] * colDp[j][1];
                result += (long) rowDp[i][1] * colDp[j][0];*//*
                // 简化公式
                result += (long) (rowDp[i][0] + rowDp[i][1]) * (colDp[j][0] + colDp[j][1]);
                // 这个你就是每一行每一列的1的个数-1 的乘积吗
                rowDp[i][0]++;
                colDp[j][0]++;
            }
        }*/
        int[] colDp = new int[colLen];
        int[] rowDp = new int[rowLen];
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                rowDp[i] += grid[i][j];
                colDp[j] += grid[i][j];
            }
        }
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                // 以(i,j)为直角三角形的直角点
                result += (long) (rowDp[i] - 1) * (colDp[j] - 1) * grid[i][j];
            }
        }
        return result;
    }
}