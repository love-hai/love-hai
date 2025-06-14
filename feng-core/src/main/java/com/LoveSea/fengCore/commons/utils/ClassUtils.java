package com.LoveSea.fengCore.commons.utils;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author xiahaifeng
 */

public class ClassUtils {

    public static ParameterizedType createParameterizedType(Class<?> rawType, Type... actualTypeArguments) {
        return new InnerParameterizedType(rawType, actualTypeArguments);
    }

    private record InnerParameterizedType(Class<?> rawType, Type... actualTypeArguments) implements ParameterizedType {

        @Override
            public Type @NotNull [] getActualTypeArguments() {
                return actualTypeArguments;
            }

            @Override
            public @NotNull Type getRawType() {
                return rawType;
            }

            @Override
            public Type getOwnerType() {
                return null;
            }
        }

}