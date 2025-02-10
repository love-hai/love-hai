package com.LoveSea.fengCore.study.leetCode;

import java.util.Arrays;

/**
 * 135. 分发糖果
 * n 个孩子站成一排。给你一个整数数组 ratings 表示每个孩子的评分。
 * 你需要按照以下要求，给这些孩子分发糖果：
 * 每个孩子至少分配到 1 个糖果。
 * 相邻两个孩子评分更高的孩子会获得更多的糖果。
 * 请你给每个孩子分发糖果，计算并返回需要准备的 最少糖果数目 。
 *
 * @author xiahaifeng
 */

public class Solution135 {
    public int candy(int[] ratings) {
        int result = 1;
        int lastCandy = 1;
        int len;
        // -1:下降 0:平 1:上升
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                result += ++lastCandy;
            } else if (ratings[i] == ratings[i - 1]) {
                result += 1;
                lastCandy = 1;
            } else {
                len = 2;
                i++;
                while (i < ratings.length) {
                    if (ratings[i] < ratings[i - 1]) {
                        len++;
                        i++;
                    } else {
                        i--;
                        break;
                    }
                }
                result += len * (len - 1) / 2;
                if (lastCandy < len) {
                    result += len - lastCandy;
                }
                lastCandy = 1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Solution135 solution135 = new Solution135();
        int[] ratings = {1, 0, 2};
        System.out.println(solution135.candy(ratings));
    }
}