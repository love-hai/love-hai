package com.LoveSea.fengCore.event.api;

import com.LoveSea.fengCore.event.internal.EventDrivenImpl;

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