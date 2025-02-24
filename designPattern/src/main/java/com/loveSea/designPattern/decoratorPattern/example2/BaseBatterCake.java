package com.loveSea.designPattern.decoratorPattern.example2;

/**
 * @author xiahaifeng
 */

public class BaseBatterCake extends BatterCake {
    @Override
    protected String getMsg() {
        return "煎饼";
    }

    @Override
    protected double getPrice() {
        return 5;
    }
}