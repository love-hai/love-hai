package com.LoveSea.fengCore.study.jdk.dynamicProxy.example1;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author xiahaifeng
 */
@Slf4j
public class JDKMeipo implements InvocationHandler {
    private Object target;

    public Object getInstance(Object proxy) {
        this.target = proxy;
        Class<?> clazz = proxy.getClass();
        return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object obj = method.invoke(target, args);
        after();
        return obj;
    }

    public void before() {
        System.out.println("我是媒婆，我给你找对象，现在已经确认你的要求了");
        System.out.println("开始物色");
    }

    public void after() {
        System.out.println("如何合适，就领结婚证吧");
    }

}