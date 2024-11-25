package com.LoveSea.fengCore.event;

/**
 * @author xiahaifeng
 */

@FunctionalInterface
public interface EventListener<T> {
    void run(T var1);
}
