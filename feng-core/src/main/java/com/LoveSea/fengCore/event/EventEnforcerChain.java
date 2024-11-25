package com.LoveSea.fengCore.event;

import com.LoveSea.fengCore.event.api.Event;
import com.LoveSea.fengCore.event.api.EventListener;

import java.util.Optional;

/**
 * @author xiahaifeng
 */

public interface EventEnforcerChain<T>  extends EventEnforcer<T> {
    EventDriven add(EventEnforcerItem<T> eventEnforcerItem);
}