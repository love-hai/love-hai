package com.LoveSea.fengCore.study.algorithms;

/**
 * @author xiahaifeng
 */

public class Kmp {
    // 我正在试图理解KMP算法。
    // 虽然我曾经已经理解了它，但是我现在已经忘记了。


    public static int[] get_next(String pattern) {
        // 初始值全部设置成0
        char[] p = pattern.toCharArray();
        int[] next = new int[pattern.length()];
        int j = 0;
        for (int i = 1; i < pattern.length() - 1; i++) {
            while (j > 0 && p[i] != p[j]) {
                j = next[j - 1] + 1;
            }
            if (p[i] == p[j]) {
                j++;
            }
            next[i + 1] = j + 1;
        }
        return next;
    }

    public static int kmp(String s, String pattern) {
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
        String pattern = "abc";
        int index = kmp(s, pattern);
        System.out.println(index);
    }


}