package com.LoveSea.fengCore.study.gfg;

/**
 * @author xiahaifeng
 */

public class Solution179 {

    /**
     * s 是小写字母的字符串，每次操作删除三个相同的字符（不需要连续），返回使用
     * 字符串 s 缩短至最短的次数。
     */
    public static int getOperations(String s) {
        int[] charCount = new int[26];
        int ans = 0;
        for (char c : s.toCharArray()) {
            int index = c - 'a';
            charCount[index]++;
            if (charCount[index] == 3) {
                charCount[index] = 0;
                ans++;
            }
        }
        return ans;
    }

    /**
     * minXOR : 您将获得一个由多个元素组成的整数数组 arr[] 。您还将获得一个整数 k ，表示可以执行的最大操作数
     * 从数组中选取任意数字。
     * 取消设置其任何设置位（即，将其二进制表示中的 1 更改为 0）
     * 操作以最小化整个数组的 XOR 值。数组的 XOR 值定义为将数组的所有元素异或的结果。<br>
     *
     * @author xiahaifeng
     */
    public static int minXOR(int n, int k, int[] arr) {






        return 0;

    }
}