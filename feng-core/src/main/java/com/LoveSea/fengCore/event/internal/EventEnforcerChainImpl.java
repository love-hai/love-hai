package com.LoveSea.fengCore.event.internal;

import com.LoveSea.fengCore.event.api.Event;

import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xiahaifeng
 */
public class EventEnforcerChainImpl<T extends Event> implements EventEnforcerChain<T> {

    /**
     * 对 listeners 新增 和 使用迭代器遍历时进行加锁。
     */
    private final ReentrantLock lock;
    private final LinkedList<EventEnforcer<T>> listeners;

    private final Class<T> eventClazz;

    public EventEnforcerChainImpl(Class<T> eventClazz) {
        this.listeners = new LinkedList<>();
        this.lock = new ReentrantLock();
        this.eventClazz = eventClazz;
    }

    @Override
    public boolean add(EventEnforcerItem<T> eventEnforcerItem) {
        try {
            lock.lock();
            return listeners.add(eventEnforcerItem);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void dispatch(T var1) {
        try {
            lock.lock();
            for (EventEnforcer<T> eventEnforcer : listeners) {
                eventEnforcer.dispatch(var1);
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    public Class<?> getEventClazz() {
        return eventClazz;
    }
}