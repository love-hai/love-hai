package com.LoveSea.fengCore.study.jdk.dynamicProxy.example3;

import java.lang.reflect.Method;

/**
 * @author xiahaifeng
 */

public class GPMeipo implements GPInvocationHandler {

    private Object target;

    public GPMeipo(Object target) {
        this.target = target;
    }

    public static Object getInstance(Object target) {
        GPMeipo gpMeipo = new GPMeipo(target);
        Class<?> clazz = target.getClass();
       return GPProxy.newProxyInstance(new GPClassLoader(),clazz.getInterfaces(),gpMeipo);
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object result = method.invoke(target, args);
        after();
        return result;
    }
    public void before() {
        System.out.println("我是媒婆，我给你找对象，现在已经确认你的要求了");
        System.out.println("开始物色");
    }

    public void after() {
        System.out.println("如何合适，就领结婚证吧");
    }
}