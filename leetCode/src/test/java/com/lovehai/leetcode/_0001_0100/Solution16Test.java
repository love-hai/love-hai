package com.lovehai.leetcode._0001_0100;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Solution16Test {

    @Test
    void threeSumClosest() {
        // [4,0,5,-5,3,3,0,-4,-5]
        int[] nums = new int[]{4, 0, 5, -5, 3, 3, 0, -4, -5};
        int ans = new Solution16().threeSumClosest(nums, -2);
        assertEquals(-2, ans);
    }
}