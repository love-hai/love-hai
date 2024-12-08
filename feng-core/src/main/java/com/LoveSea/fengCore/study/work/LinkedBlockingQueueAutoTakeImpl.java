package com.LoveSea.fengCore.study.work;

/**
 * @author xiahaifeng
 */

public class LinkedBlockingQueueAutoTakeImpl<T> implements LinkedBlockingQueueAutoTake<T> {
    SolutionMap<T> solutionMap = new SolutionMap<>();

    @Override
    public void addSolution(QueueTakeSolutionRecord<T> queue) {
    }

    @Override
    public boolean removeSolution(QueueTakeSolutionRecord<T> queue) {
        return false;
    }
}