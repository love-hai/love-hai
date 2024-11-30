package com.LoveSea.fengCore.event.internal;

import com.LoveSea.fengCore.event.api.EventDriven;

/**
 * @author xiahaifeng
 */

public interface EventEnforcerItem<T> extends EventEnforcer<T> {
    EventDriven eventDriven();
}