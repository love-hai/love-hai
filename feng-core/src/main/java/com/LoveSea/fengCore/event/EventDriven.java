package com.LoveSea.fengCore.event;

/**
 * @author xiahaifeng
 */

public interface EventDriven {
    void unOccur();

    boolean isCanOccur();

    static EventDriven of() {
        return new EventDrivenImpl();
    }
}