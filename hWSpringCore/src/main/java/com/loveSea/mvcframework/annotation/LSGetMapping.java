package com.loveSea.mvcframework.annotation;

import com.loveSea.mvcframework.v1.servlet.RequestActionType;

import java.lang.annotation.*;

/**
 * @author xiahaifeng
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@LSRequestMapping(method = {RequestActionType.GET})
public @interface LSGetMapping {
    String value() default "";
}