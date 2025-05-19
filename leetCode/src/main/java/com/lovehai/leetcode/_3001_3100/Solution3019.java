package com.lovehai.leetcode._3001_3100;

/**
 * 给你一个下标从 0 开始的字符串 s ，该字符串由用户输入。按键变更的定义是：使用与上次使用的按键不同的键。
 * 例如 s = "ab" 表示按键变更一次，而 s = "bBBb" 不存在按键变更。
 * 返回用户输入过程中按键变更的次数。
 * 注意：shift 或 caps lock 等修饰键不计入按键变更，也就是说，如果用户先输入字母 'a' 然后输入字母 'A' ，不算作按键变更。
 *
 * @author xiahaifeng
 */

public class Solution3019 {
    public int countKeyChanges(String s) {
        char[] chars = s.toCharArray();
        int count = 0;
        char change = (char) ('A' - 'a');
        char last = chars[0] >= 'a' ? (char) (chars[0] + change) : chars[0];
        for (char c : chars) {
            if (c >= 'a') {
                c = (char) (c + change);
            }
            if (last != c) {
                count++;
            }
            last = c;
        }
        return count;
    }
}