package com.lovehai.leetcode._1901_2000;

/**
 *
 * @author xiahaifeng
 */

public class Solution1935 {
    public int canBeTypedWords(String text, String brokenLetters) {
        boolean[] broken = new boolean[26];
        for (char c : brokenLetters.toCharArray()) {
            broken[c - 'a'] = true;
        }
        int ans = 0;
        boolean flag = true;
        boolean hasWord = false;
        for (char c : text.toCharArray()) {
            if (' ' == c) {
                if (hasWord && flag) {
                    ans++;
                }
                flag = true;
                hasWord = false;
            } else if (broken[c - 'a']) {
                flag = false;
            } else {
                hasWord = true;
            }
        }
        if (hasWord && flag) {
            ans++;
        }
        return ans;
    }
}