package com.lovehai.leetcode._2701_2800;

import java.util.Arrays;

/**
 * @author xiahaifeng
 * 给你两个数字字符串 num1 和 num2 ，以及两个整数 max_sum 和 min_sum 。如果一个整数 x 满足以下条件，我们称它是一个好整数：
 * num1 <= x <= num2
 * min_sum <= digit_sum(x) <= max_sum.
 * 请你返回好整数的数目。答案可能很大，请返回答案对 109 + 7 取余后的结果。
 * 注意，digit_sum(x) 表示 x 各位数字之和。
 */

public class Solution2719 {
    int mod = 1000000007;
    int N = 23;
    int M = 401;

    int[][] dp;

    private int min_sum;
    private int max_sum;
    private String num;

    public int count(String num1, String num2, int min_sum, int max_sum) {
        this.min_sum = min_sum;
        this.max_sum = max_sum;
        dp = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dp[i][j] = -1;
            }
        }
        this.num = num2;
        int res = dfs(num.length() - 1, 0, true);
        this.num = sub(num1);
        res -= dfs(num.length() - 1, 0, true);
        return (res + mod) % mod;
    }
    public String sub(String num) {
        char[] arr = num.toCharArray();
        int i = arr.length - 1;
        while (arr[i] == '0') {
            i--;
        }
        arr[i]--;
        i++;
        while (i < arr.length) {
            arr[i] = '9';
            i++;
        }
        return new String(arr);
    }

    private int dfs(int pos, int sum, boolean limit) {
        if (sum > max_sum) {
            return 0;
        }
        if (-1 == pos) {
            return sum >= min_sum ? 1 : 0;
        }
        if (!limit && dp[pos][sum] != -1) {
            return dp[pos][sum];
        }
        int up = limit ? num.charAt(num.length()-pos-1) - '0' : 9;
        int res = 0;
        for (int i = 0; i <= up; i++) {
            res += dfs(pos - 1, sum + i, limit && i == up);
            res %= mod;
        }
        if (!limit) {
            dp[pos][sum] = res;
        }
        return res;
    }

    public static void main(String[] args) {
        Solution2719 solution2719 = new Solution2719();
        System.out.println(solution2719.count("4179205230", "7748704426", 8, 46));
    }
}