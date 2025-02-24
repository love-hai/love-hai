package com.LoveSea.fengCore.study.jdk.dynamicProxy.example2;

import com.LoveSea.fengCore.study.jdk.dynamicProxy.example1.DynamicProxyMain;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author xiahaifeng
 */

public class OrderServiceDynamicProxy implements InvocationHandler {
    private Object target;
    private SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
    private Object proxy;
    private OrderServiceDynamicProxy(Object target) {
        this.target = target;
        Class<?> clazz = target.getClass();
       this. proxy =  Proxy.newProxyInstance(clazz.getClassLoader(),clazz.getInterfaces(),this);
    }

    public static Object getInstance(Object target) {
        return new OrderServiceDynamicProxy(target).proxy;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        this.before(args[0]);
        Object result = method.invoke(target, args);
        this.after();
        return result;
    }

    public void before(Object arg) {
      try{
          System.out.println("before");
          Object t =arg.getClass().getMethod("getCreateTime").invoke(arg);
          Long time =(Long) t;
          Integer dbRouter = Integer.valueOf(yearFormat.format(new Date(time)));
          System.out.println(dbRouter);
//          DynamicDataSourceEntry.set(dbRouter);
      }catch (Exception e){
          e.printStackTrace();
      }
    }

    public void after(){
        System.out.println("after");
    }
}