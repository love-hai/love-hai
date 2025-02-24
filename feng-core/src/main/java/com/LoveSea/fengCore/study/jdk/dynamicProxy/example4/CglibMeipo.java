package com.LoveSea.fengCore.study.jdk.dynamicProxy.example4;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author xiahaifeng
 */

public class CglibMeipo implements MethodInterceptor {

    Class<?> clazz;

    public Object getInstance(Class<?> clazz) throws Throwable {
        Enhancer enhancer = new Enhancer();
        this.clazz = clazz;
        // 要把哪个设置成即将生成新类的父类
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        return enhancer.create();
    }
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        before();
        Object obj = methodProxy.invokeSuper(o, objects);
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