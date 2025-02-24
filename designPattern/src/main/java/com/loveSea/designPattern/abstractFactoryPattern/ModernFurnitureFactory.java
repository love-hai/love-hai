package com.loveSea.designPattern.abstractFactoryPattern;

/**
 * @author xiahaifeng
 */

public class ModernFurnitureFactory implements FurnitureFactory {

    @Override
    public ModernChair createChair() {
        return new ModernChair();
    }

    @Override
    public ModernCoffeeTable createCoffeeTable() {
        return new ModernCoffeeTable();
    }
}