package com.lovehai.leetcode._1401_1500;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Solution1488Test {

    @Test
    void avoidFlood() {
//        测试用例:[1,2,0,2,3,0,1]
/*        int[] rains = new int[]{1, 2, 0, 2, 3, 0, 1};
        int[] ans = new Solution1488().avoidFlood(rains);
        Assertions.assertArrayEquals(new int[]{-1, -1, 2, -1, -1, 1, -1}, ans);*/

        //[1,0,2,0,3,0,2,0,0,0,1,2,3]
/*        int[] rains_1 = new int[]{1, 0, 2, 0, 3, 0, 2, 0, 0, 0, 1, 2, 3};
        int[] ans_1 = new Solution1488().avoidFlood(rains_1);
        Assertions.assertArrayEquals(new int[]{-1, 1, -1, 2, -1, 3, -1, 2, 1, 1, -1, -1, -1}, ans_1);*/
        // 	测试用例:[1,2,0,0,2,1]
        //	测试结果:[-1,-1,1,1,-1,-1]
        //	期望结果:[-1,-1,2,1,-1,-1]
        int[] rains_2 = new int[]{1, 2, 0, 0, 2, 1};
        int[] ans_2 = new Solution1488().avoidFlood(rains_2);
        Assertions.assertArrayEquals(new int[]{-1, -1, 2, 1, -1, -1}, ans_2);

    }
}