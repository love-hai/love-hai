package com.LoveSea.fengCore.event;

import java.util.LinkedList;
import java.util.Optional;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xiahaifeng
 */
public class EventListenerChainImpl<T extends Event> implements EventListenerChain<T> {

    /**
     * 对 listeners 新增 和 使用迭代器遍历时进行加锁。
     */
    private final ReentrantLock lock;
    private final LinkedList<EventEnforcer<T>> listeners;

    private final Class<T> eventClazz;

    public EventListenerChainImpl(Class<T> eventClazz) {
        this.listeners = new LinkedList<>();
        this.lock = new ReentrantLock();
        this.eventClazz = eventClazz;
    }

    @Override
    public <E> Optional<EventListenerChain<E>> returnTo(Class<E> clazz) {
        if (clazz == this.eventClazz) {
            // 如果 eventClazz 和 clazz 相等，说明这次存储的是 E 类型的监听器
            // 使用强制类型转换，是不可能出现异常的。
            @SuppressWarnings("unchecked")
            EventListenerChain<E> result = (EventListenerChain<E>) this;
            return Optional.of(result);
        }
        return Optional.empty();
    }

    @Override
    public EventDriven add(EventListener<T> listener) {
        try {
            lock.lock();
            EventEnforcer<T> eventEnforcer = EventEnforcer.of(listener);
            listeners.add(eventEnforcer);
            return eventEnforcer.eventDriven();
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
}