package com.LoveSea.fengCore.commons.baseConversion;

import junit.framework.Assert;
import org.junit.jupiter.api.Test;

/**
 * @author xiahaifeng
 */

public class BaseNumConverterTest {

    @Test
    public void testConvert() {
        // 二进制
        String[] base2 = new String[]{"0", "1"};
        BaseX baseX2 = new BaseX(base2);
        // 十进制
        String[] base10 = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        BaseX baseX10 = new BaseX(base10);
        // 八进制
        String[] base8 = new String[]{"0", "1", "2", "3", "4", "5", "6", "7"};
        BaseX baseX8 = new BaseX(base8);
        // 十六进制
        String[] base16 = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};
        BaseX baseX16 = new BaseX(base16);
        String[] binaryNumbers = new String[]{"0", "1", "10", "11", "100", "101", "110", "111", "1000", "1001", "1010",
                "1011", "1100", "1101", "1110", "1111"};
        String[] decimalNumbers = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12",
                "13", "14", "15"};
        String[] x8Numbers = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "10", "11", "12", "13", "14", "15", "16",
                "17"};
        String[] x16Numbers = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E",
                "F"};
        BaseNumConverter x2To10 = baseX2.toBaseX(baseX10);
        for (int i = 0; i < binaryNumbers.length; i++) {
            String binaryNumber = binaryNumbers[i];
            String decimalNumber = x2To10.convert(binaryNumber);
            Assert.assertEquals(decimalNumbers[i], decimalNumber);
        }
        BaseNumConverter x10To2 = baseX10.toBaseX(baseX2);
        for ( int i = 0; i < decimalNumbers.length; i++) {
            String decimalNumber = decimalNumbers[i];
            String binaryNumber = x10To2.convert(decimalNumber);
            Assert.assertEquals(binaryNumbers[i], binaryNumber);
        }
        BaseNumConverter x2To8 = baseX2.toBaseX(baseX8);
        for (int i = 0; i < binaryNumbers.length; i++) {
            String binaryNumber = binaryNumbers[i];
            String x8Number = x2To8.convert(binaryNumber);
            Assert.assertEquals(x8Numbers[i], x8Number);
        }
        BaseNumConverter x8To2 = baseX8.toBaseX(baseX2);
        for (int i = 0; i < x8Numbers.length; i++) {
            String x8Number = x8Numbers[i];
            String binaryNumber = x8To2.convert(x8Number);
            Assert.assertEquals(binaryNumbers[i], binaryNumber);
        }
        BaseNumConverter x16To8 = baseX16.toBaseX(baseX8);
        for (int i = 0; i < x16Numbers.length; i++) {
            String x16Number = x16Numbers[i];
            String x8Number = x16To8.convert(x16Number);
            Assert.assertEquals(x8Numbers[i], x8Number);
        }
    }
}