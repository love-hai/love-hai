package com.LoveSea.fengCore.study.leetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 90. 子集 II
 * 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
 * 解集不能包含重复的子集。返回的解集中，子集可以按任意顺序排列。
 *
 * @author xiahaifeng
 */

public class Solution90 {
    private List<Integer> t = new ArrayList<Integer>();
    private List<List<Integer>> result = new ArrayList<>();

    private int[] nums;

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        this.nums = nums;
        Arrays.sort(nums);
        dfs(false, 0);
        return result;
    }

    private void dfs(boolean choosePre, int index) {
        if (index == nums.length) {
            result.add(new ArrayList<>(t));
            return;
        }
        dfs(false, index + 1);
        if (!choosePre && index > 0 && nums[index - 1] == nums[index]) {
            return;
        }
        t.add(nums[index]);
        dfs(true, index + 1);
        t.remove(t.size() - 1);
    }
}