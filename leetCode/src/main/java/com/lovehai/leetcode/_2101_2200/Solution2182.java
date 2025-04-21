package com.lovehai.leetcode._2101_2200;

/**
 * @author xiahaifeng
 */

/**
 * 给你一个字符串 s 和一个整数 repeatLimit ，用 s 中的字符构造一个新字符串 repeatLimitedString ，使任何字母 连续 出现的次数都不超过 repeatLimit 次。你不必使用 s 中的全部字符。
 * 返回 字典序最大的 repeatLimitedString 。
 * 如果在字符串 a 和 b 不同的第一个位置，字符串 a 中的字母在字母表中出现时间比字符串 b 对应的字母晚，则认为字符串 a 比字符串 b 字典序更大 。如果字符串中前 min(a.length, b.length) 个字符都相同，那么较长的字符串字典序更大。
 */

public class Solution2182 {
    public String repeatLimitedString(String s, int repeatLimit) {
        int[] counts = new int['z' + 1];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            counts[c]++;
        }
        char[] chars = new char[s.length()];
        int i = 'z';
        int left = 'z';
        int cur = 'z'+1;
        int index = 0;
        while (i >= 'a') {
            if(counts[i]==0) {
                i--;
                continue;
            }
            if(i == cur) {
                i--;
                continue;
            }
            if(counts[left] == 0) {
                left =i;
            }
            if(i == left) {
                int limit = Math.min(repeatLimit, counts[i]);
                for (int j = 0; j < limit; j++) {
                    chars[index++] = (char)i;
                }
                counts[i] -= limit;
            }else {
                chars[index++] = (char)i;
                counts[i] --;
            }
            cur = i;
            i= left;
        }
        return new String(chars, 0, index);
    }

    public static void main(String[] args) {
        Solution2182 solution2182 = new Solution2182();
        System.out.println(solution2182.repeatLimitedString("cczazcc", 3));
    }
}