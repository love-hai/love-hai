package com.lovehai.leetcode._0701_0800;

import java.util.HashMap;
import java.util.Map;

/**
 * 森林中有未知数量的兔子。提问其中若干只兔子 "还有多少只兔子与你（指被提问的兔子）颜色相同?"，
 * 将答案收集到一个整数数组 answers 中，其中 answers[i] 是第 i 只兔子的回答。
 * 给你数组 answers ，返回森林中兔子的最少数量。
 *
 * @author xiahaifeng
 */

public class Solution781 {
    public int numRabbits(int[] answers) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int answer : answers) {
            map.put(answer + 1, map.getOrDefault(answer+1, 0) + 1);
        }
        int ans = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int k = entry.getKey();
            int v = entry.getValue();
            ans += ((v - 1) / k + 1) * k;
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution781 solution781 = new Solution781();
        int[] answers = {1,0,1,0,0};
        int result = solution781.numRabbits(answers);
        System.out.println(result); // 输出结果
    }
}