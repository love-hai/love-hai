package com.LoveSea.fengCore.event;

/**
 * @author xiahaifeng
 */

public class EventDrivenImpl implements EventDriven{

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