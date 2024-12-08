package com.LoveSea.fengCore.study.work;

import lombok.extern.slf4j.Slf4j;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * @author xiahaifeng
 */
@Slf4j
public class QueueTakeExe<T> {
    private final T t;

    private QueueTakeExe(T t  ) {
        this.t = t;
    }

    public QueueTakeExc thenAccept(Consumer<T> executeHandler) {
        try {
            executeHandler.accept(t);
        } catch (Exception e) {
            log.error("executeHandler error", e);
            return new QueueTakeExc(e);
        }
        return QueueTakeExc.noException();
    }
}