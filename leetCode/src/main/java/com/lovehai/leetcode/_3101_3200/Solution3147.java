package com.lovehai.leetcode._3101_3200;

import java.util.Arrays;

/**
 *
 * @author xiahaifeng
 */

public class Solution3147 {
    public int maximumEnergy(int[] energy, int k) {
        if (k > energy.length) {
            return Arrays.stream(energy).max().getAsInt();
        }
        int maxEnergy = Integer.MIN_VALUE;
        for (int i = energy.length - 1; i > energy.length - k - 1; i--) {
            int sum = 0;
            int index = i;
            while (index >= 0) {
                sum += energy[index];
                index -= k;
                if (sum > maxEnergy) {
                    maxEnergy = sum;
                }
            }
        }
        return maxEnergy;
    }
}