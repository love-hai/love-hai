package com.LoveSea.fengCore.study.work;

/**
 * @author xiahaifeng
 */

public interface AfterQueueTakeAction {
    void success();

    void fail();

    void continueWait();

    boolean isSuccess();

    boolean isFail();

    boolean isContinueWait();
}