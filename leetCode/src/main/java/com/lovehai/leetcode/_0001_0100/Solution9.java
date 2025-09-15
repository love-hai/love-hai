package com.lovehai.leetcode._0001_0100;

/**
 *
 * @author xiahaifeng
 */

public class Solution9 {
    public boolean isPalindrome(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }
       int reverse = 0;
        while(x > reverse){
            reverse = reverse * 10 + x % 10;
            x /= 10;
        }
        return x == reverse || x == reverse / 10;
    }
}