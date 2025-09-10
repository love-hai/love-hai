package com.lovehai.leetcode._1701_1800;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Solution1733Test {

    @Test
    void minimumTeachings() {
        // 	测试用例:3
        //			[[2],[1,3],[1,2],[3]]
        //			[[1,4],[1,2],[3,4],[2,3]]
        Solution1733 solution1733 = new Solution1733();
        int n = 3;
        int[][] languages = new int[][]{{2},{1,3},{1,2},{3}};
        int[][] friendships = new int[][]{{1,4},{1,2},{3,4},{2,3}};
        int res = solution1733.minimumTeachings(n, languages, friendships);
        Assertions.assertEquals(2, res);
    }
}