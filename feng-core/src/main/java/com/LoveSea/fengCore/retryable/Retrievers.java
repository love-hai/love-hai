package com.LoveSea.fengCore.retryable;

import lombok.extern.slf4j.Slf4j;

/**
 * <p><b>Retriever 说明</b></p>
 * <p> 重试器虽然不能解决需要对一个方法时进行重试时，写多余的代码，
 * 但是可以让这个重试的过程和结果更加清晰，更加容易维护。使用效果还可以。
 * 让一些复杂的重试逻辑，变得更加易于理解
 * </p>
 * <ul>
 *     <li>1. {@link #of(ReFunObMethod)} 传入一个有返回值方法，返回一个ObRetriever对象</li>
 *     <li>2. {@link #of(ReFunVoidMethod) }传入一个没有返回值的方法，返回一个VoidRetriever对象</li>
 * </ul>
 *
 * @author xiahaifeng
 */
@Slf4j
public class Retrievers {

    public static <R> ObRetriever<R> of(ReFunObMethod<R> reFunObMethod) {
        ObRetriever<R> obRetriever = new ObRetrieverImpl<>();
        return obRetriever.accept(reFunObMethod);
    }

    public static VoidRetriever of(ReFunVoidMethod reFunMethod) {
        VoidRetriever voidRetriever = new VoidRetrieverImpl();
        return voidRetriever.accept(reFunMethod);
    }
}