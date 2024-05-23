package com.LoveSea.fengCore.study.designPattern.templatePattern;

public class MakeLandscapePainting extends MakePaintingTemplate{

        @Override
        public void initialize(){
            System.out.println("初始化山水画");
        }
        @Override
        public void draw(){
            System.out.println("在画上画一些山水");
        }
}
