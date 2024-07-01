package com.LoveSea.fengCore.retryable;

import lombok.extern.slf4j.Slf4j;

import java.util.Stack;

/**
 * 重试次数记录
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

    void addRetryCount() {
        getRetryCountRecord().push(getRetryCountRecord().isEmpty() ? 1 : getRetryCountRecord().pop() + 1);
    }

    void pushRetryCount(Integer retryCount) {
        getRetryCountRecord().push(retryCount);
    }

    void popRetryCount() {
        getRetryCountRecord().pop();
    }

    int getRetryCount() {
        return getRetryCountRecord().peek();
    }

    void remove() {
        retryCountRecord.remove();
    }
}
