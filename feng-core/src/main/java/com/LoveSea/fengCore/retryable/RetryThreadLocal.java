package com.LoveSea.fengCore.retryable;

import lombok.extern.slf4j.Slf4j;

import java.util.Stack;

/**
 * 标志判断一个线程内的方法的是否需要重试
 *
 * @author xiahaifeng
 */

@Slf4j
public class RetryThreadLocal {
    private final ThreadLocal<Stack<Integer>> retryCountRecord = new ThreadLocal<>();

    private Stack<Integer> getRetryCountRecord() {
        Stack<Integer> stack = retryCountRecord.get();
        if (null == stack) {
            stack = new Stack<>();
            retryCountRecord.set(stack);
        }
        return stack;
    }

    public void addRetryCount() {
        getRetryCountRecord().push(getRetryCountRecord().isEmpty() ? 1 : getRetryCountRecord().pop() + 1);
    }

    public void pushRetryCount(Integer retryCount) {
        getRetryCountRecord().push(retryCount);
    }

    public void popRetryCount() {
        getRetryCountRecord().pop();
    }

    public int getRetryCount() {
        return getRetryCountRecord().peek();
    }

    public void remove() {
        retryCountRecord.remove();
    }

}
