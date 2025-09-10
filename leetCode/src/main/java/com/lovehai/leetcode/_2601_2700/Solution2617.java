package com.lovehai.leetcode._2601_2700;

/**
 * @author xiahaifeng
 */

/*
给你一个下标从 0 开始的 m x n 整数矩阵 grid 。你一开始的位置在 左上角 格子 (0, 0) 。
当你在格子 (i, j) 的时候，你可以移动到以下格子之一：
满足 j < k <= grid[i][j] + j 的格子 (i, k) （向右移动），或者
满足 i < k <= grid[i][j] + i 的格子 (k, j) （向下移动）。
请你返回到达 右下角 格子 (m - 1, n - 1) 需要经过的最少移动格子数，如果无法到达右下角格子，请你返回 -1 。
 */

public class Solution2617 {
    // 超时
    public int minimumVisitedCells1(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                dp[i][j] = -1;
            }
        }
        dp[0][0] = 1;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if(-1==dp[i][j]) {
                    continue;
                }
                int sn = dp[i][j] + 1;
                int bored = Math.min(grid[i][j], grid.length - i - 1);
                for (int k = 1; k <= bored; k++) {
                    if(-1==dp[i+k][j]) {
                        dp[i + k][j] = sn;
                    }else {
                        dp[i + k][j] = Math.min(dp[i + k][j], sn);
                    }
                }
                bored = Math.min(grid[i][j], grid[0].length - j - 1);
                for (int k = 1; k <= bored; k++) {
                    if(-1==dp[i][j+k]) {
                        dp[i][j + k] = sn;
                    }else {
                        dp[i][j + k] = Math.min(dp[i][j + k], sn);
                    }
                }
            }
        }
        return dp[grid.length - 1][grid[0].length - 1];
    }


    private int[][] dp;
    private int[][] grid;

    public int minimumVisitedCells(int[][] grid) {
        this.grid = grid;
        dp = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                dp[i][j] = -1;
            }
        }
        dp[0][0] = 1;
        dfsCol(0,0,1,Math.min(grid[0][0],grid[0].length-1));
        dfsRow(0,0,1,Math.min(grid[0][0],grid.length-1));
        return dp[grid.length - 1][grid[0].length - 1];
    }

    public void dfsCol(int i,int j,int start,int end){
        if(end<start){
            return;
        }
        if(start>grid[0].length-1){
            return;
        }
        if(-1==dp[i][j]) {
            return;
        }
        if(j == grid[0].length-1){
            return;
        }
        if(i > grid.length-1){
            return;
        }
        for (int k = start; k <= end; k++) {
            if(-1==dp[i][k]) {
                dp[i][k] = dp[i][j] + 1;
            }else {
                dp[i][k] = Math.min(dp[i][k], dp[i][j] + 1);
            }
            dfsCol(i,k,end+1,Math.min(grid[i][k]+k,grid[0].length-1));
            dfsRow(i,k,i+1,Math.min(grid[i][k]+i,grid.length-1));
        }
    }

    public void dfsRow(int i,int j,int start,int end){
        if(end<start){
            return;
        }
        if(start>grid.length-1){
            return;
        }
        if(-1==dp[i][j]) {
            return;
        }
        if(i == grid.length-1){
            return;
        }
        if(j > grid[0].length-1){
            return;
        }
        for (int k = start; k <= end; k++) {
            if(-1==dp[k][j]) {
                dp[k][j] = dp[i][j] + 1;
            }else {
                dp[k][j] = Math.min(dp[k][j], dp[i][j] + 1);
            }
            dfsRow(k,j,end+1,Math.min(grid[k][j]+k,grid.length-1));
            dfsCol(k,j,j+1,Math.min(grid[k][j]+j,grid[0].length-1));
        }
    }



    public static void main(String[] args) {
        //[[1],[1],[0]]
        int[][] grid = {{1},{1},{0}};
        Solution2617 solution2617 = new Solution2617();
        System.out.println(solution2617.minimumVisitedCells(grid));
    }
}