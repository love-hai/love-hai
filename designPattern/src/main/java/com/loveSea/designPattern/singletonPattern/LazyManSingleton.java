package com.loveSea.designPattern.singletonPattern;

/**
 * 非线程安全懒汉式单例
 * @author xiahaifeng
 */
public class LazyManSingleton implements Singleton {
    private static Singleton singleton_Instance;

    private LazyManSingleton() {
    }

    public static Singleton getInstance() {
        if (singleton_Instance == null) {
            singleton_Instance = new LazyManSingleton();
        }
        return singleton_Instance;
    }
}