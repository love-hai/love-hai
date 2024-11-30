package com.LoveSea.fengCore.callback.api;

/**
 * @author xiahaifeng
 */
@FunctionalInterface
public interface PACallback<P, R> {
    void run(P p, R r);
}