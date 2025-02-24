package com.loveSea.designPattern.prototypePattern;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

/**
 * @author xiahaifeng
 */
@Slf4j
public class Main {
    public static void main(String[] args) {
        long startTime;
        long endTime;
        long count = 1000000000L;
        BigDecimal we = BigDecimal.ZERO;



        startTime = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            Sku sku = new Sku();
            sku.setWeight(we);
            sku.setCostPrice(we);
        }
        endTime = System.currentTimeMillis();
        log.info(String.valueOf(endTime - startTime));

/*        startTime = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            Sku sku = Sku.newInstance();
            sku.setWeight(we);
            sku.setCostPrice(we);
        }
        endTime = System.currentTimeMillis();
        log.info(String.valueOf(endTime - startTime));*/

    }
}