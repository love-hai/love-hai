package com.lovehai.leetcode._1401_1500;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 *
 * @author xiahaifeng
 */

public class Solution1488 {
    public int[] avoidFlood(int[] rains) {
        Map<Integer, Integer> lastRain = new HashMap<>();
        TreeSet<Integer> dryDays = new TreeSet<>();
        int[] ans = new int[rains.length];
        for (int i = 0; i < rains.length; i++) {
            int rain = rains[i];
            if (rain <= 0) {
                dryDays.add(i);
                ans[i] = 1;
                continue;
            }
            ans[i] = -1;
            if (lastRain.containsKey(rain)) {
                int lastDay = lastRain.get(rain);
                // 找出比 lastDay 大的最早晴天
                Integer dryDay = dryDays.higher(lastDay);
                if (dryDay == null) {
                    return new int[0];
                }
                dryDays.remove(dryDay);
                ans[dryDay] = rain;
            }
            lastRain.put(rain, i);
        }
        return ans;
    }
}