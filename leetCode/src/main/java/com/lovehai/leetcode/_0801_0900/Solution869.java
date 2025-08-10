package com.lovehai.leetcode._0801_0900;

import java.util.*;

/**
 * 给定正整数 n ，我们按任何顺序（包括原始顺序）将数字重新排序，注意其前导数字不能为零。
 * 如果我们可以通过上述方式得到 2 的幂，返回 true；否则，返回 false。
 *
 * @author xiahaifeng
 */

public class Solution869 {
    static Map<Integer, List<String>> map = new HashMap<>();

    static {
        for (int i = 1; i <= 1000000000; i *= 2) {
            char[] chars = String.valueOf(i).toCharArray();
            Arrays.sort(chars);
            map.computeIfAbsent(chars.length, k -> new ArrayList<>()).add(new String(chars));
        }
    }

    public boolean reorderedPowerOf2(int n) {
        char[] chars = String.valueOf(n).toCharArray();
        Arrays.sort(chars);
        String sorted = new String(chars);
        List<String> list = map.get(chars.length);
        if (null == list) {
            return false;
        }
        for (String s1 : list) {
            if (s1.equals(sorted)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Solution869 solution869 = new Solution869();
        System.out.println(solution869.reorderedPowerOf2(1));
    }
}