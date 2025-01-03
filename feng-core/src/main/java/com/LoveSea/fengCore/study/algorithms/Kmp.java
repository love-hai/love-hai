package com.LoveSea.fengCore.study.algorithms;

/**
 * @author xiahaifeng
 */

public class Kmp {
    private int[] get_next(String pattern) {
        int[] next = new int[pattern.length()];
        int[] prefix = get_prefix(pattern);
        next[0] = -1;
        System.arraycopy(prefix, 0, next, 1, pattern.length() - 1);
        return next;
    }

    // abcababcabc

    private int[] get_prefix(String pattern) {
        char[] p = pattern.toCharArray();
        int[] prefix = new int[pattern.length()];
        int j = 0;
        for (int i = 1; i < pattern.length(); i++) {
            while (j > 0 && p[i] != p[j]) {
                // 最难理解就是这个地方。
                // 如何比对到第i个字符和第j个字符不相等时。
                // 看看j前面一位的前缀表的值，的下一位的值是否和i相等。
                // abcababcabc
                //      abcababcabc
                // 当a 和 c 不相等时，看看a 前面一个字符的b的前缀表的值的下一位是否和c相等。
                j = prefix[j - 1];
            }
            if (p[i] == p[j]) {
                j++;
            }
            prefix[i] = j;
        }
        return prefix;
    }

    public int kmp(String s, String pattern) {
        char[] str = s.toCharArray();
        char[] p = pattern.toCharArray();
        int[] next = get_next(pattern);
        int j = 0;
        for (int i = 0; i < s.length(); i++) {
            while (j > 0 && str[i] != p[j]) {
                j = next[j];
            }
            if (str[i] == p[j]) {
                j++;
            }
            if (j == pattern.length()) {
                return i - pattern.length() + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        String s = "ababcabcabababc";
        String pattern = "abcab";
        Kmp kmp = new Kmp();
        System.out.println(kmp.kmp(s, pattern));
    }
}