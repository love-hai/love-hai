package com.loveSea.mvcframework.annotation;

import com.loveSea.mvcframework.v1.servlet.RequestActionType;

import java.lang.annotation.*;

/**
 * @author xiahaifeng
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LSRequestMapping {
    String value() default "";

    RequestActionType[] method() default {};
}