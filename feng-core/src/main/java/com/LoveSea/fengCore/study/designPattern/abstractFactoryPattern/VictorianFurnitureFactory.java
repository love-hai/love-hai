package com.LoveSea.fengCore.study.designPattern.abstractFactoryPattern;

/**
 * @author xiahaifeng
 */

public class VictorianFurnitureFactory implements FurnitureFactory {
    @Override
    public VictorianChair createChair() {
        return new VictorianChair();
    }

    @Override
    public VictorianCoffeeTable createCoffeeTable() {
        return new VictorianCoffeeTable();
    }
}