package com.lovehai.leetcode._2201_2300;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 2274. 不含特殊楼层的最大连续楼层数
 * Alice 管理着一家公司，并租用大楼的部分楼层作为办公空间。
 * Alice 决定将一些楼层作为 特殊楼层 ，仅用于放松。
 * 给你两个整数 bottom 和 top ，表示 Alice 租用了从 bottom 到 top（含 bottom 和 top 在内）的所有楼层。
 * 另给你一个整数数组 special ，其中 special[i] 表示  Alice 指定用于放松的特殊楼层。
 * 返回不含特殊楼层的 最大 连续楼层数。
 *
 * @author xiahaifeng
 */

public class Solution2274 {
    public int maxConsecutive(int bottom, int top, int[] special) {
        Arrays.sort(special);
        int max = 0;
        int start = bottom;
        int cur;
        for (int s : special) {
            if ((cur = s - start) > max) {
                max = cur;
            }
            start = s + 1;
        }
        if ((cur = top - start + 1) > max) {
            max = cur;
        }
        return max;
    }
}