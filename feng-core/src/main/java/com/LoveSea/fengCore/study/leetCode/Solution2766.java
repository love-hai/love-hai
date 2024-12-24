package com.LoveSea.fengCore.study.leetCode;


import java.util.*;

/**
 * 重新放置石块
 * 给你一个下标从 0 开始的整数数组 nums ，表示一些石块的初始位置。再给你两个长度 相等 下标从 0 开始的整数数组 moveFrom
 * 和 moveTo 。 在 moveFrom.length 次操作内，你将改变石块的位置。在第 i 次操作中，你将位置在 moveFrom[i] 的所有石块
 * 移到位置 moveTo[i] 。完成这些操作后，请你按升序返回所有有石块的位置。
 * 注意：
 * 如果一个位置至少有一个石块，我们称这个位置有石块。
 * 一个位置可能会有多个石块。
 *
 * @author xiahaifeng
 */

public class Solution2766 {
    public List<Integer> relocateMarbles(int[] nums, int[] moveFrom, int[] moveTo) {
        Map<Integer, Set<Integer>> tFmap = new HashMap<>();
        Map<Integer, Integer> ftMap = new HashMap<>();
        for (int i = 0; i < moveFrom.length; i++) {
            int moveFromIndex = moveFrom[i];
            int moveToIndex = moveTo[i];
            if (tFmap.containsKey(moveFromIndex)) {
                Set<Integer> oldFromIndex = tFmap.get(moveFromIndex);
                tFmap.remove(moveFromIndex);
                tFmap.put(moveTo[i], oldFromIndex);
                for (Integer index : oldFromIndex) {
                    ftMap.put(index, moveToIndex);
                }
            } else {
                tFmap.computeIfAbsent(moveToIndex, k -> new HashSet<>()).add(moveFromIndex);
            }
            ftMap.put(moveFromIndex, moveToIndex);
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (ftMap.containsKey(nums[i])) {
                nums[i] = ftMap.get(nums[i]);
            }
            set.add(nums[i]);
        }
        List<Integer> res = new ArrayList<>(set);
        Collections.sort(res);
        return res;
    }

    public static void main(String[] args) {
        Solution2766 solution2766 = new Solution2766();
        int[] nums = {3,4};
        int[] moveFrom = {4,3,1,2,2,3,2,4,1};
        int[] moveTo = {3,1,2,2,3,2,4,1,1};
        List<Integer> res = solution2766.relocateMarbles(nums, moveFrom, moveTo);
        System.out.println(res);
    }
}