package com.LoveSea.fengCore.study.designPattern.decoratorPattern.example2;

/**
 * @author xiahaifeng
 */

public class BatterCakeDecorator extends BatterCake {
    private final BatterCake batterCake;

    public BatterCakeDecorator(BatterCake batterCake) {
        this.batterCake = batterCake;
    }

    @Override
    protected String getMsg() {
        return this.batterCake.getMsg();
    }

    @Override
    protected double getPrice() {
        return this.batterCake.getPrice();
    }
}