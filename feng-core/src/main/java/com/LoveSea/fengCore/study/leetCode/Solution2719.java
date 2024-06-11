package com.LoveSea.fengCore.study.leetCode;

/**
 * @author xiahaifeng
 * 给你两个数字字符串 num1 和 num2 ，以及两个整数 max_sum 和 min_sum 。如果一个整数 x 满足以下条件，我们称它是一个好整数：
 * num1 <= x <= num2
 * min_sum <= digit_sum(x) <= max_sum.
 * 请你返回好整数的数目。答案可能很大，请返回答案对 109 + 7 取余后的结果。
 * 注意，digit_sum(x) 表示 x 各位数字之和。
 */

public class Solution2719 {
    private int min_sum;
    private int max_sum;
    public int count(String num1, String num2, int min_sum, int max_sum) {

    }

    private int dfs(int pos,int sum,boolean limit){
        if(sum>max_sum){
            return 0;
        }
        if(-1==pos){
            return sum>=min_sum?1:0;
        }

    }
}