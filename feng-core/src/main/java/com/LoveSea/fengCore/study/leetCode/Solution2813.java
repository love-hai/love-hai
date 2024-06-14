package com.LoveSea.fengCore.study.leetCode;

import com.google.common.collect.Lists;

import java.util.*;

/**
 * @author xiahaifeng
 */
/*
给你一个长度为 n 的二维整数数组 items 和一个整数 k 。
items[i] = [profiti, categoryi]，其中 profiti 和 categoryi 分别表示第 i 个项目的利润和类别。
现定义 items 的 子序列 的 优雅度 可以用 total_profit + distinct_categories2 计算，
其中 total_profit 是子序列中所有项目的利润总和，distinct_categories 是所选子序列所含的所有类别中不同类别的数量。
你的任务是从 items 所有长度为 k 的子序列中，找出 最大优雅度 。
用整数形式表示并返回 items 中所有长度恰好为 k 的子序列的最大优雅度。
注意：数组的子序列是经由原数组删除一些元素（可能不删除）而产生的新数组，且删除不改变其余元素相对顺序。
 */
public class Solution2813 {
    public long findMaximumElegance(int[][] items, int k) {
        Arrays.sort(items, (item0, item1) -> item1[0] - item0[0]);
        categorySet = new HashSet<>();
        dp = new ArrayList<>();
        for (int[] item : items) {
            List<Integer> list = List.of(item[0], item[1]);
            dp.add(list);
        }
        return dnf(k);


    }
    private List<List<Integer>> dp;
    private Set<Integer> categorySet;

    public long dnf(int k) {
        if (k == 1) {
            categorySet.add(dp.get(0).get(1));
            int sum = dp.get(0).get(0)+1;
            dp.remove(0);
            return sum;
        }
        long sum = dnf(k-1);
        long categoryNum = categorySet.size();
        long categoryNumAdd = (categoryNum+1) * (categoryNum+1)-categoryNum*categoryNum;
        if(!categorySet.contains(dp.get(0).get(1))) {
            categorySet.add(dp.get(0).get(1));
            sum += dp.get(0).get(0);
            dp.remove(0);
            sum += categoryNumAdd;
            return sum;
        }else {
            long firstProfit = dp.get(0).get(0);
            for (int i = 1; i < dp.size(); i++) {
                if(!categorySet.contains(dp.get(i).get(1))) {
                    if(firstProfit >= dp.get(i).get(0)+categoryNumAdd) {
                        sum += firstProfit;
                        dp.remove(0);
                    }else {
                        sum += dp.get(i).get(0)+categoryNumAdd;
                        categorySet.add(dp.get(i).get(1));
                        dp.remove(i);
                    }
                    return sum;
                }
            }
            dp.remove(0);
            return sum+firstProfit;
        }
    }

    public static void main(String[] args) {
//        [[2,2],[8,6],[10,6],[2,4],[9,5],[4,5]]
        int[][] items = {{2,2},{8,6},{10,6},{2,4},{9,5},{4,5}};
        Solution2813 solution2813 = new Solution2813();
        System.out.println(solution2813.findMaximumElegance(items, 4));
    }
}