package com.lalang.lalangapi.config;

import feign.RetryableException;
import feign.Retryer;

/**
 * Response返回值错误重试
 * @author xiahaifeng
 */

public class ResponseErrorRetryer implements Retryer {
    private static final long defaultPeriod = 1000L;
    private static final int defaultMaxAttempts = 1;

    private final int maxAttempts;
    private final long backoff;
    private int attempt;

    public ResponseErrorRetryer(int maxAttempts, long backoff) {
        this.maxAttempts = maxAttempts;
        this.backoff = backoff;
        this.attempt = 0;
    }

    public ResponseErrorRetryer() {
        this.maxAttempts = defaultMaxAttempts;
        this.backoff = defaultPeriod;
        this.attempt = 0;
    }

    @Override
    public void continueOrPropagate(RetryableException e) {
        if (attempt++ >= maxAttempts) {
            throw e;
        } else {
            try {
                Thread.sleep(backoff);
            } catch (InterruptedException ignored) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @Override
    public Retryer clone() {
        return new ResponseErrorRetryer(this.maxAttempts, this.backoff);
    }
}