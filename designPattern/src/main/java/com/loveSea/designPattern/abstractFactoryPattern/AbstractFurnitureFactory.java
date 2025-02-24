package com.loveSea.designPattern.abstractFactoryPattern;

/**
 * @author xiahaifeng
 */

public class AbstractFurnitureFactory {

    public static void main(String[] args) {
        produceFurniture(new ModernFurnitureFactory());
        produceFurniture(new VictorianFurnitureFactory());
    }

    public static void produceFurniture(FurnitureFactory furnitureFactory) {
        Chair chair = furnitureFactory.createChair();
        CoffeeTable coffeeTable = furnitureFactory.createCoffeeTable();
        chair.sitOn();
        coffeeTable.use();
    }

}