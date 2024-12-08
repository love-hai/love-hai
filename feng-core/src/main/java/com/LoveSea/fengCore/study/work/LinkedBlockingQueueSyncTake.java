package com.LoveSea.fengCore.study.work;

/**
 * @author xiahaifeng
 */

public interface LinkedBlockingQueueSyncTake<T> extends LinkedBlockingQueueTake<T> {
    QueueTakeExe<T> take() throws InterruptedException;
}