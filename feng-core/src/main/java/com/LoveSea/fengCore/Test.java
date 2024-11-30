package com.LoveSea.fengCore;

import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;

/**
 * @author xiahaifeng
 */
@Slf4j
public class Test {
    public static void main(String[] args) throws Exception {
        BigInteger.probablePrime(512, new java.util.Random());
    }

}
