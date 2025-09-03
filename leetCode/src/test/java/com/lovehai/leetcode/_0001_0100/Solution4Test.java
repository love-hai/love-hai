package com.lovehai.leetcode._0001_0100;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Solution4Test {

    @Test
    public void findMedianSortedArrays() {
        Solution4 solution4 = new Solution4();

        double medianSortedArrays = solution4.findMedianSortedArrays(new int[]{2}, new int[]{1, 3, 4, 5, 6});
        Assertions.assertEquals(3.5, medianSortedArrays);

        medianSortedArrays = solution4.findMedianSortedArrays(new int[]{3}, new int[]{1, 2, 4});
        Assertions.assertEquals(2.5, medianSortedArrays);
    }
}