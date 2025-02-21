package com.LoveSea.fengCore.study.designPattern.prototypePattern;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FigurePainting implements Painting {

    public static class FigurePaintingHolder {
        private static final FigurePainting figurePainting = new FigurePainting();
    }

    public static FigurePainting getInstance() {
        return FigurePaintingHolder.figurePainting;
    }

    public FigurePainting() {
    }

    private String figure;
    private String figure1;
    private String figure2;
    private String figure3;
    private String figure4;
    private String figure5;
    private String figure6;
    private String figure7;
    private String figure8;
    private String figure9;
    private String figure10;
    private String figure11;
    private String figure12;
    private String figure13;
    private String figure14;
    private String figure15;


    public String getName() {
        return figure;
    }

    @Override
    public void make() {
        log.info("在画上画一个{}", figure);
    }

    @Override
    public Object clone() {
        Object clone = null;
        try {
            clone = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }
}