package com.LoveSea.fengCore.study.leetCode;

/**
 * 给你两个字符串 word1 和 word2 。
 * 如果一个字符串 x 重新排列后，word2 是重排字符串的前缀，那么我们称字符串 x 是 合法的 。请你返回 word1 中 合法子字符串的数目。
 * @author xiahaifeng
 */

public class Solution3297 {
    public long validSubstringCount(String word1, String word2) {
        int[] word2Count = new int[26];
        for (int i = 0; i < word2.length(); i++) {
            word2Count[word2.charAt(i) - 'a']++;
        }
        long result = 0;
        int start = 0;
        int end = 0;
        boolean flag = false;
        int[] word1Count = new int[26];
        int word1CountLength = word1.length();
        while (end != word1CountLength || flag) {
            if (flag) {
                int index = word1.charAt(start) - 'a';
                word1Count[index]--;
                if (word1Count[index] < word2Count[index]) {
                    flag = false;
                }
                start++;
            } else {
                int index = word1.charAt(end) - 'a';
                word1Count[index]++;
                if (word1Count[index] == word2Count[index]) {
                    boolean isSame = true;
                    for (int i = 0; i < 26; i++) {
                        if (word1Count[i] < word2Count[i]) {
                            isSame = false;
                            break;
                        }
                    }
                    flag = isSame;
                }
                end++;
            }
            if (flag) {
                result += word1CountLength - end + 1;
            }
        }
        return result;
    }


    public static void main(String[] args) {
        Solution3297 solution3297 = new Solution3297();
        System.out.println(solution3297.validSubstringCount("bcca", "abc"));
    }
}