package com.LoveSea.fengCore.event.internal;

import com.LoveSea.fengCore.event.api.EventDriven;
import com.LoveSea.fengCore.event.api.EventListener;

/**
 * @author xiahaifeng
 */

public class EventEnforcerItemImpl<T> implements EventEnforcerItem<T> {
    private final Class<T> eventClazz;
    private final EventDriven eventDriven;
    private final EventListener<T> eventListener;

    public EventEnforcerItemImpl(Class<T> clazz, EventListener<T> eventListener) {
        this.eventClazz = clazz;
        this.eventListener = eventListener;
        this.eventDriven = EventDriven.of();
    }

    @Override
    public EventDriven eventDriven() {
        return eventDriven;
    }

    public boolean isEnforced() {
        return eventDriven.isCanOccur();
    }

    @Override
    public void dispatch(T var1) {
        if (isEnforced()) {
            eventListener.run(var1);
        }
    }

    @Override
    public Class<?> getEventClazz() {
        return eventClazz;
    }
}