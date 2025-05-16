package com.LoveSea.fengCore.commons.utils.baseConversion;

import java.util.List;

/**
 * @author xiahaifeng
 */

public class CommonBaseNumIteratorCreator implements BaseNumIteratorCreator {
    public CommonBaseNumIteratorCreator() {
    }

    @Override
    public Iterable<String> baseNumber(String baseNumber) {
        return List.of(baseNumber.split(""));
    }
}