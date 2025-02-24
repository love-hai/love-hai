package com.loveSea.designPattern.singletonPattern;

/**
 * @author xiahaifeng
 */

// 线程安全懒汉式单例
public class SyncLazyManSingleton implements Singleton {
    private static Singleton singleton_Instance;

    private SyncLazyManSingleton() {
    }

    public static synchronized Singleton getInstance() {
        if (singleton_Instance == null) {
            singleton_Instance = new SyncLazyManSingleton();
        }
        return singleton_Instance;
    }
}