package com.lovehai.leetcode._3001_3100;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Solution3025Test {
    @Test
    public void testMinimumSubarrayLength() {
        Solution3025 solution = new Solution3025();
        int[][] nums = {{6, 2}, {4, 4}, {2, 6}};
        int ans = solution.numberOfPairs(nums);
        Assertions.assertEquals(2, ans);
    }

}