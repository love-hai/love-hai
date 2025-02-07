package com.LoveSea.fengCore.study.leetCode;

/**
 * 59. 螺旋矩阵 II
 * 给你一个正整数n生成一个包含 1到n^2所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
 * @author xiahaifeng
 */

public class Solution59 {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int num = 1;
        int x = 0, y = 0;
        int length = n-1;
        while (length > 0) {
            for (int i = 0; i < length; i++) {
                matrix[y][x++] = num++;
            }
            for (int i = 0; i < length; i++) {
                matrix[y++][x] = num++;
            }
            for (int i = 0; i < length; i++) {
                matrix[y][x--] = num++;
            }
            for (int i = 0; i < length; i++) {
                matrix[y--][x] = num++;
            }
            x++;
            y++;
            length -= 2;
        }
        if (length == 0) {
            matrix[y][x] = num;
        }
        return matrix;
    }
}