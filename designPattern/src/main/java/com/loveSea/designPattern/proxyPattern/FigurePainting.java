package com.loveSea.designPattern.proxyPattern;

import com.loveSea.designPattern.decoratorPattern.example1.Painting;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FigurePainting implements Painting {
    @Override
    public void make() {
        log.info("在画上画一些人物");
    }
}
