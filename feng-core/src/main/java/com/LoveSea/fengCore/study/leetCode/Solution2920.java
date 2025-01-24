package com.LoveSea.fengCore.study.leetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 2920. 收集所有金币可获得的最大积分
 * 有一棵由n个节点组成的无向树，以 0为根节点，节点编号从 0到 n-1。
 * 给你一个长度为 n - 1 的二维 整数 数组 edges ，其中 edges[i] = [ai, bi] 表示在树上的节点 ai 和 bi 之间存在一条边。另给你一个下标从 0 开始、长度为 n 的数组 coins 和一个整数 k ，其中 coins[i] 表示节点 i 处的金币数量。
 * 从根节点开始，你必须收集所有金币。要想收集节点上的金币，必须先收集该节点的祖先节点上的金币。
 * 节点i上的金币可以用下述方法之一进行收集：
 * 收集所有金币，得到共计 coins[i] - k 点积分。如果 coins[i] - k 是负数，你将会失去 abs(coins[i] - k) 点积分。
 * 收集所有金币，得到共计 floor(coins[i] / 2) 点积分。如果采用这种方法，节点 i 子树中所有节点 j 的金币数 coins[j] 将会减少至 floor(coins[j] / 2) 。
 * 返回收集 所有树节点的金币之后可以获得的最大积分。
 *
 * @author xiahaifeng
 */
public class Solution2920 {
    private List<Integer>[] tree;
    private int[] coins;
    private int k;
    private int[][] dp;

    private int dpColLen;

    public int maximumPoints(int[][] edges, int[] coins, int k) {
        tree = new List[coins.length];
        Arrays.setAll(tree, i -> new ArrayList<>());
        int maxCoin = Arrays.stream(coins).max().getAsInt();
        int maxBit = Integer.toBinaryString(maxCoin).length();
        dp = new int[coins.length][++maxBit];
        dpColLen = maxBit;
        for (int i = 0; i < coins.length; i++) {
            Arrays.fill(dp[i], -1);
        }
        for (int[] edge : edges) {
            int x = edge[0];
            int y = edge[1];
            int min = Math.min(x, y);
            int max = Math.max(x, y);
            tree[min].add(max);
        }
        this.coins = coins;
        this.k = k;
        return dfs(0, 0);
    }

    int dfs(int index, int rightMove) {
        if (rightMove >= dpColLen) {
            rightMove = dpColLen - 1;
        }
        if (dp[index][rightMove] != -1) {
            return dp[index][rightMove];
        }
        int coin = coins[index] >> rightMove;
        int sum = coin - k;
        for (int i : tree[index]) {
            sum += dfs(i, rightMove);
        }
        rightMove++;
        int sum2 = coin >> 1;
        for (int i : tree[index]) {
            sum2 += dfs(i, rightMove);
        }
        rightMove--;
        dp[index][rightMove] = Math.max(sum, sum2);
        return dp[index][rightMove];
    }
}