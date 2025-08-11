package com.lovehai.leetcode._2401_2500;

import java.util.ArrayList;
import java.util.List;

/**
 * 2438. 二的幂数组中查询范围内的乘积
 * 给你一个正整数 n ，你需要找到一个下标从 0 开始的数组 powers ，它包含 最少 数目的 2 的幂，且它们的和为 n 。
 * powers 数组是 非递减 顺序的。根据前面描述，构造 powers 数组的方法是唯一的。
 * 同时给你一个下标从 0 开始的二维整数数组 queries ，其中 queries[i] = [lefti, righti] ，
 * 其中 queries[i] 表示请你求出满足 lefti <= j <= righti 的所有 powers[j] 的乘积。
 * 请你返回一个数组 answers ，长度与 queries 的长度相同，其中 answers[i]是第 i 个查询的答案。
 * 由于查询的结果可能非常大，请你将每个 answers[i] 都对 10^9 + 7 取余 。
 *
 * @author xiahaifeng
 */

public class Solution2438 {
    public int[] productQueries(int n, int[][] queries) {
        List<Integer> prefixProducts = getPrefixProducts(n);
        // 1000000007
        int mod = 1000000007;
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];
            int left = query[0];
            int right = query[1];
            long temp = prefixProducts.get(right);
            if (0 == left) {
                ans[i] = Math.toIntExact(temp);
            } else {
                long reverseSource = modInverse(prefixProducts.get(left - 1), mod);
                ans[i] = Math.toIntExact((temp * reverseSource) % mod);
            }
        }
        return ans;
    }

    // 快速幂求逆元
    private long modInverse(long a, int mod) {
        return powMod(a, mod - 2, mod);
    }

    private long powMod(long base, long exp, int mod) {
        long res = 1;
        while (exp > 0) {
            if ((exp & 1) == 1) res = (res * base) % mod;
            base = (base * base) % mod;
            exp >>= 1;
        }
        return res;
    }

    private List<Integer> getPrefixProducts(int n) {
        int mod = 1000000007;
        List<Integer> powers = new ArrayList<>();
        for (int bit = 0; n > 0; bit++) {
            if ((n & 1) == 1) {
                powers.add(1 << bit);
            }
            n >>= 1;
        }
        long temp = 1;
        for (int i = 0; i < powers.size(); i++) {
            temp = temp * powers.get(i) % mod;
            powers.set(i, Math.toIntExact(temp));
        }
        return powers;
    }

}