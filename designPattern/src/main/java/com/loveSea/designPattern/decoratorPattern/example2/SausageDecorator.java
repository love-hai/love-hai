package com.loveSea.designPattern.decoratorPattern.example2;

/**
 * @author xiahaifeng
 */

public class SausageDecorator extends BatterCakeDecorator {
    public SausageDecorator(BatterCake batterCake) {
        super(batterCake);
    }

    @Override
    protected String getMsg() {
        return super.getMsg() + "+香肠";
    }

    @Override
    protected double getPrice() {
        return super.getPrice() + 2;
    }
}