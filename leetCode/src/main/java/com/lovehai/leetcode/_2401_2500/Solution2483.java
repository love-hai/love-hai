package com.lovehai.leetcode._2401_2500;

/**
 *
 * @author xiahaifeng
 */

public class Solution2483 {
    public int bestClosingTime(String customers) {
        char[] arr = customers.toCharArray();
        int y_size = 0;
        for (char c : arr) {
            if (c == 'Y') {
                y_size++;
            }
        }
        int l_n = 0;
        int r_y = y_size;
        // 在0时的时候关门花费代价为cost
        // 为了从 0 开始遍历 ，在 0 时 为 y 时，代价减 1 ，为 n 时，代价加 1，所以我们预先加上 arr[0] 的代价
        int minCost = r_y + l_n;
        int ans = 0;
        for (int index = 0; index < arr.length; index++) {
            char c = arr[index];
            if (c == 'Y') {
                r_y--;
            } else {
                l_n++;
            }
            int cost = r_y + l_n;
            if (cost < minCost) {
                minCost = cost;
                ans = index + 1;
            }
        }
        return ans;
    }
}