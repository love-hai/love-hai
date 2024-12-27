package com.LoveSea.fengCore.study.leetCode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 查询数组中元素的出现位置
 * 给你一个整数数组 nums ，一个整数数组 queries 和一个整数 x 。
 * 对于每个查询 queries[i] ，你需要找到 nums 中第 queries[i] 个 x 的位置，
 * 并返回它的下标。如果数组中 x 的出现次数少于 queries[i] ，该查询的答案为 -1 。
 * 请你返回一个整数数组 answer ，包含所有查询的答案。
 *
 * @author xiahaifeng
 */

public class Solution3159 {
    public int[] occurrencesOfElement(int[] nums, int[] queries, int x) {
        List<Query> queryList = new ArrayList<>(queries.length);
        for (int i = 0; i < queries.length; i++) {
            Query query = new Query();
            query.query = queries[i];
            query.index = i;
            queryList.add(query);
        }
        int[] result = new int[queries.length];
        Collections.sort(queryList);
        int numsIndex = 0;
        int count = 0;
        int lastEqualIndex = -1;
        outer:
        for (int i = 0; i < queries.length; i++) {
            int query = queryList.get(i).query;
            if (count == query) {
                result[queryList.get(i).index] = lastEqualIndex;
                continue;
            }
            if (numsIndex >= nums.length) {
                result[queryList.get(i).index] = -1;
                continue;
            }
            for (; numsIndex < nums.length; numsIndex++) {
                if (nums[numsIndex] == x) {
                    count++;
                    lastEqualIndex = numsIndex;
                    if (count == query) {
                        result[queryList.get(i).index] = numsIndex;
                        numsIndex++;
                        continue outer;
                    }
                }
            }
            if (numsIndex == nums.length) {
                result[queryList.get(i).index] = -1;
            }
        }
        return result;
    }


    public static class Query implements Comparable<Query> {
        int query;
        int index;

        @Override
        public int compareTo(Query o) {
            return query - o.query;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 1, 7};
        int[] queries = {1, 3, 2, 4};
        int x = 1;
        Solution3159 solution3159 = new Solution3159();
        int[] result = solution3159.occurrencesOfElement(nums, queries, x);
        for (int i : result) {
            System.out.println(i);
        }
    }

}