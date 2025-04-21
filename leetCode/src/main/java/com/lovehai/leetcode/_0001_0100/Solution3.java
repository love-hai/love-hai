package com.lovehai.leetcode._0001_0100;

/**
 * @author xiahaifeng
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长 子串 的长度。
 */

class Solution3 {
    public int lengthOfLongestSubstring(String s) {
        if(s.isEmpty()){
            return 0;
        }
        char[] chars = s.toCharArray();
        int start = 0;
        int end = 1;
        int ans = 1;
        while (end < chars.length) {
            int idx = findCharAndIndex(chars, start, end - 1, chars[end]);
            if (-1 == idx) {
                ans = Math.max(ans, end - start + 1);

            } else {
                start = idx + 1;
            }
            end++;
        }
        return ans;
    }

    /**
     * findCharAndIndex :  从指定的开始位置到结束位置查找指定字符
     *
     * @param chars 查找的字符数组
     * @param start index开始
     * @param end   index结束
     * @param c     查找的字符
     */
    public int findCharAndIndex(char[] chars, int start, int end, char c) {
        for (int i = start; i <= end; i++) {
            if (chars[i] == c) {
                return i;
            }
        }
        return -1;
    }
}