package com.LoveSea.fengCore.study.jdk.dynamicProxy.example3;

import java.lang.reflect.Method;

/**
 * @author xiahaifeng
 */

public interface GPInvocationHandler {
    Object invoke(Object proxy, Method method, Object[] args) throws Throwable;
}