package com.LoveSea.fengCore.retryable;

/**
 * @author xiahaifeng
 */
@FunctionalInterface
public interface ReFunVoidMethod extends ReFunMethod {
    void run() throws Exception;
}