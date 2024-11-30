package com.LoveSea.fengCore.callback.api;

import com.LoveSea.fengCore.callback.internal.Callback;

import java.util.Optional;

/**
 * @author xiahaifeng
 */

public interface CallbackAble<C extends Callback> {
    <V extends C> C set(Class<V> vClazz, V callback);

    <V extends C> Optional<C> get(Class<V> vClazz);

    <V extends C> C remove(Class<V> vClazz);
}