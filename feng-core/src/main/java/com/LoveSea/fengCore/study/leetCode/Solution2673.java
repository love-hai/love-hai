package com.LoveSea.fengCore.study.leetCode;

/**
 * @author xiahaifeng
 */

public class Solution2673 {

    int[] dp;
    int[] cost;

    int n;
    public int minIncrements(int n, int[] cost) {
        this.n = n;
        this.cost = cost;
        dp = new int[n];
        int res = 0;
        for (int i= n-1; i > 0; i-=2) {
//            int rightIndex = i;
            int leftIndex = i-1;
            int rightPathValue = getAfterPathValue(i)+cost[i];
            int leftPathValue = getAfterPathValue(leftIndex)+cost[leftIndex];
            int maxPathValue = Math.max(rightPathValue, leftPathValue);
            dp[i] = maxPathValue;
            dp[i-1] = maxPathValue;
            res += 2*maxPathValue- rightPathValue - leftPathValue;
        }
        return res;
    }

    /**
     * getAfterPathValue : 获取i位置后的路径值
     * @param i : 当前位置的索引
     * @author xiahaifeng
     */

    int getAfterPathValue(int i) {
        int leftChildIndex = (i+1) * 2 - 1;
        if(leftChildIndex >= n) {
            return 0;
        }
        return dp[leftChildIndex];
    }


}