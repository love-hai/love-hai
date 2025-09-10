package com.lovehai.leetcode._3101_3200;

import java.util.Arrays;

/**
 * 同位字符串连接的最小长度
 *
 * @author xiahaifeng
 */

public class Solution3138 {
    public int minAnagramLength(String s) {
        int[] count = new int[26];
        char[] charArray = s.toCharArray();
        int length = s.length();
        for (char c : charArray) {
            count[c - 'a']++;
        }
        // 找最大公因数,最多可以分成这么多组
        int maxGcd = 0;
        for (int i : count) {
            if (i != 0) {
                maxGcd = 0 == maxGcd ? i : gcd(maxGcd, i);
            }
        }
        // 如果最多只能分成一组,直接返回
        if (maxGcd == 1) {
            return length;
        }
        int minSize = length / maxGcd;
        int[][] counts = new int[2][26];
        counts[0] = count;
        counts[1] = new int[26];
        for (int size = minSize; size <= length / 2; size++) {
            if (length % size != 0) {
                continue;
            }
            // 按照 res 分割
            int countIndex = 1;
            int offset = 0;
            boolean isSame = true;
            while (offset < length) {
                countIndex = (countIndex + 1) % 2;
                countSplit(charArray, counts[countIndex], offset, size);
                offset += size;
                if (offset == size) {
                    continue;
                }
                if (!Arrays.equals(counts[0], counts[1])) {
                    isSame = false;
                    break;
                }
            }
            if (isSame) {
                return size;
            }
        }
        return s.length();
    }
    // 统计offset - offset+size的字符数组
    void countSplit(char[] s, int[] count1, int offset, int size) {
        Arrays.fill(count1, 0);
        int endIndex = offset + size;
        for (int i = offset; i < endIndex; i++) {
            count1[s[i] - 'a']++;
        }
    }
    // 求最大公因数
    int gcd(int a, int b) {
        if (1 == a || 1 == b) {
            return 1;
        }
        int min = Math.min(a, b);
        for (int i = min; i > 1; i--) {
            if (a % i == 0 && b % i == 0) {
                return i;
            }
        }
        return 1;
    }
    public static void main(String[] args) {
        Solution3138 solution3138 = new Solution3138();
        System.out.println(solution3138.minAnagramLength("aabbabab"));
    }
}