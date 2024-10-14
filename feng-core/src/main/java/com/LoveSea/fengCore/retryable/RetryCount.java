package com.LoveSea.fengCore.retryable;

/**
 * @author xiahaifeng
 */

public class RetryCount {
    private static final RetryCountThreadLocal  retryCountThreadLocal= new RetryCountThreadLocal();

    static void addRetryCount() {
        retryCountThreadLocal.addRetryCount();
    }

    static void pushRetryCount(Integer retryCount) {
        retryCountThreadLocal.pushRetryCount(retryCount);
    }

    static void popRetryCount() {
        retryCountThreadLocal.popRetryCount();
    }

    public static int getRetryCount() {
        return retryCountThreadLocal.getRetryCount();
    }

    static void remove() {
        retryCountThreadLocal.remove();
    }
}