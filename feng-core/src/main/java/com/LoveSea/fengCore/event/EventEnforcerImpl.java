package com.LoveSea.fengCore.event;

import com.LoveSea.fengCore.event.api.EventListener;

/**
 * @author xiahaifeng
 */

public class EventEnforcerImpl<T> implements EventEnforcer<T> {
    private final EventDriven eventDriven;
    private final EventListener<T> eventListener;

    public EventEnforcerImpl(EventListener<T> eventListener) {
        this.eventListener = eventListener;
        eventDriven = EventDriven.of();
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
}