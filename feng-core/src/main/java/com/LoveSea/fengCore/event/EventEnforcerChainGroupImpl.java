package com.LoveSea.fengCore.event;

import com.LoveSea.fengCore.event.api.Event;
import com.LoveSea.fengCore.event.api.EventListener;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xiahaifeng
 */

public class EventEnforcerChainGroupImpl<T extends Event> implements EventEnforcerChainGroup<T> {
    private final Map<Class<? extends T>, EventEnforcer<?>> eventEnforcerMap = new ConcurrentHashMap<>();
    private final ReentrantLock lock = new ReentrantLock();

    private <E extends T> EventEnforcer<E> get(Class<E> eventClass) {
        EventEnforcer<?> eventEnforcer = eventEnforcerMap.get(eventClass);
        if (null == eventEnforcer) {
            return null;
        }
        if (eventClass != eventEnforcer.getEventClazz()) {
            return null;
        }
        return eventEnforcer.toThis(eventClass).orElse(null);
    }

    private <E extends T> EventDriven addOrCreate(Class<E> eventClass, EventListener<E> listener) {
        try {
            lock.lock();
            EventEnforcerItem<E> eventEnforcerItem = EventEnforcer.of(eventClass, listener);
            EventEnforcer<?> eventEnforcer = eventEnforcerMap.get(eventClass);
            if (null == eventEnforcer) {
                this.eventEnforcerMap.put(eventClass, eventEnforcerItem);
                return eventEnforcerItem.eventDriven();
            }
            if (eventEnforcer instanceof EventEnforcerChain) {
                EventEnforcerChain<E> oldEventEnforcerChain = eventEnforcer.toChain(eventClass).orElse(null);
                if (null == oldEventEnforcerChain) {
                    throw new IllegalStateException("need " + eventClass + "; but eventEnforcerChain eventClazz is " + eventEnforcer.getEventClazz());
                }
                return oldEventEnforcerChain.add(eventEnforcerItem);
            }
            if (eventEnforcer instanceof EventEnforcerItem) {
                EventEnforcerItem<E> oldEventEnforcerItem = eventEnforcer.toItem(eventClass).orElse(null);
                if (null == oldEventEnforcerItem) {
                    throw new IllegalStateException("need " + eventClass + "; but eventEnforcerItem eventClazz is " + eventEnforcer.getEventClazz());
                }
                EventEnforcerChain<E> eventEnforcerChain = EventEnforcer.of(eventClass);
                eventEnforcerChain.add(oldEventEnforcerItem);
                eventEnforcerMap.put(eventClass, eventEnforcerChain);
                return eventEnforcerChain.add(eventEnforcerItem);
            }
            throw new IllegalStateException("unknown eventEnforcer type " + eventEnforcer.getClass());
        } finally {
            lock.unlock();
        }
    }

    @Override
    public <E extends T> void dispatch(Class<E> eventClass, E event) {
        EventEnforcer<E> eventEnforcer = get(eventClass);
        if (null == eventEnforcer) {
            return;
        }
        eventEnforcer.dispatch(event);
    }

    @Override
    public <E extends T> EventDriven on(Class<E> eventClass, EventListener<E> listener) {
        return this.addOrCreate(eventClass, listener);
    }
}