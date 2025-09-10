package com.lovehai.leetcode._0901_1000;

/**
 * @author xiahaifeng
 */

public class Solution935 {
    /**
     * 给定一个整数 n，返回我们可以拨多少个长度为 n 的不同电话号码。
     * 你可以将骑士放置在任何数字单元格上，然后你应该执行 n - 1 次移动来获得长度为 n 的号码。
     * 所有的跳跃应该是有效的骑士跳跃。
     * 因为答案可能很大，所以输出答案模 109 + 7.
     */
    public int knightDialer(int n) {
        //   04, 06, 16, 18, 27, 29, 34, 38, 40, 43, 49, 60, 61, 67, 72, 76, 81, 83, 92, 94
        int mod = 1000000007;
        int[][] pre = new int[][]{{4, 6}, {6, 8}, {7, 9}, {4, 8}, {0, 3, 9}, {}, {0, 1, 7}, {2, 6}, {1, 3}, {2, 4}};
        int[][] dp = new int[2][10];
        if(n == 1) return 10;
        for(int i = 0; i < 10; i++) {
            if(i == 5) continue;
            dp[0][i] = 1;
        }
        for(int i = 1; i < n; i++) {
            for(int j = 0; j < 10; j++) {
                if(j == 5)
                    continue;
                dp[i % 2][j] = 0;
                for(int k : pre[j]) {
                    dp[i % 2][j] = (dp[i % 2][j] + dp[(i - 1) % 2][k]) % mod;
                }
            }
        }
        int res = 0;
        for(int i = 0; i < 10; i++) {
            res = (res + dp[(n-1) % 2][i]) % mod;
        }
        return res;
    }
}