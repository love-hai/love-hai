package com.loveSea.mvcframework.annotation;

import com.loveSea.mvcframework.v1.servlet.RequestActionType;

import java.lang.annotation.*;

/**
 * @author xiahaifeng
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@LSRequestMapping(method = {RequestActionType.PUT})
public @interface LSPutMapping {
    String value() default "";
}