package com.LoveSea.fengCore.study.lock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 简单阐述一下AQS的思路
 * 获取锁，获取成功将state设置为1，设置线程
 * 获取失败，检查当前使用的线程是否是请求线程，如果是，state+1。
 * 如果当前使用锁的线程也不是请求线程，那么将请求线程加入到等待队列中，等待唤醒。
 * 这个类模拟 线程加入等待队列。
 *
 * @author xiahaifeng
 */

public class AQSLockExample {
    public static void main(String[] args) {
        AQSLock aqsLock = new AQSLock();
        AtomicInteger flag = new AtomicInteger();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10; j++) {
                    aqsLock.lock();
                    try {
                        Thread.sleep((long) (Math.random()*1000));
                        System.out.println(flag.getAndIncrement());
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    } finally {
                        aqsLock.unlock();
                    }
                }
            }).start();
        }
    }
}