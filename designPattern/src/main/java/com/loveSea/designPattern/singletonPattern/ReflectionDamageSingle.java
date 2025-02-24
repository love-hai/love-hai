package com.loveSea.designPattern.singletonPattern;

/**
 * @author xiahaifeng
 */

public class ReflectionDamageSingle {
    private static ReflectionDamageSingle reflectionDamageSingle;
    private ReflectionDamageSingle() {
        if(null != reflectionDamageSingle) {
            throw new RuntimeException("ReflectionDamageSingle is created");
        }
    }

    public static ReflectionDamageSingle getInstance() {
        return reflectionDamageSingle;
    }
}