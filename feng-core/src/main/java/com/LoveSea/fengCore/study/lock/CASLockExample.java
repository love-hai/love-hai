package com.LoveSea.fengCore.study.lock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xiahaifeng
 */

public class CASLockExample {
    private final AtomicInteger counter = new AtomicInteger(0);

    public void increment() {
        int current;
        int newValue;
        do {
            current = counter.get();         // 获取当前值
            newValue = current + 1;         // 计算新值
        } while (!counter.compareAndSet(current, newValue)); // CAS尝试更新
    }

    public int getValue() {
        return counter.get();
    }

    public static void main(String[] args) throws InterruptedException {
        CASLockExample casExample = new CASLockExample();

        // 创建多个线程同时更新计数器
        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    casExample.increment();
                }
            });
        }

        // 启动线程
        for (Thread thread : threads) {
            thread.start();
        }

        // 等待所有线程完成
        for (Thread thread : threads) {
            thread.join();
        }

        // 输出计数器的最终值
        System.out.println("Final counter value: " + casExample.getValue());
    }
}