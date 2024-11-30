package com.LoveSea.fengCore.callback.internal;

import com.LoveSea.fengCore.callback.api.CallBackParams;
import com.LoveSea.fengCore.callback.api.CallbackAction;
import com.LoveSea.fengCore.callback.api.CallbackGroup;
import com.LoveSea.fengCore.callback.api.PACallback;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xiahaifeng
 */

public class CallbackGroupImpl<T extends Callback> implements CallbackGroup<T> {
    private final Map<Class<?>, Callback> CALLBACK_MAP = new ConcurrentHashMap<>();

    @Override
    public <E extends T> E set(Class<E> callback, E callbackInstance) {
        return (E) CALLBACK_MAP.put(callback, callbackInstance);
    }

    @Override
    public <E extends T> void run(Class<E> callback, CallBackParams params, CallbackAction action) {
        Callback callbackInstance = CALLBACK_MAP.get(callback);
        if(null == callbackInstance) {
            return;
        }
        if (callbackInstance instanceof PACallback paCallback) {
            paCallback.run(params, action);
        }
    }

    @Override
    public <E extends T> E remove(Class<E> callback) {
        return (E) CALLBACK_MAP.remove(callback);
    }

    @Override
    public <E extends T> E get(Class<E> callback) {
        return (E) CALLBACK_MAP.get(callback);
    }
}