package com.LoveSea.fengCore.retryable;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
public @interface Retryable {
    // 最大重试次数
    int maxRetries() default 3;

    long delay() default 1000L;
}
