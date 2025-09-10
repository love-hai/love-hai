package com.lovehai.leetcode._0501_0600;

/**
 * 552. 学生出勤记录 II
 * 困难
 * 相关标签
 * 相关企业
 * 可以用字符串表示一个学生的出勤记录，其中的每个字符用来标记当天的出勤情况（缺勤、迟到、到场）。记录中只含下面三种字符：
 * 'A'：Absent，缺勤
 * 'L'：Late，迟到
 * 'P'：Present，到场
 * 如果学生能够 同时 满足下面两个条件，则可以获得出勤奖励：
 * 按 总出勤 计，学生缺勤（'A'）严格 少于两天。
 * 学生 不会 存在 连续 3 天或 连续 3 天以上的迟到（'L'）记录。
 * 给你一个整数 n ，表示出勤记录的长度（次数）。请你返回记录长度为 n 时，可能获得出勤奖励的记录情况 数量 。答案可能很大，所以返回对 109 + 7 取余 的结果。
 *
 *
 * @author xiahaifeng
 */

public class Solution552 {
    public int checkRecord(int n) {
        int MOD = 1000000007;
        long [][][] dp = new long[2][2][8];
        // LL  0
        // LP  1
        // LA  2
        // AL  3
        // AP  4
        // PP  5
        dp[0][0][5] = 1;
        // PA  6
        // PL  7
        int pre = 0;
        int cur = 1;
        for (int i = 0; i < n; i++) {
            dp[cur][0][0] = dp[pre][0][7];
            dp[cur][0][1] = (dp[pre][0][0]+dp[pre][0][7])%MOD;
            dp[cur][0][2] = 0;
            dp[cur][0][3] = 0;
            dp[cur][0][4] = 0;
            dp[cur][0][5] = (dp[pre][0][1]+dp[pre][0][5])%MOD;
            dp[cur][0][6] = 0;
            dp[cur][0][7] = dp[cur][0][5];

            dp[cur][1][0] = (dp[pre][1][3]+ dp[pre][1][7])%MOD;
            dp[cur][1][1] = (dp[pre][1][0]+dp[pre][1][3]+dp[pre][1][7])%MOD;
            dp[cur][1][2] =(dp[pre][0][0]+dp[pre][0][7])%MOD;
            dp[cur][1][3] = (dp[pre][1][2]+dp[pre][1][6])%MOD;
            dp[cur][1][4] =  dp[cur][1][3];
            dp[cur][1][5] = (dp[pre][1][1]+dp[pre][1][4]+dp[pre][1][5])%MOD;
            dp[cur][1][6] = (dp[pre][0][1]+dp[pre][0][5])%MOD;
            dp[cur][1][7] = (dp[pre][1][1]+dp[pre][1][4]+dp[pre][1][5])%MOD;
            pre = 1 - pre;
            cur = 1 - cur;
        }
        long res = 0;
        for (int i = 0; i < 8; i++) {
            res = (res + dp[pre][0][i])%MOD;
            res = (res + dp[pre][1][i])%MOD;
        }
        return  (int) res;
    }
}