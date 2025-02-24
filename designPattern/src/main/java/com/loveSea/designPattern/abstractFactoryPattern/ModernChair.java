package com.loveSea.designPattern.abstractFactoryPattern;

import lombok.extern.slf4j.Slf4j;

/**
 * @author xiahaifeng
 */
@Slf4j
public class ModernChair  implements Chair {
    @Override
    public void sitOn() {
        log.info("坐在现代椅子上");
    }
}