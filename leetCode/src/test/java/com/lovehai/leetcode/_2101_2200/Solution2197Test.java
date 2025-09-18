package com.lovehai.leetcode._2101_2200;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class Solution2197Test {

    @Test
    void replaceNonCoprimes() {
        int[] nums;
        List<Integer> answer;
   /*     nums = new int[]{6, 4, 3, 2, 7, 6, 2};
        answer = new Solution2197().replaceNonCoprimes(nums);
        Assertions.assertEquals(List.of(12, 7, 6), answer);
        nums = new int[]{2, 2, 1, 1, 3, 3, 3};
        answer = new Solution2197().replaceNonCoprimes(nums);
        Assertions.assertEquals(List.of(2, 1, 1, 3), answer);
        nums = new int[]{31, 97561, 97561, 97561, 97561, 97561, 97561, 97561, 97561};
        answer = new Solution2197().replaceNonCoprimes(nums);
        Assertions.assertEquals(List.of(31, 97561), answer);*/
        nums = new int[]{287,41,49,287,899,23,23,20677,5,825};
        answer = new Solution2197().replaceNonCoprimes(nums);
        Assertions.assertEquals(List.of(2009,20677,825), answer);
    }
}