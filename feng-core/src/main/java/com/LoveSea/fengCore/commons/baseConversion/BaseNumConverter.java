package com.LoveSea.fengCore.commons.baseConversion;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xiahaifeng
 */

public class BaseNumConverter {
    private final BaseX fromBase;
    private final BaseX toBase;

    protected BaseNumConverter(BaseX fromBase, BaseX toBase) {
        this.fromBase = fromBase;
        this.toBase = toBase;
    }

    public String convert(String baseNumber) {
        // check base validity
        if (!fromBase.isValid(baseNumber)) {
            String errorMessage = "baseNumber [%s] is not valid for baseX [%s]".formatted(baseNumber, fromBase.getName());
            throw new IllegalArgumentException(errorMessage);
        }
        // 将输入的数字转换为 toBase 的 字符串表示
        // 需要通过 fromBase 转换为十进制数字
//        int bigIntegerRadix = 10;
        BigInteger bigInteger = BigInteger.valueOf(0);
        int fromX = fromBase.getX();
        BigInteger fromxBigInteger = BigInteger.valueOf(fromX);
        for (int charsIndex : fromBase.baseNumCharsIndexList(baseNumber)) {
            // 计算十进制数字
            bigInteger = bigInteger.multiply(fromxBigInteger).add(BigInteger.valueOf(charsIndex));
        }
        List<Integer> toBaseCharsIndexList = new ArrayList<>();
        int toX = toBase.getX();
        BigInteger toXBigInteger = BigInteger.valueOf(toX);
        do {
            // 计算 toBase 的数字
            BigInteger[] divideAndRemainder = bigInteger.divideAndRemainder(toXBigInteger);
            toBaseCharsIndexList.add(0, divideAndRemainder[1].intValue());
            // 减一
            bigInteger = divideAndRemainder[0];
        } while (bigInteger.compareTo(BigInteger.ZERO) > 0);
        return toBase.baseNumIndexListToString(toBaseCharsIndexList);
    }
}