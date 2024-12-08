package com.LoveSea.fengCore.study.work;

/**
 * @author xiahaifeng
 */

public interface LinkedBlockingQueueAutoTake<T> extends LinkedBlockingQueueTake<T> {
    void addSolution(QueueTakeSolutionRecord<T> queue);
    boolean removeSolution(QueueTakeSolutionRecord<T> queue);
}