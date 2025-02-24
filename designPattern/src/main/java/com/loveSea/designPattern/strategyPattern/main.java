package com.loveSea.designPattern.strategyPattern;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class main {
    public static void main(String[] args) {
        PaintingStrategy paintingStrategy = new PaintingStrategy(new FigurePainting());
        paintingStrategy.make();
        paintingStrategy = new PaintingStrategy(new LandscapePainting());
        paintingStrategy.make();
        log.info("end");
    }
}
