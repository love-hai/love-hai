package com.lovehai.leetcode._3301_3400;

/**
 *
 * @author xiahaifeng
 */

public class Solution3370 {
    public int smallestNumber(int n) {
        return (1 << (32 - Integer.numberOfLeadingZeros(n))) - 1;
    }
}