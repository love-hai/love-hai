package com.LoveSea.fengCore.retryable;

/**
 * @author xiahaifeng
 */
@FunctionalInterface
public interface ReFunObMethod<T> extends ReFunMethod {
    T run() throws Exception;
}