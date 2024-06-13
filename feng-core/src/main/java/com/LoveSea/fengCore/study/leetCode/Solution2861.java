package com.LoveSea.fengCore.study.leetCode;

/**
 * @author xiahaifeng
 */

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 假设你是一家合金制造公司的老板，你的公司使用多种金属来制造合金。现在共有 n 种不同类型的金属可以使用，
 并且你可以使用 k 台机器来制造合金。每台机器都需要特定数量的每种金属来创建合金。
 对于第 i 台机器而言，创建合金需要 composition[i][j] 份 j 类型金属。
 最初，你拥有 stock[i] 份 i 类型金属，而每购入一份 i 类型金属需要花费 cost[i] 的金钱。
 给你整数 n、k、budget，下标从 1 开始的二维数组 composition，
 两个下标从 1 开始的数组 stock 和 cost，请你在预算不超过 budget 金钱的前提下，最大化 公司制造合金的数量。
 所有合金都需要由同一台机器制造。
 返回公司可以制造的最大合金数。
 */

public class Solution2861 {

    public int maxNumberOfAlloys(int n, int k, int budget, List<List<Integer>> composition, List<Integer> stock, List<Integer> cost) {
        int left = 0,right = 200000000,ans = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            boolean flag = false;
            for (int i = 0; i < k; i++) {
                long sumCost = 0;
                for (int j = 0; j < n; j++) {
                    long remaining = (long) composition.get(i).get(j) *mid - stock.get(j);
                    remaining = Math.max(0, remaining);
                    sumCost += remaining * cost.get(j);
                }
                if(sumCost <= budget) {
                    flag = true;
                    break;
                }
            }
            if(flag) {
                ans = mid;
                left = mid + 1;
            }else {
                right = mid - 1;
            }
        }
        return ans;
    }



    @Deprecated
    public int maxNumberOfAlloys1(int n, int k, int budget, List<List<Integer>> composition, List<Integer> stock, List<Integer> cost) {

        int num = 0;
        for (int i = 0; i < k; i++) {
            List<Integer> numCost = composition.get(i);
            List<Integer> stockCopy = new ArrayList<>(List.copyOf(stock));
            int temp = numOfOne(numCost, stockCopy, cost, budget, n);
            num = Math.max(num, temp);
        }
        return num;
    }

    public int numOfOne(List<Integer> numCost, List<Integer> stock, List<Integer> cost,int budget,int n) {
        int count = 0;
        while (true) {
            for (int j = 0; j < n; j++) {
                if (stock.get(j) < numCost.get(j)) {
                    budget -= (numCost.get(j) - stock.get(j)) * cost.get(j);
                    stock.set(j, 0);
                    if (budget < 0) {
                        return count;
                    }
                }else {
                    stock.set(j, stock.get(j) - numCost.get(j));
                }
            }
            count++;
        }
    }

    public static void main(String[] args) {
        Solution2861 solution2861 = new Solution2861();

       //  [[5,2,3,4,7,3,3,1],[1,5,9,9,6,1,9,7],[5,8,3,10,2,4,8,7],[9,9,5,9,6,8,4,3]]
        List<List<Integer>> composition = new ArrayList<>();
        List<Integer> list1 = Lists.newArrayList(5,2,3,4,7,3,3,1);
        List<Integer> list2 = Lists.newArrayList(1,5,9,9,6,1,9,7);
        List<Integer> list3 = Lists.newArrayList(5,8,3,10,2,4,8,7);
        List<Integer> list4 = Lists.newArrayList(9,9,5,9,6,8,4,3);
        composition.add(list1);
        composition.add(list2);
        composition.add(list3);
        composition.add(list4);
//        [3,5,3,6,1,5,8,1]
        List<Integer> stock =Lists.newArrayList(3,5,3,6,1,5,8,1);
        //[4,5,4,9,4,8,7,5]
        List<Integer> cost =  Lists.newArrayList(4,5,4,9,4,8,7,5);
        System.out.println(solution2861.maxNumberOfAlloys(8,4,196,composition,stock,cost));
    }
}