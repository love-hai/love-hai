package com.LoveSea.fengCore.event;

/**
 * @author xiahaifeng
 */

public interface EventEnforcer<T> {
    EventDriven eventDriven();

    void dispatch(T var1);

    static <T extends Event> EventEnforcer<T> of(EventListener<T> listener) {
        return new EventEnforcerImpl<>(listener);
    }
}