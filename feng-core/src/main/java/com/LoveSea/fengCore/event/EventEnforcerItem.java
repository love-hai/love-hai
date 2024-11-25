package com.LoveSea.fengCore.event;

/**
 * @author xiahaifeng
 */

public interface EventEnforcerItem<T> extends EventEnforcer<T> {
    EventDriven eventDriven();
}