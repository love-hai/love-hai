package com.loveSea.designPattern.strategyPattern;

public class FigurePainting implements Painting {
    @Override
    public void make() {
        System.out.println("在画上画一些人物");
    }
}
