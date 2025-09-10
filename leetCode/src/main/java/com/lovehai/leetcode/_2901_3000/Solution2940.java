package com.lovehai.leetcode._2901_3000;

/**
 * 给你一个下标从 0 开始的正整数数组 heights ，其中 heights[i] 表示第 i 栋建筑的高度。
 * 如果一个人在建筑 i ，且存在 i < j 的建筑 j 满足 heights[i] < heights[j] ，那么这个人可以移动到建筑 j 。
 * 给你另外一个数组 queries ，其中 queries[i] = [ai, bi] 。第 i 个查询中，Alice 在建筑 ai ，Bob 在建筑 bi 。
 * 请你能返回一个数组 ans ，其中 ans[i] 是第 i 个查询中，Alice 和 Bob 可以相遇的 最左边的建筑 。如果对于查询 i ，
 * Alice 和 Bob 不能相遇，令 ans[i] 为 -1 。
 *
 * @author xiahaifeng
 */

public class Solution2940 {
    public int[] leftmostBuildingQueries(int[] heights, int[][] queries) {

        return null;
    }

    public static void main(String[] args) {
        Solution2940 solution2940 = new Solution2940();
        int[] heights = new int[]{6, 4, 8, 5, 2, 7};
        // [[0,1],[0,3],[2,4],[3,4],[2,2]]
        int[][] queries = new int[][]{{0, 1}, {0, 3}, {2, 4}, {3, 4}, {2, 2}};
        int[] res = solution2940.leftmostBuildingQueries(heights, queries);
    }
}