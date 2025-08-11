package com.lovehai.leetcode._2501_2600;

/**
 * 2595. 奇偶位数
 * 给你一个 正 整数 n 。
 * 用 even 表示在 n 的二进制形式（下标从 0 开始）中值为 1 的偶数下标的个数。
 * 用 odd 表示在 n 的二进制形式（下标从 0 开始）中值为 1 的奇数下标的个数。
 * 请注意，在数字的二进制表示中，位下标的顺序 从右到左。
 * 返回整数数组 answer ，其中 answer = [even, odd] 。
 *
 * @author xiahaifeng
 */

public class Solution2595 {
    public int[] evenOddBit(int n) {
        int even = 0, odd = 0;
        char[] chars = Integer.toBinaryString(n).toCharArray();
        boolean isEven = ((chars.length - 1) & 1) == 0;
        for (char aChar : chars) {
            if (aChar == '1') {
                if (isEven) {
                    even++;
                } else {
                    odd++;
                }
            }
            isEven = !isEven;
        }
        return new int[]{even, odd};
    }
}