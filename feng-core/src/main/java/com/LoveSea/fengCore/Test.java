package com.LoveSea.fengCore;

import lombok.extern.slf4j.Slf4j;

/**
 * @author xiahaifeng
 */
@Slf4j
public class Test {
    public static void main(String[] args) {
        String[] arr = {"aaa", "bbb", "ccc"};
        for (String s : arr) {
            log.info(s);
        }
        String a  = "aaa";
        String b = "bbb";
        String c = "ccc";

        String[] arr1 = new String[]{"aaa", "bbb", "ccc"};
        for (String s : arr1) {
            log.info(s);
        }
    }
}
