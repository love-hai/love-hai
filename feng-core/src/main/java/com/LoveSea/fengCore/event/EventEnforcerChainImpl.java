package com.LoveSea.fengCore.event;

import com.LoveSea.fengCore.event.api.Event;
import com.LoveSea.fengCore.event.api.EventListener;

import java.util.LinkedList;
import java.util.Optional;
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
    public EventDriven add(EventEnforcerItem<T> eventEnforcerItem) {
        try {
            lock.lock();
            listeners.add(eventEnforcerItem);
            return eventEnforcerItem.eventDriven();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void dispatch(T var1) {
        try {
            lock.lock();
            for (EventEnforcer<T> listener : listeners) {
                listener.dispatch(var1);
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