package com.LoveSea.fengCore;

import lombok.extern.slf4j.Slf4j;

/**
 * @author xiahaifeng
 */
@Slf4j
public class Test {
    public static void main(String[] args) {
        int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int temp = arr[0];
        System.arraycopy(arr, 1, arr, 0, 9);
        arr[9] = temp;
        for (int i : arr) {
            log.info("{}", i);
        }
    }
}
