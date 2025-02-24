package com.loveSea.designPattern.factoryPattern;

public class LandscapePainting implements Painting {

        @Override
        public void make() {
            System.out.println("在画上画一些山水");
        }

        public void make2() {
            System.out.println("擦去画上的山水");
        }
}
