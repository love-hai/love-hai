package com.LoveSea.fengCore.event.internal;

import com.LoveSea.fengCore.event.api.Event;
import com.LoveSea.fengCore.event.api.EventListener;

import java.util.Optional;

/**
 * @author xiahaifeng
 */

public interface EventEnforcer<T> {

    default <E> Optional<EventEnforcer<E>> toThis(Class<E> eventClazz) {
        if (eventClazz != getEventClazz()) {
            return Optional.empty();
        }
        @SuppressWarnings("unchecked")
        EventEnforcer<E> result = (EventEnforcer<E>) this;
        return Optional.of(result);
    }

    default <E> Optional<EventEnforcerItem<E>> toItem(Class<E> eventClazz) {
        if (eventClazz != getEventClazz()) {
            return Optional.empty();
        }
        if (this instanceof EventEnforcerItem) {
            @SuppressWarnings("unchecked")
            EventEnforcerItem<E> result = (EventEnforcerItem<E>) this;
            return Optional.of(result);
        }
        return Optional.empty();
    }

    default <E> Optional<EventEnforcerChain<E>> toChain(Class<E> eventClazz) {
        if (eventClazz != getEventClazz()) {
            return Optional.empty();
        }
        if (this instanceof EventEnforcerChain) {
            @SuppressWarnings("unchecked")
            EventEnforcerChain<E> result = (EventEnforcerChain<E>) this;
            return Optional.of(result);
        }
        return Optional.empty();
    }

    void dispatch(T var1);

    Class<?> getEventClazz();

    static <T extends Event> EventEnforcerItem<T> of(Class<T> eventClazz, EventListener<T> listener) {
        return new EventEnforcerItemImpl<>(eventClazz, listener);
    }

    static <T extends Event> EventEnforcerChain<T> of(Class<T> eventClazz) {
        return new EventEnforcerChainImpl<>(eventClazz);
    }
}