package com.LoveSea.fengCore.event.api;

/**
 * @author xiahaifeng
 */

public interface Listenable<T extends Event> {
    <E extends T> EventDriven on(Class<E> eventClass, EventListener<E> listener);
}