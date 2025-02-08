package com.LoveSea.fengCore.study.leetCode;

import java.util.Arrays;

/**
 * 63. 不同路径 II
 * 给定一个 m x n 的整数数组 grid。一个机器人初始位于 左上角（即 grid[0][0]）。
 * 机器人尝试移动到 右下角（即 grid[m - 1][n - 1]）。机器人每次只能向下或者向右移动一步。
 * 网格中的障碍物和空位置分别用 1 和 0 来表示。机器人的移动路径中不能包含 任何 有障碍物的方格。
 * 返回机器人能够到达右下角的不同路径数量。
 * 测试用例保证答案小于等于 2 * 109。
 *
 * @author xiahaifeng
 */

public class Solution63 {
    private int[][] obstacleGrid;

    private int[][] dp;

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        this.obstacleGrid = obstacleGrid;
        dp = new int[obstacleGrid.length][obstacleGrid[0].length];
        for (int i = 0; i < obstacleGrid.length; i++) {
            Arrays.fill(dp[i], -1);
        }
        if (obstacleGrid[0][0] == 1) {
            return 0;
        }
        dp[0][0] = 1;
        return dfs(obstacleGrid.length - 1, obstacleGrid[0].length - 1);
    }

    private int dfs(int x, int y) {
        if (x < 0 || y < 0) {
            return 0;
        }
        if (dp[x][y] != -1) {
            return dp[x][y];
        }
        if (obstacleGrid[x][y] == 1) {
            dp[x][y] = 0;
            return 0;
        }
        dp[x][y] = dfs(x - 1, y) + dfs(x, y - 1);
        return dp[x][y];
    }
}