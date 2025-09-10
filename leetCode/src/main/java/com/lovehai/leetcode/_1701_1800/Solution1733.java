package com.lovehai.leetcode._1701_1800;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author xiahaifeng
 */

public class Solution1733 {
    public int minimumTeachings(int n, int[][] languages, int[][] friendships) {
        int[] pers = new int[n];
        Set<Integer>[] sets = new Set[n];
        for (int[] friendship : friendships) {
            int[] a = languages[friendship[0] - 1];
            int[] b = languages[friendship[1] - 1];
            Arrays.sort(a);
            Arrays.sort(b);
            // 检查两个用户是否已经掌握相同的语言
            boolean flag = false;
            int i = 0, j = 0;
            while (i < a.length && j < b.length) {
                if (a[i] == b[j]) {
                    flag = true;
                    break;
                } else if (a[i] < b[j]) {
                    i++;
                } else {
                    j++;
                }
            }
            if (flag) {
                continue;
            }
            for (int k = 0; k < pers.length; k++) {
                if (sets[k] == null) {
                    sets[k] = new HashSet<>();
                }
                int lCode = k + 1;
                if (!sets[k].contains(friendship[0])) {
                    if (Arrays.binarySearch(a, lCode) < 0) {
                        pers[k]++;
                        sets[k].add(friendship[0]);
                    }
                }
                if (!sets[k].contains(friendship[1])) {
                    if (Arrays.binarySearch(b, lCode) < 0) {
                        pers[k]++;
                        sets[k].add(friendship[1]);
                    }
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for (int per : pers) {
            min = Math.min(min, per);
        }
        return min;
    }
}