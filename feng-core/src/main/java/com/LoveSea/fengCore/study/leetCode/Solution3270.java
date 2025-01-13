package com.LoveSea.fengCore.study.leetCode;

/**
 * 3270. 求出数字答案
 * 给你三个 正 整数 num1 ，num2 和 num3 。
 * 数字 num1 ，num2 和 num3 的数字答案 key 是一个四位数，定义如下：
 * <ul>
 * <li> 一开始，如果有数字少于四位数，给它补前导 0 。</li>
 * <li>答案 key 的第 i 个数位（1 <= i <= 4）为 num1 ，num2 和 num3 第 i 个数位中的 最小 值。</li>
 * </ul>
 * 请你返回三个数字 没有前导0的数字答案。
 *
 * @author xiahaifeng
 */

public class Solution3270 {
    public int generateKey(int num1, int num2, int num3) {
        int result = 0;
        for (int i = 3; i >= 0; i--) {
            int divisor = pow(10, i);
            int digit = num1 / divisor;
            num1 %= divisor;
            digit = Math.min(digit,  num2 / divisor);
            num2 %= divisor;
            digit = Math.min(digit, num3 / divisor);
            num3 %= divisor;
            result = result * 10 + digit;
        }
        return result;
    }
    private int pow(int i, int i2) {
        if(i2 == 0) {
            return 1;
        }
        int result = i;
        for (int j = 0; j < i2 - 1; j++) {
            result *= i;
        }
        return result;
    }
    public static void main(String[] args) {
        Solution3270 solution3270 = new Solution3270();
        System.out.println(solution3270.generateKey(1, 10, 1000));
    }



}