package com.lovehai.leetcode._3001_3100;

import java.util.Arrays;

/**
 *
 * @author xiahaifeng
 */

public class Solution3027 {
    public int numberOfPairs(int[][] points) {
        // 先进行排序
        Arrays.sort(points, (a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            return Integer.compare(b[1], a[1]);
        });
        int ans = 0;
        for (int i = 0; i < points.length - 1; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = i + 1; j < points.length; j++) {
                if (points[i][1] >= points[j][1] && points[j][1] > max) {
                    max = points[j][1];
                    ans++;
                }
            }
        }
        return ans;
    }
}