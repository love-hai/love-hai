package com.lovehai.leetcode._2201_2300;

/**
 * @author xiahaifeng
 * 2288.价格减免
 */
/*
句子 是由若干个单词组成的字符串，单词之间用单个空格分隔，其中每个单词可以包含数字、小写字母、和美元符号 '$' 。
如果单词的形式为美元符号后跟着一个非负实数，那么这个单词就表示一个 价格 。
例如 "$100"、"$23" 和 "$6" 表示价格，而 "100"、"$" 和 "$1e5 不是。
给你一个字符串 sentence 表示一个句子和一个整数 discount 。对于每个表示价格的单词，
都在价格的基础上减免 discount% ，并 更新 该单词到句子中。
所有更新后的价格应该表示为一个 恰好保留小数点后两位 的数字。
返回表示修改后句子的字符串。
注意：所有价格 最多 为  10 位数字。
 */

public class Solution2288 {
    public String discountPrices(String sentence, int discount) {
        sentence = sentence + " ";
        StringBuilder sb = new StringBuilder();
        boolean isPrice = false;
        boolean beforeIsNum = true;
        double discountNum = 1 - discount / 100.0;
        StringBuilder price = new StringBuilder();
        for (int i = 0; i < sentence.length(); i++) {
            char c = sentence.charAt(i);
            if (c == '$' && !isPrice && beforeIsNum) {
                isPrice = true;
                beforeIsNum = false;
                price.delete(0, price.length());
                continue;
            }
            if (isPrice) {
                if (!(c >= '0' && c <= '9')) {
                    isPrice = false;
                    sb.append('$');
                    if (!price.isEmpty() && c == ' ') {
                        double p = Double.parseDouble(price.toString());
                        p = p * discountNum;
                        sb.append(String.format("%.2f", p));
                    } else {
                        sb.append(price);
                    }
                    sb.append(c);
                } else {
                    price.append(c);
                }
            } else {
                sb.append(c);
            }
            beforeIsNum = c == ' ';
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "there are $1 $2 and 5$ candies in the shop";
        int discount = 50;
        Solution2288 solution2288 = new Solution2288();
        System.out.println(solution2288.discountPrices(s, discount));
    }
}