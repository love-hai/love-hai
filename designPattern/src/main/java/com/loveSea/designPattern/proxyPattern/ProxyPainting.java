package com.loveSea.designPattern.proxyPattern;

import lombok.extern.slf4j.Slf4j;

/**
 * @author xiahaifeng
 */
@Slf4j
public class ProxyPainting implements Painting{
    @Override
    public void make() {
        FigurePainting figurePainting = new FigurePainting();
        log.info("代理画家开始画画");
        figurePainting.make();
    }
}
