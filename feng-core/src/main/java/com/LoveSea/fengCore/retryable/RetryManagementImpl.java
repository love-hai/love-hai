package com.LoveSea.fengCore.retryable;

/**
 * @author xiahaifeng
 */

public class RetryManagementImpl implements RetryManagement {

    public static void pushRetryCount(Integer retryCount) {
        retryThreadLocal.pushRetryCount(retryCount);
    }

    /**
     * addRetryCount : 方法重试次数+1
     *
     * @author xiahaifeng
     */
    public static void addRetryCount() {
        retryThreadLocal.addRetryCount();
    }

    /**
     * popRetryCount : 方法重试次数pop出栈
     *
     * @author xiahaifeng
     */
    public static void popRetryCount() {
        retryThreadLocal.popRetryCount();
    }
}
