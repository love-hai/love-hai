package com.LoveSea.fengCore.study.work;

import java.util.function.BiConsumer;

/**
 * @author xiahaifeng
 */

public class QueueTakeSolutionRecord<T> {
    public QueueTakeSolutionRecord(BiConsumer<T, AfterQueueTakeAction> executeHandler,
                                   BiConsumer<Throwable, AfterQueueTakeAction> ExceptionHandler) {
        this.executeHandler = executeHandler;
        this.ExceptionHandler = ExceptionHandler;
    }
    BiConsumer<T, AfterQueueTakeAction> executeHandler;
    BiConsumer<Throwable, AfterQueueTakeAction> ExceptionHandler;
}