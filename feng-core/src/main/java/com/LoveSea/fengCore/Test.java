package com.LoveSea.fengCore;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author xiahaifeng
 */

public class Test {
    public static void main(String[] args) {
        BigDecimal bigDecimal = new BigDecimal("12.5382");
        BigDecimal bigDecimal2 = new BigDecimal(bigDecimal.toPlainString());
        BigDecimal bigDecimal1 = new BigDecimal("115.283");
        System.out.println(bigDecimal.divide(new BigDecimal("0.0003"),4,RoundingMode.HALF_UP).divide(bigDecimal1, 4, RoundingMode.HALF_UP).multiply(new BigDecimal("100"))
                .stripTrailingZeros().toPlainString().concat("%"));

    }
}
