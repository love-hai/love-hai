package com.lovehai.leetcode._0601_0700;

import java.util.*;

/**
 * 掉落的方块
 * 在二维平面上的 x 轴上，放置着一些方块。
 * 给你一个二维整数数组 positions ，其中 positions[i] = [lefti, sideLengthi]
 * 表示：第 i 个方块边长为 sideLengthi ，其左侧边与 x 轴上坐标点 lefti 对齐。
 * 每个方块都从一个比目前所有的落地方块更高的高度掉落而下。方块沿 y 轴负方向下落，直到着陆到另一个正方形的顶边或者是 x 轴上。
 * 一个方块仅仅是擦过另一个方块的左侧边或右侧边不算着陆。一旦着陆，它就会固定在原地，无法移动。
 * 在每个方块掉落后，你必须记录目前所有已经落稳的 方块堆叠的最高高度 。
 * 返回一个整数数组 ans ，其中 ans[i] 表示在第 i 块方块掉落后堆叠的最高高度。
 *
 * @author xiahaifeng
 */

public class Solution699 {
    public static class HeightInfo implements Comparable<HeightInfo> {
        private int start;
        private int end;
        private int height;

        public HeightInfo(int start, int end, int height) {
            this.start = start;
            this.end = end;
            this.height = height;
        }

        public HeightInfo() {
        }

        @Override
        public int compareTo(HeightInfo o) {
            return this.start - o.start;
        }
    }

    private final List<HeightInfo> list = new ArrayList<>();

    public void add(HeightInfo heightInfo) {
        int index = Collections.binarySearch(list, heightInfo);
        if (index < 0) {
            index = -index - 1;
        } else {
            throw new IllegalArgumentException(" heightInfo is already exist");
        }
        list.add(index, heightInfo);
    }

    public List<Integer> fallingSquares(int[][] positions) {
        List<Integer> result = new ArrayList<>();
        List<Integer> deleteIndex = new ArrayList<>();
        List<HeightInfo> addList = new ArrayList<>();
        int maxHeight = 0;
        for (int[] position : positions) {
            int left = position[0];
            int right = left + position[1] - 1;
            int height = position[1];
            int startHeight = 0;
            int lastStartIndex = findLastStart(left);
            for (int i = lastStartIndex; i < list.size(); i++) {
                HeightInfo heightInfo = list.get(i);
                if (heightInfo.start > right) {
                    break;
                }
                // 如果有交集
                if (heightInfo.end < left) {
                    continue;
                }
                startHeight = Math.max(startHeight, heightInfo.height);
            }
            int endHeight = startHeight + height;
            deleteIndex.clear();
            addList.clear();
            for (int i = lastStartIndex; i < list.size(); i++) {
                HeightInfo heightInfo = list.get(i);
                if (heightInfo.start > right) {
                    break;
                }
                if (heightInfo.end < left) {
                    continue;
                }
                if (left <= heightInfo.start && heightInfo.end <= right) {
                    // 这个区间会被删掉
                    deleteIndex.add(i);
                } else if (heightInfo.start < left && right < heightInfo.end) {
                    // 原来的区间被分成了三段
                    addList.add(new HeightInfo(right + 1, heightInfo.end, heightInfo.height));
                    heightInfo.end = left - 1;
                } else if (left <= heightInfo.start) {
                    // 原来的区间被分成了两段
                    heightInfo.start = right + 1;
                } else {
                    // 原来的区间被分成了两段
                    heightInfo.end = left - 1;
                }
            }
            addList.add(new HeightInfo(left, right, endHeight));
            for (int i = deleteIndex.size() - 1; i >= 0; i--) {
                list.remove((int) deleteIndex.get(i));
            }
            for (HeightInfo heightInfo : addList) {
                add(heightInfo);
            }
            maxHeight = Math.max(maxHeight, endHeight);
            result.add(maxHeight);
        }
        return result;
    }

    // 找到最后一个start 不大于left的值。
    private HeightInfo lastStart = new HeightInfo();

    public int findLastStart(int left) {
        lastStart.start = left;
        int index = Collections.binarySearch(list, lastStart);
        if (index >= 0) {
            return index;
        }
        index = -index - 2;
        return Math.max(index, 0);
    }


    public static void main(String[] args) {
        // [[33,34],[47,62],[70,16],[90,79],[73,86],[55,6],[74,2],[40,95],[52,16],[50,33]]
        int[][] positions =  {{33, 34}, {47, 62}, {70, 16}, {90, 79}, {73, 86}, {55, 6}, {74, 2}, {40, 95}, {52, 16}, {50, 33}};
        Solution699 solution699 = new Solution699();
        List<Integer> res = solution699.fallingSquares(positions);
        System.out.println(res);
    }
}