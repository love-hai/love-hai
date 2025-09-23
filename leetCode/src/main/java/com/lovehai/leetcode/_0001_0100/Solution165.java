package com.lovehai.leetcode._0001_0100;

/**
 *
 * @author xiahaifeng
 */

public class Solution165 {
    public int compareVersion(String version1, String version2) {
        char[] v1 = version1.toCharArray();
        char[] v2 = version2.toCharArray();
        int i = 0, j = 0;
        while (i < v1.length || j < v2.length) {
            int num1 = 0, num2 = 0;
            while (i < v1.length && v1[i] != '.') {
                num1 = num1 * 10 + v1[i] - '0';
                i++;
            }
            while (j < v2.length && v2[j] != '.') {
                num2 = num2 * 10 + v2[j] - '0';
                j++;
            }
            if (num1 > num2) {
                return 1;
            } else if (num1 < num2) {
                return -1;
            }else {
                i++;
                j++;
            }
        }
        return 0;
    }
}