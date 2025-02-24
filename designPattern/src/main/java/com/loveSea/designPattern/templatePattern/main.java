package com.loveSea.designPattern.templatePattern;

public class main {
    public static void main(String[] args) {
        MakePaintingTemplate makePaintingTemplate = new MakeFigurePainting();
        makePaintingTemplate.makePainting();
        System.out.println("==================================");
        makePaintingTemplate = new MakeLandscapePainting();
        makePaintingTemplate.makePainting();
    }
}
