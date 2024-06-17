package com.LoveSea.fengCore.study.leetCode;

/**
 * @author xiahaifeng
 */

import java.util.*;

/**
 * 给定字符串列表 strs ，返回其中 最长的特殊序列 的长度。如果最长特殊序列不存在，返回 -1 。
 * 特殊序列 定义如下：该序列为某字符串 独有的子序列（即不能是其他字符串的子序列）。
 *  s 的 子序列可以通过删去字符串 s 中的某些字符实现。
 * 例如，"abc" 是 "aebdc" 的子序列，因为您可以删除"aebdc"中的下划线字符来得到 "abc" 。"aebdc"的子序列还包括"aebdc"、 "aeb" 和 "" (空字符串)。
 */
public class Solution522 {
    public int findLUSlength(String[] strs) {
        Set<String> noConformSet = new HashSet<>();
        Set<String> conformSet = new HashSet<>();
        for (String str : strs) {
            if(noConformSet.contains(str)){
                continue;
            }
            if (conformSet.contains(str)) {
                conformSet.remove(str);
                noConformSet.add(str);
            }else {
                conformSet.add(str);
            }
        }
        List<String> conformList = new ArrayList<>(conformSet);
        conformList.sort((o1, o2) -> o2.length() - o1.length());
        for (String s : conformList) {
            boolean flag = true;
            char[] chars = s.toCharArray();
            int sLength = s.length();
            for (String s1 : noConformSet) {
                if(s.length() >= s1.length()) {
                    continue;
                }
                if(s1.contains(s)){
                    flag = false;
                    break;
                }

                char[] chars1 = s1.toCharArray();
                int j = 0;
                for (char c : chars1) {
                    if(sLength == j){
                        break;
                    }
                    if (chars[j] == c) {
                        j++;
                    }
                }
                if (sLength == j) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return s.length();
            }
            noConformSet.add(s);
        }
        return -1;
    }

    public static void main(String[] args) {
        Solution522 solution522 = new Solution522();
        // ["abcabc","abcabc","abcabc","abc","abc","bac"]
        String[] strs = new String[]{"abcabc","abcabc","abcabc","abc","abc","bac"};
        System.out.println(solution522.findLUSlength(strs));
    }
}