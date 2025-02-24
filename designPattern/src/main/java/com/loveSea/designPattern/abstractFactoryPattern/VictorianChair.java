package com.loveSea.designPattern.abstractFactoryPattern;

import lombok.extern.slf4j.Slf4j;

/**
 * @author xiahaifeng
 */
@Slf4j
public class VictorianChair implements Chair {

    @Override
    public void sitOn() {
        log.info("坐在维多利亚风格的椅子上");
    }
}