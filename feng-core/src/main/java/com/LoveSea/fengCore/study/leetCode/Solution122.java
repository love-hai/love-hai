package com.LoveSea.fengCore.study.leetCode;

/**
 * 122. 买卖股票的最佳时机 II
 * 给你一个整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格。
 * 在每一天，你可以决定是否购买和/或出售股票。你在任何时候 最多 只能持有 一股 股票。
 * 你也可以先购买，然后在 同一天 出售。
 * 返回 你能获得的 最大 利润 。
 * @author xiahaifeng
 */

public class Solution122 {
    public int maxProfit(int[] prices) {
        // 找到所有上升的区间，然后计算每个区间的差值
        int result = 0;
        for (int i = 1; i < prices.length; i++) {
            if(prices[i] > prices[i-1]){
                result += prices[i] - prices[i-1];
            }
        }
        return result;
    }
}