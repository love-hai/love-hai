package com.LoveSea.fengCore.study.designPattern.flyweightPattern;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FigurePainting implements Painting {
    public FigurePainting(String figure) {
        this.figure = figure;
    }
    private final String figure;

    public String getName() {
        return figure;
    }

    @Override
    public void make() {
        log.info("在画上画一个{}",figure);
    }
}