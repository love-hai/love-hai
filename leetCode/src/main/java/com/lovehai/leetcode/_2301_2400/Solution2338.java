package com.lovehai.leetcode._2301_2400;

import java.util.ArrayList;
import java.util.List;

/**
 * 2338. 统计理想数组的数目
 * 给你两个整数 n 和 maxValue ，用于描述一个 理想数组 。
 * 对于下标从 0 开始、长度为 n 的整数数组 arr ，如果满足以下条件，则认为该数组是一个 理想数组 ：
 * 每个 arr[i] 都是从 1 到 maxValue 范围内的一个值，其中 0 <= i < n 。
 * 每个 arr[i] 都可以被 arr[i - 1] 整除，其中 0 < i < n 。
 * 返回长度为 n 的 不同 理想数组的数目。由于答案可能很大，返回对 109 + 7 取余的结果。
 * @author xiahaifeng
 */

public class Solution2338 {

    public int idealArrays(int n, int maxValue) {
        this.multipleNumbers = new ArrayList<>();
        this.maxValue = maxValue;

        return 0;
    }
    private List<Integer> multipleNumbers;
    private int maxValue;
    private void findMultipleNumber(int initV, int multipleNumber) {
        int multiple = 2;
        if(initV * multiple > maxValue) {
            this.multipleNumbers.add(multipleNumber);
            return;
        }
        int newV;
        multipleNumber++;
        multiple++;
        while ((newV =multiple * initV) <= maxValue) {
            this.findMultipleNumber(newV, multipleNumber);
        }
    }


}