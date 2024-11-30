package com.LoveSea.fengCore.event.internal;

import com.LoveSea.fengCore.event.api.EventDriven;

/**
 * @author xiahaifeng
 */

public class EventDrivenImpl implements EventDriven {

    boolean canOccur = true;

    @Override
    public void unOccur() {
        canOccur = false;
    }

    @Override
    public boolean isCanOccur() {
        return canOccur;
    }
}