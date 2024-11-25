package com.LoveSea.fengCore.event;

import com.LoveSea.fengCore.event.api.Event;
import com.LoveSea.fengCore.event.api.EventListener;

import java.util.LinkedList;
import java.util.Optional;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xiahaifeng
 */

public class EventListenerChainGroupImpl<T extends Event> implements EventListenerChainGroup<T> {
    private final LinkedList<EventListenerChain<?>> eventListeners = new LinkedList<>();
    private final ReentrantLock lock = new ReentrantLock();

    private <E extends T> EventListenerChain<E> getOrCreate(Class<E> eventClass) {
        try {
            lock.lock();
            for (EventListenerChain<?> eventListenerChain : eventListeners) {
                Optional<EventListenerChain<E>> optional = eventListenerChain.returnTo(eventClass);
                if (optional.isPresent()) {
                    return optional.get();
                }
            }
            EventListenerChain<E> eventListenerChain = EventListenerChain.of(eventClass);
            this.eventListeners.add(eventListenerChain);
            return eventListenerChain;
        } finally {
            lock.unlock();
        }
    }
    @Override
    public <E extends T> void dispatch(Class<E> eventClass, E event) {
        EventListenerChain<E> eventListenerChain = getOrCreate(eventClass);
        eventListenerChain.dispatch(event);
    }

    @Override
    public <E extends T> EventDriven on(Class<E> eventClass, EventListener<E> listener) {
        EventListenerChain<E> eventListenerChain = getOrCreate(eventClass);
        return eventListenerChain.add(listener);
    }
}