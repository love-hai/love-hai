package com.lovehai.leetcode._0801_0900;

/**
 * 保持城市天际线
 * @author xiahaifeng
 */

public class Solution807 {
    /*
     * 给你一座由 n x n 个街区组成的城市，每个街区都包含一座立方体建筑。给你一个下标从 0 开始的 n x n 整数矩阵 grid ，
     * 其中 grid[r][c] 表示坐落于 r 行 c 列的建筑物的 高度 。
     * 城市的 天际线 是从远处观察城市时，所有建筑物形成的外部轮廓。从东、南、西、北四个主要方向观测到的 天际线 可能不同。
     * 我们被允许为 任意数量的建筑物 的高度增加 任意增量（不同建筑物的增量可能不同） 。 高度为 0 的建筑物的高度也可以增加。
     * 然而，增加的建筑物高度 不能影响 从任何主要方向观察城市得到的 天际线 。
     * 在 不改变 从任何主要方向观测到的城市 天际线 的前提下，返回建筑物可以增加的 最大高度增量总和 。
     */
    public int maxIncreaseKeepingSkyline(int[][] grid) {
        // 1.找到每行的最大值
        // 其实只需要看两个面
        int[] rowMax = new int[grid.length];
        for (int i = 0; i < grid.length; i++) {
            int max = 0;
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] > max) {
                    max = grid[i][j];
                }
            }
            rowMax[i] = max;
        }
        // 2.找到每列的最大值
        int[] colMax = new int[grid[0].length];
        for (int i = 0; i < grid[0].length; i++) {
            int max = 0;
            for (int j = 0; j < grid.length; j++) {
                if (grid[j][i] > max) {
                    max = grid[j][i];
                }
            }
            colMax[i] = max;
        }
        int sum = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                int min = Math.min(rowMax[i], colMax[j]);
                sum += min - grid[i][j];
            }
        }
        return sum;
    }

}