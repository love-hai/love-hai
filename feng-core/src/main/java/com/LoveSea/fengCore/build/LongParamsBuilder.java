package com.LoveSea.fengCore.build;

/**
 * 长参数构建器 限制参数的类型，避免返回的都是object对象
 *
 * @author xiahaifeng
 */

public interface LongParamsBuilder<T, B extends LongParamsBuilder<T, B>> {
    T build();
    B self();
}