/*
 * The MIT License (MIT)
 * Copyright © 2025 love-hai <xiahaifeng2000@gmail.com>
 * See the LICENSE file for details.
 */
package com.LoveSea.fengCore;

import lombok.extern.slf4j.Slf4j;

/**
 * @author xiahaifeng
 */
@Slf4j
public class Test {

    public static void main(String[] args) {

    }
    public int nextBeautifulNumber(int n) {
        // 找到 x 位的最大一个漂亮数
        int x = String.valueOf(n).length();
        int res = 0;
        for (int i = 1; i <= x; i++) {
            res = res * 10 + x;
        }
        if(res <= n){
            // 找到 x + 1 位的最小的漂亮数
            res = 1;
            for (int i = 1; i <= x; i++) {
                res = res * 10 + x;
            }
            return res;
        }else {
            int lastRes = res;
            while (true){
                res =  0;

            }
        }
    }
}
