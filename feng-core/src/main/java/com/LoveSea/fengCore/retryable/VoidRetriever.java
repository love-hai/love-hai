package com.LoveSea.fengCore.retryable;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

/**
 * @author xiahaifeng
 */

public interface VoidRetriever {

    void run() throws Exception;

    VoidRetriever remark(String remark);

    VoidRetriever retryIfError();

    // 设置打印备注的方法
    VoidRetriever printRemark(BiConsumer<Integer, String> printRemarkMethod);

    // 设置需要重试的异常
    VoidRetriever retryIfException(Class<? extends Exception> exception);

    VoidRetriever retryIfException(Class<? extends Exception> e, int level);

    VoidRetriever retryIfState(Supplier<Boolean> state, int level);

    VoidRetriever retryIfState(Supplier<Boolean> retryIfStates);

    VoidRetriever noRetryIfException(Class<? extends Exception> exception);

    VoidRetriever noRetryIfException(Class<? extends Exception> e, int level);

    VoidRetriever noRetryIfState(Supplier<Boolean> state, int level);

    VoidRetriever noRetryIfState(Supplier<Boolean> retryIfStates);

    // 设置执行方法
    VoidRetriever accept(ReFunMethod reFunObMethod);

    // 设置重试间隔
    VoidRetriever sleep(long sleepTime);

    // 设置重试次数
    VoidRetriever count(int retryCount);
}