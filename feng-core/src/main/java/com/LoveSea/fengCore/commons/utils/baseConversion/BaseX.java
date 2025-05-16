package com.LoveSea.fengCore.commons.utils.baseConversion;

import java.util.*;

/**
 * @author xiahaifeng
 */

public class BaseX {

    public String getName() {
        return _name;
    }

    private final String _name;
    // 进制字符集
    private final String[] baseChars;

    private final Map<String, Integer> baseCharIndexMap;

    private BaseNumIteratorCreator baseNumberIteratorCreator;

    private final int _x;

    public int getX() {
        return _x;
    }

    public BaseX(String[] baseChars, BaseNumIteratorCreator iteratorCreator) {
        this.baseChars = baseChars;
        this._x = baseChars.length;
        this._name = "Base-" + _x;
        this.baseNumberIteratorCreator = iteratorCreator;
        this.baseCharIndexMap = new HashMap<>();
        for (String baseChar : baseChars) {
            this.baseCharIndexMap.put(baseChar, baseCharIndexMap.size());
        }
        if (baseCharIndexMap.size() != baseChars.length) {
            throw new IllegalArgumentException("baseChars must be unique");
        }
    }

    public BaseX(String[] baseChars) {
        this(baseChars, new CommonBaseNumIteratorCreator());
    }

    /**
     * isValid : 判断一个String 是否是这个进制的数<br>
     *
     * @param baseNumber 字符串
     * @return boolean
     * @author xiahaifeng
     */
    public boolean isValid(String baseNumber) {
        Set<String> baseSet = baseCharIndexMap.keySet();
        for (String c : baseNumberIteratorCreator.baseNumber(baseNumber)) {
            if (!baseSet.contains(c)) {
                return false;
            }
        }
        return true;
    }

    private Iterable<String> baseNumber(String baseNumber) {
        return baseNumberIteratorCreator.baseNumber(baseNumber);
    }

    private int baseNumCharSIndex(String baseNumChar) {
        return baseCharIndexMap.get(baseNumChar);
    }

    public List<Integer> baseNumCharsIndexList(String baseNumber) {
        List<Integer> charsIndexList = new ArrayList<>();
        for (String c : baseNumberIteratorCreator.baseNumber(baseNumber)) {
            charsIndexList.add(baseNumCharSIndex(c));
        }
        return charsIndexList;
    }

    public String baseNumIndexListToString(List<Integer> baseNumIndexList) {
        StringBuilder sb = new StringBuilder();
        for (Integer index : baseNumIndexList) {
            sb.append(baseChars[index]);
        }
        return sb.toString();
    }

    public BaseNumConverter fromBaseX(BaseX fromBaseX) {
        return new BaseNumConverter(fromBaseX, this);
    }

    public BaseNumConverter toBaseX(BaseX toBaseX) {
        return new BaseNumConverter(this, toBaseX);
    }
}