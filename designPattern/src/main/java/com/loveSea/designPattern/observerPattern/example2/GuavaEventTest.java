package com.loveSea.designPattern.observerPattern.example2;

import com.google.common.eventbus.EventBus;

/**
 * @author xiahaifeng
 */

public class GuavaEventTest {
    public static void main(String[] args) {
        EventBus eventBus = new EventBus();
        GuavaEvent guavaEvent = new GuavaEvent();
        eventBus.register(guavaEvent);
        eventBus.post("小夏");
        eventBus.post(123);
    }
}