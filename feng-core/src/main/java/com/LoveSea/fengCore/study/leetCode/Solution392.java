package com.LoveSea.fengCore.study.leetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 392. 判断子序列
 * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
 * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。
 * （例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
 * 进阶：
 * 如果有大量输入的 S，称作 S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。
 * 在这种情况下，你会怎样改变代码？
 *
 * @author xiahaifeng
 */

public class Solution392 {
    public boolean isSubsequence(String s, String t) {
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();
        int i = 0;
        int j = 0;
        while (i < sChars.length && j < tChars.length) {
            if (sChars[i] == tChars[j]) {
                i++;
            }
            j++;
        }
        return i == sChars.length;
    }

    public boolean isSubsequencePlus(String s, String t) {
        Map<Character, List<Integer>> map = new HashMap<>();
        char[] tChars = t.toCharArray();
        for (int i = 0; i < tChars.length; i++) {
            List<Integer> list = map.computeIfAbsent(tChars[i], k -> new ArrayList<>());
            list.add(i);
        }
        char[] sChars = s.toCharArray();
        int last = -1;
        boolean flag;
        for (char sChar : sChars) {
            List<Integer> list = map.get(sChar);
            flag = false;
            if (list == null) {
                return false;
            }
            for (int index : list) {
                if (index > last) {
                    last = index;
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                return false;
            }
        }
        return true;
    }
}