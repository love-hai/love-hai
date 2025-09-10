package com.lovehai.leetcode._1801_1900;

/**
 * 如果所给格子的颜色是白色，请你返回 true，如果是黑色，请返回 false 。
 *
 * @author xiahaifeng
 */

public class Solution1812 {

    /*
      给定坐标一定代表国际象棋棋盘上一个存在的格子。坐标第一个字符是字母，第二个字符是数字。判断国际象棋棋盘中一个格子的颜色
      a1 为黑色
      a2 为白色
      b1 为白色
      b2 为黑色
      黑色返回false，白色返回true
     */
    public boolean squareIsWhite(String coordinates) {
        char x = coordinates.charAt(0);
        char y = coordinates.charAt(1);
        int temp = (x - 'a' + y - '1');
        // temp 为偶数时，返回就是黑色false，为奇数时，返回就是白色true
        return (temp & 1) ==1;
    }
}