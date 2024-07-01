package com.LoveSea.fengCore.retryable;

/**
 * RetryManagement
 * @author xiahaifeng
 */

public interface RetryManagement {
    RetryThreadLocal retryThreadLocal = new RetryThreadLocal();

    /**
     * getRetryCount : 获取方法重试次数
     *
     * @author xiahaifeng
     */
    static int getRetryCount() {
        return retryThreadLocal.getRetryCount();
    }
}
