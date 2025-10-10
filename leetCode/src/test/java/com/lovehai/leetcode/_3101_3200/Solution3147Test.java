package com.lovehai.leetcode._3101_3200;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Solution3147Test {

    @Test
    void maximumEnergy() {
        //	测试用例:[5,-10,4,3,5,-9,9,-7]
        //			2
        Solution3147 solution3147 = new Solution3147();
        int[] energy = new int[]{5, -10, 4, 3, 5, -9, 9, -7};
        Assertions.assertEquals(23, solution3147.maximumEnergy(energy, 2));
    }
}