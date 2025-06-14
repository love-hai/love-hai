package com.LoveSea.fengCore.commons.utils.excel;

import lombok.Getter;

@Getter
public enum ExcelCalculationEnum {
    automatic(-4015, "automatic", "自动计算"),
    manual(-4135, "manual", "手动计算"),
    semiautomatic(2, "semiautomatic", "半自动计算");
    public static final ExcelCalculationEnum defaultCal = automatic;

    private final int value;
    private final String code;
    private final String name;

    ExcelCalculationEnum(int value, String code, String name) {
        this.value = value;
        this.code = code;
        this.name = name;
    }
}
