package com.LoveSea.fengCore.study.work;

import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * @author xiahaifeng
 */

public class QueueTakeExc {
    private final Throwable throwable;

    public QueueTakeExc(Throwable throwable) {
        this.throwable = throwable;
    }

    public void exceptionally(Consumer<Throwable> ExceptionHandler) {
        Objects.requireNonNull(throwable);
        ExceptionHandler.accept(throwable);
    }

    public static QueueTakeExc noException() {
        return EmptyQueueTakeExc.emptyNetRequestExc;
    }

    public static class EmptyQueueTakeExc extends QueueTakeExc {
        static EmptyQueueTakeExc emptyNetRequestExc = new EmptyQueueTakeExc();

        public EmptyQueueTakeExc() {
            super(null);
        }

        @Override
        public void exceptionally(Consumer<Throwable> ExceptionHandler) {
        }
    }
}