package com.lovehai.leetcode._2500_2600;

import com.lovehai.leetcode._2501_2600.Solution2566;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author xiahaifeng
 */
public class Solution2566Test {

    @Test
    public void testMinMaxDifference() {
        Solution2566 solution2566 = new Solution2566();
        int ans = solution2566.minMaxDifference(11891);
        Assertions.assertEquals(99009, ans);
        ans = solution2566.minMaxDifference(90);
        Assertions.assertEquals(99, ans);
    }
}