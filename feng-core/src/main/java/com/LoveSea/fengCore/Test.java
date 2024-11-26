package com.LoveSea.fengCore;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * @author xiahaifeng
 */
@Slf4j
public class Test {
    public static void main(String[] args) throws Exception {

        long   sequenceMask =-1L << 12;
        log.info("sequenceMask:{}", sequenceMask);
        log.info("sequenceMask:{}",Long.toBinaryString(sequenceMask));
        sequenceMask = ~sequenceMask;
        log.info("sequenceMask:{}", sequenceMask);
        log.info("sequenceMask:{}",Long.toBinaryString(sequenceMask));
    }

}
