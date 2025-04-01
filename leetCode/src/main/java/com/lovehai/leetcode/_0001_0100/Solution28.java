package com.LoveSea.fengCore.study.leetCode;

/**
 * 28. 找出字符串中第一个匹配项的下标
 * 给你两个字符串 haystack 和 needle ， 请你在 haystack 字符串中找出 needle 字符串的第一个匹配项的下标（下标从 0 开始）。
 * 如果 needle 不是 haystack 的一部分，则返回  -1 。
 *
 * @author xiahaifeng
 */

public class Solution28 {
    public int strStr(String haystack, String needle) {
        // 再次复习一次KMP算法
        int needleLen = needle.length();
        // 前缀表 ，对应的位置记录着当前位置的前缀的最大长度
        int[] prefix = new int[needle.length()];
        char[] needleChars = needle.toCharArray();
        for (int left = 0, right = 1; right < needleLen; right++) {
            while (left > 0 && needleChars[left] != needleChars[right]) {
                left = prefix[left - 1];
            }
            if (needleChars[left] == needleChars[right]) {
                left++;
            }
            prefix[right] = left;
        }
        int[] next = new int[needleLen];
        next[0] = -1;
        System.arraycopy(prefix, 0, next, 1, needleLen - 1);
        int j = 0;
        char[] haystackChar = haystack.toCharArray();
        for (int i = 0; i < haystackChar.length; i++) {
            while (j > 0 && haystackChar[i] != needleChars[j]) {
                j = next[j];
            }
            if (haystackChar[i] == needleChars[j]) {
                j++;
            }
            if (j == needleLen) {
                return i - needleLen + 1;
            }
        }
        return -1;
    }
}