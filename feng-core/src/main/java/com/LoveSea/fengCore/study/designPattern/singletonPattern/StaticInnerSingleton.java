package com.LoveSea.fengCore.study.designPattern.singletonPattern;

import lombok.extern.slf4j.Slf4j;

/**
 * 静态内部类单例
 * 只有被调用时才会加载内部类，实现了懒加载
 *
 * @author xiahaifeng
 */

@Slf4j
public class StaticInnerSingleton implements Singleton {

    private static class StaticInnerSingletonHolder {
        private static final Singleton Singleton_Instance = new StaticInnerSingleton();
    }

    private StaticInnerSingleton() {
        log.info("StaticInnerSingleton is created");
    }

    public static Singleton getInstance() {
        return StaticInnerSingletonHolder.Singleton_Instance;
    }

    public static void main(String[] args) {
        log.info("start test StaticInnerSingleton");
        Singleton instance = StaticInnerSingleton.getInstance();
        log.info("instance:{}", instance);
        log.info("end test StaticInnerSingleton");
    }
}