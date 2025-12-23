package com.lovehai.leetcode._2201_2300;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Solution2210Test {

    @Test
    void countHillValley() {
        int [] nums1 = {2,4,1,1,6,5};
        Solution2210 solution2210 = new Solution2210();
        assertEquals(3, solution2210.countHillValley(nums1));
    }
}