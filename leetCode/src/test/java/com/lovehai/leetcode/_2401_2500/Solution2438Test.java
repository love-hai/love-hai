package com.lovehai.leetcode._2401_2500;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Solution2438Test {
    @Test
    public void test1() {
        Solution2438 solution = new Solution2438();
        int n = 15;
        int[][] queries = {{0, 1}, {2, 2}, {0, 3}};
        int[] expected = {2, 4, 64};
        int[] result = solution.productQueries(n, queries);
        assertArrayEquals(expected, result);
    }

    @Test
    public void test2() {
        Solution2438 solution = new Solution2438();
        int n = 2;
        int[][] queries = {{0, 0}};
        int[] expected = {2};
        int[] result = solution.productQueries(n, queries);
        assertArrayEquals(expected, result);
    }
}