package com.loveSea.designPattern.abstractFactoryPattern;

import lombok.extern.slf4j.Slf4j;

/**
 * @author xiahaifeng
 */
@Slf4j
public class ModernCoffeeTable implements CoffeeTable {

    @Override
    public void use() {
        log.info("现代风格的茶几");
    }
}