package com.loveSea.designPattern.abstractFactoryPattern;

/**
 * @author xiahaifeng
 */

public interface FurnitureFactory {
    Chair createChair();

    CoffeeTable createCoffeeTable();
}