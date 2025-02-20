package com.LoveSea.fengCore.study.leetCode;

import java.util.List;

/**
 * 给定 m 个数组，每个数组都已经按照升序排好序了。
 * 现在你需要从两个不同的数组中选择两个整数（每个数组选一个）并且计算它们的距离。
 * 两个整数 a 和 b 之间的距离定义为它们差的绝对值 |a-b| 。
 *
 * @author xiahaifeng
 */

public class Solution624 {
    public int maxDistance(List<List<Integer>> arrays) {
        int[][] mins = {{-1, Integer.MAX_VALUE}, {-1, Integer.MAX_VALUE}};
        int[][] maxs = {{-1, Integer.MIN_VALUE}, {-1, Integer.MIN_VALUE}};
        for (int i = 0; i < arrays.size(); i++) {
            List<Integer> list = arrays.get(i);
            if (list.get(0) < mins[0][1]) {
                mins[1][0] = mins[0][0];
                mins[1][1] = mins[0][1];
                mins[0][0] = i;
                mins[0][1] = list.get(0);
            } else if (list.get(0) < mins[1][1]) {
                mins[1][0] = i;
                mins[1][1] = list.get(0);
            }
            if (list.get(list.size() - 1) > maxs[0][1]) {
                maxs[1][0] = maxs[0][0];
                maxs[1][1] = maxs[0][1];
                maxs[0][0] = i;
                maxs[0][1] = list.get(list.size() - 1);
            } else if (list.get(list.size() - 1) > maxs[1][1]) {
                maxs[1][0] = i;
                maxs[1][1] = list.get(list.size() - 1);
            }
        }
        if (mins[0][0] != maxs[0][0]) {
            return maxs[0][1] - mins[0][1];
        } else {
            return Math.max(maxs[0][1] - mins[1][1], maxs[1][1] - mins[0][1]);
        }
    }
}