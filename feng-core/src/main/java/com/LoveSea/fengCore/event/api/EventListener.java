package com.LoveSea.fengCore.event.api;

/**
 * @author xiahaifeng
 */

@FunctionalInterface
public interface EventListener<T> {
    void run(T var1);
}
