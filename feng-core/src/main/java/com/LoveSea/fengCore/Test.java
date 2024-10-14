package com.LoveSea.fengCore;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xiahaifeng
 */
@Slf4j
public class Test {
    public static void main(String[] args) {
        int a = -5;
        //打印 a 的二进制
        log.info(Integer.toBinaryString(a));
        int b = a >>> 2;
        //打印 b 的二进制
        log.info(Integer.toBinaryString(b));
    }
}
