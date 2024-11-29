package com.LoveSea.fengCore.study.designPattern.singletonPattern;

/**
 * 线程安全的双重检查锁模式
 *
 * @author xiahaifeng
 */

public class DoubleCheckedLockSingleton implements Singleton {
    // 如果不加 volatile 就是非线程安全的。
    private static volatile Singleton singleton_instance;

    private DoubleCheckedLockSingleton() {
    }

    public static Singleton getInstance() {
        if (singleton_instance == null) {
            synchronized (Singleton.class) {
                if (singleton_instance == null) {
                    singleton_instance = new DoubleCheckedLockSingleton();
                }
            }
        }
        return singleton_instance;
    }
}