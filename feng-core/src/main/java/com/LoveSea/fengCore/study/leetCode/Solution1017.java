package com.LoveSea.fengCore.study.leetCode;

/**
 * @author xiahaifeng
 */
/*
给你一个整数 n ，以二进制字符串的形式返回该整数的 负二进制（base -2）表示。

注意，除非字符串就是 "0"，否则返回的字符串中不能含有前导零。
 */

public class Solution1017 {
    public String baseNeg2(int n) {
        if (n == 0) {
            return "0";
        }
        StringBuilder res = new StringBuilder();
        while (n != 0) {
            int remainder = n % -2;
            n /= -2;
            if (remainder < 0) {
                remainder = 1;
                n++;
            }
            res.append(remainder);
        }
        return res.reverse().toString();
    }

    public static void main(String[] args) {
        int a = -9;
        System.out.println(a%-2);
        System.out.println(a/-2);

    }
}