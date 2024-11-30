package com.LoveSea.fengCore.callback.api;

import com.LoveSea.fengCore.callback.internal.Callback;
import com.LoveSea.fengCore.callback.internal.CallbackGroupImpl;

/**
 * @author xiahaifeng
 */

public interface CallbackGroup<C extends Callback> {
    <E extends C> E set(Class<E> callback, E callbackInstance);

    <E extends C> void run(Class<E> callback, CallBackParams params, CallbackAction action);

    <E extends C> E remove(Class<E> callback);

    <E extends C> E get(Class<E> callback);

    static <C extends Callback> CallbackGroup<C> of() {
        return new CallbackGroupImpl<>();
    }

}