package com.LoveSea.fengCore.event;

import com.LoveSea.fengCore.event.api.Event;
import com.LoveSea.fengCore.event.api.EventListener;

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