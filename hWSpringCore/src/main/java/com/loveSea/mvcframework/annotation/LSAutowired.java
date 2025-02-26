package com.loveSea.mvcframework.annotation;

import java.lang.annotation.*;

/**
 * @author xiahaifeng
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LSAutowired {
    String value() default "";
}