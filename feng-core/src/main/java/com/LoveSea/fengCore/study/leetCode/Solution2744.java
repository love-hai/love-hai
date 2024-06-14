package com.LoveSea.fengCore.study.leetCode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

/**
 * @author xiahaifeng
 * @since 2024/6/14 22:57
 */

public class Solution2744 {
    public int maximumNumberOfStringPairs(String[] words) {
        Set<String> set = new HashSet<>(Arrays.asList(words));
//        Predicate<String> predicate = str -> str.charAt(0) != str.charAt(1) && set.contains("" + str.charAt(1) + str.charAt(0));
//        return (int) Arrays.stream(words).filter(predicate).count();
        int res = 0;
        // 字符串反转和原字符串相同的个数
        int same = 0;
        for (String word : words) {
            String sb = new StringBuilder(word).reverse().toString();
            if (set.contains(sb)) {
                res++;
            }
            if (word.equals(sb)) {
                same++;
            }
        }
        res -= same;
        return res / 2;
    }

    public static void main(String[] args) {
//        ["cd","ac","dc","ca","zz"]
        String[] words = {"cd", "ac", "dc", "ca", "zz"};
        Solution2744 solution2744 = new Solution2744();


    }

}