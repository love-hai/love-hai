package com.LoveSea.fengCore.study.jdk.dynamicProxy.example1;

import lombok.extern.slf4j.Slf4j;

/**
 * @author xiahaifeng
 */
@Slf4j
public class Customer implements Person {
    @Override
    public void findLove() {
        log.info("高富帅");
        log.info("身高180");
        log.info("6 块腹肌");
    }
}