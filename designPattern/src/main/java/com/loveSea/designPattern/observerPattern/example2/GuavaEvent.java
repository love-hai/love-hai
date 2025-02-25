package com.loveSea.designPattern.observerPattern.example2;

import com.google.common.eventbus.Subscribe;

/**
 * @author xiahaifeng
 */

public class GuavaEvent {

    @Subscribe
    public void subscribe(String str) {
        System.out.println("执行subscribe1方法，传入的参数是：" + str);
    }
    @Subscribe
    public void subscribe3(String str) {
        System.out.println("执行subscribe3方法，传入的参数是：" + str);
    }

    @Subscribe
    public void subscribe(Integer integer) {
        System.out.println("执行subscribe2方法，传入的参数是：" + integer);
    }
}