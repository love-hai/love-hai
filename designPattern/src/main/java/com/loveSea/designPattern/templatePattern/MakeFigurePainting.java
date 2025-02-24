package com.loveSea.designPattern.templatePattern;
public class MakeFigurePainting extends MakePaintingTemplate {

    @Override
    public void initialize(){
        System.out.println("初始化人物画");
    }
    @Override
    public void draw(){
        System.out.println("在画上画一些人物");
    }

}
