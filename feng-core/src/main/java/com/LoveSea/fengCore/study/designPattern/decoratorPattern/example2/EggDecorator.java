package com.LoveSea.fengCore.study.designPattern.decoratorPattern.example2;

/**
 * @author xiahaifeng
 */

public class EggDecorator extends BatterCakeDecorator {
    public EggDecorator(BatterCake batterCake) {
        super(batterCake);
    }

    @Override
    protected String getMsg() {
        return super.getMsg() + "+鸡蛋";
    }

    @Override
    protected double getPrice() {
        return super.getPrice() + 1;
    }
}