package com.lovehai.leetcode._2101_2200;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author xiahaifeng
 */

public class Solution2197 {
    public List<Integer> replaceNonCoprimes(int[] nums) {
        List<Integer> answer = new ArrayList<>();
        int last = 1;
        for (int current : nums) {
            while (true) {
                if (1 == last || current == 1) {
                    answer.add(current);
                    break;
                }
                if (last == current) {
                    break;
                }
                int gcd = gcd(last, current);
                if (gcd == 1) {
                    answer.add(current);
                    break;
                }
                last = lcm(last, current, gcd);
                current = last;
                answer.set(answer.size() - 1, last);
                if(answer.size() < 2){
                    break;
                }
                last = answer.get(answer.size() - 2);
                current = answer.remove(answer.size() - 1);
            }
            last = current;
        }
        return answer;
    }

    private int lcm(int a, int b, int gcd) {
        return a / gcd * b;
    }

    private int gcd(int a, int b) {
        if (a < b) {
            return gcd(b, a);
        }
        while (b != 0) {
            int tmp = a % b;
            a = b;
            b = tmp;
        }
        return a;
    }
}