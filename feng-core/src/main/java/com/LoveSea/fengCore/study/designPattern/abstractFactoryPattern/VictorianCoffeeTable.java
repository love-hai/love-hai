package com.LoveSea.fengCore.study.designPattern.abstractFactoryPattern;

import lombok.extern.slf4j.Slf4j;

/**
 * @author xiahaifeng
 */
@Slf4j
public class VictorianCoffeeTable implements CoffeeTable {

    @Override
    public void use() {
        log.info("You are using a Victorian coffee table.");
    }
}