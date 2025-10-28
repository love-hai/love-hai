package com.lovehai.leetcode._1701_1800;

/**
 *
 * @author xiahaifeng
 */

public class Solution1716 {
    public int totalMoney(int n) {
        int weeks = n / 7;
        int days = n % 7;
        int total = 0;
        // 第一周 28 第二周 28 + 7 第三周 28 + 2 * 7
        // 计算完整周的总和
        // total = 28  +  28 + 7 + 28 + 2 * 7 + ... + 28 + (weeks -1)* 7
        // total =  (28  + 28+ (weeks -1)* 7)  * weeks / 2
        total += (7 * weeks + 49) * weeks / 2;
        // 还要加上 weeks 周后的剩余天数
        total += (weeks + 1 + weeks + days) * days / 2;
        return total;
    }
}