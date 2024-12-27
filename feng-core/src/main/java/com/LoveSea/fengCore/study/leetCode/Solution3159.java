package com.LoveSea.fengCore.study.leetCode;

import java.util.ArrayList;
import java.util.Arrays;
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
        int[] nums = {1, 2, 3};
        int[] queries = {10};
        int x = 5;
        Solution3159 solution3159 = new Solution3159();
        int[] result = solution3159.occurrencesOfElementPlus(nums, queries, x);
        for (int i : result) {
            System.out.println(i);
        }
    }

    // 大神的代码
    public int[] occurrencesOfElementPlus(int[] nums, int[] queries, int x) {
        int len = queries.length;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == x) {
                list.add(i);
            }
        }
        int[] answer = new int[len];
        int query;
        int location = list.size();
        for (int i = 0; i < len; i++) {
            query = queries[i] - 1;
            if (query >= location) {
                answer[i] = -1;
            } else {
                answer[i] = list.get(query);
            }
        }
        return answer;
    }

    public int[] occurrencesOfElement2(int[] nums, int[] queries, int x) {
        int n = nums.length;
        int index[] = new int[n];
        Arrays.fill(index, -1);
        int location = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == x) {
                index[location++] = i;
            }
        }
        n--;
        int len = queries.length;
        int answer[] = new int[len];
        for (int i = 0; i < len; i++) {
            location = queries[i] - 1;
            if (location > n) {
                answer[i] = -1;
            } else {
                answer[i] = index[location];
            }
        }
        return answer;
    }

}