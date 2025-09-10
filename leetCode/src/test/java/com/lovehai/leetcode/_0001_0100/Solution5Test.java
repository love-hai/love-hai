package com.lovehai.leetcode._0001_0100;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Solution5Test {

    @Test
    void longestPalindrome2() {
        Solution5 solution5 = new Solution5();
        String s = "ac";
        String ans = solution5.longestPalindrome2(s);
        Assertions.assertEquals("a", ans);
    }
}