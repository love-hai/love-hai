package com.LoveSea.fengCore.study.designPattern.singletonPattern;

import lombok.extern.slf4j.Slf4j;

/**
 * 饿汉式单例
 * 程序启动时就创建实例
 * @author xiahaifeng
 */
@Slf4j
public class HungryManSingleton implements Singleton {
    private static final Singleton Singleton_Instance = new HungryManSingleton();

    private HungryManSingleton() {
        log.info("HungryManSingleton is created");
    }

    public static Singleton getInstance() {
        return Singleton_Instance;
    }

    public static void main(String[] args) {
        log.info("start test HungryManSingleton");
        Singleton instance = HungryManSingleton.getInstance();
        log.info("instance:{}", instance);
        log.info("end test HungryManSingleton");
    }

}