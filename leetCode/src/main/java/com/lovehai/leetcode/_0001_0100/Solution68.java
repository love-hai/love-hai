package com.LoveSea.fengCore.study.leetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiahaifeng
 */

public class Solution68 {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        int left = 0;
        int right;
        int remainLen = maxWidth;
        for (right = 0; right < words.length; right++) {
            String word = words[right];
            int len = word.length();
            if (remainLen >= len) {
                remainLen -= len;
                remainLen--;
            } else {
                // 不够放
                remainLen++;
                result.add(this.getLine(left, right, words, remainLen));
                left = right;
                remainLen = maxWidth;
                right--;
            }
        }
        result.add(this.getLastLine(left, right, words, maxWidth));
        return result;
    }

    private String getLine(int left, int right, String[] words, int remainLen) {
        // 剩余的remainLen 空格数将会分配到left和right之间的空格上
        StringBuilder sb = new StringBuilder();
        int count = right - left;
        if (count == 1) {
            sb.append(words[left]);
            sb.append(" ".repeat(remainLen));
            return sb.toString();
        }
        int spaceCount = count - 1;
        // 计算出每个空格的数量
        int intervalSpace = remainLen / spaceCount + 1;
        int remainSpace = remainLen % spaceCount;
        for (int i = 0; i < count - 1; i++) {
            sb.append(words[i + left]);
            if (i < remainSpace) {
                sb.append(" ".repeat(intervalSpace + 1));
            } else {
                sb.append(" ".repeat(intervalSpace));
            }
        }
        sb.append(words[right - 1]);
        return sb.toString();
    }

    private String getLastLine(int left, int right, String[] words, int maxWidth) {
        // 剩余的remainLen 空格数将会分配到left和right之间的空格上
        StringBuilder sb = new StringBuilder();
        for (; left < right; left++) {
            sb.append(words[left]);
            sb.append(" ");
        }
        if (sb.length() > maxWidth) {
            return sb.substring(0, maxWidth);
        } else if (sb.length() < maxWidth) {
            sb.append(" ".repeat(maxWidth - sb.length()));
        }
        return sb.toString();
    }
}