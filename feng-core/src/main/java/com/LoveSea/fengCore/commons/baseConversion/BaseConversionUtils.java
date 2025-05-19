package com.LoveSea.fengCore.commons.baseConversion;

/**
 * @author xiahaifeng
 */

public class BaseConversionUtils {
    private final static String[] base10Chars = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    private final static BaseX baseX10 = new BaseX(base10Chars);

    private final static String[] base2Chars = new String[]{"0", "1"};
    private final static BaseX baseX2 = new BaseX(base2Chars);

    private final static String[] base16Chars = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};
    private final static BaseX baseX16 = new BaseX(base16Chars);


    public static Integer base10ToBase2(String base26) {
        String ans = baseX10.toBaseX(baseX2).convert(base26);
        return Integer.valueOf(ans);
    }

    public static String base2ToBase10(Integer base10) {
        return baseX10.fromBaseX(baseX2).convert(base10.toString());
    }
}