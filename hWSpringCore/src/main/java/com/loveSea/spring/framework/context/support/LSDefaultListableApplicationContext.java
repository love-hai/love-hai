package com.loveSea.spring.framework.context.support;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xiahaifeng
 */

public class LSDefaultListableApplicationContext extends LSAbstractApplicationContext {
    protected final Map<String, LSBeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
}