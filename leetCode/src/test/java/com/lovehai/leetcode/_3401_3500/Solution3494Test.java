package com.lovehai.leetcode._3401_3500;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Solution3494Test {

    @Test
    void minTime() {
        // 	测试用例:[1,5,2,4]
        //			[5,1,4,2]

        int[] skill = new int[]{1, 5, 2, 4};
        int[] mana = new int[]{5, 1, 4, 2};
        Long time = new Solution3494().minTime(skill, mana);
        Assertions.assertEquals(110L, time);
    }
}