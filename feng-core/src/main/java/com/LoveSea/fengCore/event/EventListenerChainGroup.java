package com.LoveSea.fengCore.event;

/**
 * @author xiahaifeng
 */

public interface EventListenerChainGroup<T> {
    <E extends T> void dispatch(Class<E> eventClass, E event);

    <E extends T> EventDriven on(Class<E> eventClass, EventListener<E> listener);

    static <T extends Event> EventListenerChainGroup<T> of() {
        return new EventListenerChainGroupImpl<>();
    }
}