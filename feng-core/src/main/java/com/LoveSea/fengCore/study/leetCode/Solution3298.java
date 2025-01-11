package com.LoveSea.fengCore.study.leetCode;

/**
 * 3298. 统计重新排列后包含另一个字符串的子字符串数目 II
 * 给你两个字符串 word1 和 word2 。
 * 如果一个字符串 x 重新排列后，word2 是重排字符串的前缀，那么我们称字符串 x 是 合法的 。请你返回 word1 中 合法子字符串的数目。
 * 注意 ，这个问题中的内存限制比其他题目要 小 ，所以你 必须 实现一个线性复杂度的解法。
 *
 * @author xiahaifeng
 */

public class Solution3298 {
    public long validSubstringCount(String word1, String word2) {
        if (word1.length() < word2.length()) {
            return 0;
        }
        int[] count = new int[26];
        for (char c : word2.toCharArray()) {
            count[c - 'a']++;
        }
        int less = 0;
        for (int i = 0; i < 26; i++) {
            if (count[i] > 0) {
                less++;
            }
        }
        long result = 0;
        int start = 0;
        char[] word1Chars = word1.toCharArray();
        for (char c : word1Chars) {
            count[c - 'a']--;
            if (count[c - 'a'] == 0) {
                less--;
            }
            while (less == 0) {
                int index = word1Chars[start++] - 'a';
                if (count[index] == 0) {
                    less++;
                }
                count[index]++;
            }
            result += start;
        }
        return result;
    }
}