package com.LoveSea.fengCore.study.work;

/**
 * @author xiahaifeng
 */

public class AfterQueueTakeActionImpl implements AfterQueueTakeAction {
    static AfterQueueTakeAction successAction() {
        return AfterNetQueueTakeActionEnum.SUCCESS.afterNetQueueTakeAction();
    }

    static AfterQueueTakeAction failAction() {
        return AfterNetQueueTakeActionEnum.FAIL.afterNetQueueTakeAction();
    }

    static AfterQueueTakeAction continueWaitAction() {
        return AfterNetQueueTakeActionEnum.CONTINUE_WAIT.afterNetQueueTakeAction();
    }

    private AfterNetQueueTakeActionEnum afterNetQueueTakeActionEnum;

    private AfterQueueTakeActionImpl(AfterNetQueueTakeActionEnum afterNetQueueTakeActionEnum) {
        this.afterNetQueueTakeActionEnum = afterNetQueueTakeActionEnum;
    }

    @Override
    public void success() {
        afterNetQueueTakeActionEnum = AfterNetQueueTakeActionEnum.SUCCESS;

    }

    @Override
    public void fail() {
        afterNetQueueTakeActionEnum = AfterNetQueueTakeActionEnum.FAIL;
    }

    @Override
    public void continueWait() {
        afterNetQueueTakeActionEnum = AfterNetQueueTakeActionEnum.CONTINUE_WAIT;
    }

    @Override
    public boolean isSuccess() {
        return afterNetQueueTakeActionEnum == AfterNetQueueTakeActionEnum.SUCCESS;
    }

    @Override
    public boolean isFail() {
        return afterNetQueueTakeActionEnum == AfterNetQueueTakeActionEnum.FAIL;
    }

    @Override
    public boolean isContinueWait() {
        return afterNetQueueTakeActionEnum == AfterNetQueueTakeActionEnum.CONTINUE_WAIT;
    }

    public enum AfterNetQueueTakeActionEnum {
        // default
        SUCCESS,
        FAIL,
        CONTINUE_WAIT;

        AfterNetQueueTakeActionEnum() {
        }

        AfterQueueTakeAction afterNetQueueTakeAction() {
            return new AfterQueueTakeActionImpl(this);
        }
    }
}