package com.lovehai.leetcode._3001_3100;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author xiahaifeng
 */

public class Solution3005 {
    public int maxFrequencyElements(int[] nums) {
        int[] freq = new int[101]; // 视题目范围而定
        int max = 0;

        for (int num : nums) {
            int v = ++freq[num];
            if (v > max) {
                max = v;
            }
        }

        int count = 0;
        for (int f : freq) {
            if (f == max) {
                count++;
            }
        }
        return count * max;
    }
}