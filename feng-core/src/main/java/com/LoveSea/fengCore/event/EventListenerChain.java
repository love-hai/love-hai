package com.LoveSea.fengCore.event;

import java.util.Optional;

/**
 * @author xiahaifeng
 */

public interface EventListenerChain<T> {

    <E> Optional<EventListenerChain<E>> returnTo(Class<E> eventClazz);

    EventDriven add(EventListener<T> listener);

    void dispatch(T var1);

    static <T extends Event> EventListenerChain<T> of(Class<T> eventClazz) {
        return new EventListenerChainImpl<>(eventClazz);
    }
}