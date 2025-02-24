package com.loveSea.designPattern.singletonPattern;

import lombok.extern.slf4j.Slf4j;

/**
 * @author xiahaifeng
 */
@Slf4j
public enum SingletonEnum implements Singleton {

    INSTANCE;

    SingletonEnum() {
        System.out.println("SingletonEnum is created");
    }

    public static void main(String[] args) {
        log.info("start test SingletonEnum");
        Singleton instance = SingletonEnum.INSTANCE;
        log.info("instance:{}", instance);
        log.info("end test SingletonEnum");
    }
}