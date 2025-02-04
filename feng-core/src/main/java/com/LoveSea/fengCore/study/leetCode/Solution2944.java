package com.LoveSea.fengCore.study.leetCode;

/**
 * 2944. 购买水果需要的最少金币数
 * 给你一个 下标从 1 开始的 整数数组 prices ，其中 prices[i] 表示你购买第 i 个水果需要花费的金币数目。
 * 水果超市有如下促销活动：
 * 如果你花费 prices[i] 购买了下标为 i 的水果，那么你可以免费获得下标范围在 [i + 1, i + i] 的水果。
 * 注意即使你可以免费获得水果j你仍然可以花费 prices[j]个金币去购买它以获得它的奖励。
 * 请你返回获得所有水果所需要的最少金币数。
 *
 * @author xiahaifeng
 */

public class Solution2944 {
    public int minimumCoins(int[] prices) {
        int pricesSize = prices.length;
        if (pricesSize <= 2) {
            return prices[0];
        }
        int[] minCost = new int[pricesSize + 1];
        minCost[1] = prices[0];
        minCost[2] = prices[0];
        for (int i = 3; i <= pricesSize; i += 2) {
            int j = ceil(i, 2);
            int mincost_ = minCost[j - 1] + prices[j - 1];
            for (; j <= i; j++) {
                int value = prices[j - 1] + minCost[j - 1];
                if (mincost_ > value) {
                    mincost_ = value;
                }
            }
            minCost[i] = mincost_;
            if ((i + 1) <= pricesSize)
                minCost[i + 1] = minCost[i];
        }
        return minCost[pricesSize];
    }

    private int ceil(int a, int b) {
        int c = a / b;
        if (a % b != 0) {
            c++;
        }
        return c;
    }

    public static void main(String[] args) {
        Solution2944 solution2944 = new Solution2944();
        int[] prices = {26,18,6,12,49,7,45,45};
        System.out.println(solution2944.minimumCoins(prices));
    }

}