package com.loveSea.spring.framework.beans.config;

/**
 * 原生Spring中的BeanPostProcessor是为对象初始化事件设置的一种回调机制。这个Mini版本中只做说明
 * @author xiahaifeng
 */

public class LSBeanPostProcessor {
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception {
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws Exception {
        return bean;
    }
}