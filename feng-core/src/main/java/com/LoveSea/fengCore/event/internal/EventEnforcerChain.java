package com.LoveSea.fengCore.event.internal;

/**
 * @author xiahaifeng
 */

public interface EventEnforcerChain<T>  extends EventEnforcer<T> {
    boolean add(EventEnforcerItem<T> eventEnforcerItem);
}