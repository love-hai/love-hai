package com.LoveSea.fengCore.event.api;

import com.LoveSea.fengCore.event.internal.EventEnforcerChainGroupImpl;

/**
 * @author xiahaifeng
 */

public interface EventEnforcerChainGroup<T> {
    <E extends T> void dispatch(Class<E> eventClass, E event);

    <E extends T> EventDriven on(Class<E> eventClass, EventListener<E> listener);

    static <T extends Event> EventEnforcerChainGroup<T> of() {
        return new EventEnforcerChainGroupImpl<>();
    }
}