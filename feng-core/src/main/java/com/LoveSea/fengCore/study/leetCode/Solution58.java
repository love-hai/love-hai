package com.LoveSea.fengCore.study.leetCode;

/**
 * 58. 最后一个单词的长度
 * 给你一个字符串 s，由若干单词组成，单词前后用一些空格字符隔开。返回字符串中 最后一个单词的长度。
 * 单词是指仅由字母组成、不包含任何空格字符的最大子字符串
 * @author xiahaifeng
 */

public class Solution58 {
    public int lengthOfLastWord(String s) {
        char[] chars = s.toCharArray();
        int result = 0;
        for (int i = chars.length - 1; i >= 0; i--) {
            if (' ' == chars[i]) {
                if (result == 0) {
                    continue;
                } else {
                    break;
                }
            }
            result++;
        }
        return result;
    }
}