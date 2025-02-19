package com.LoveSea.fengCore.study.designPattern.abstractFactoryPattern;

import org.checkerframework.checker.units.qual.C;

/**
 * @author xiahaifeng
 */

public interface FurnitureFactory {
    Chair createChair();
    CoffeeTable createCoffeeTable();
}