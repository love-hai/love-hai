package com.LoveSea.fengCore.retryable;

import java.util.function.BiConsumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author xiahaifeng
 */

public interface ObRetriever<R> {
    ObRetriever<R> retryIfError();

    ObRetriever<R> retryIfResult(R retryIfResult);

    ObRetriever<R> retryIfResult(R retryIfResult, int level);

    ObRetriever<R> retryIfResult(Predicate<R> retryIfState);

    ObRetriever<R> retryIfResult(Predicate<R> retryIfState, int level);

    ObRetriever<R> retryIfState(Supplier<Boolean> retryIfState);

    ObRetriever<R> retryIfState(Supplier<Boolean> retryIfState, int level);

    ObRetriever<R> retryIfException(Class<? extends Exception> exception);

    ObRetriever<R> retryIfException(Class<? extends Exception> e, int level);

    ObRetriever<R> noRetryIfResult(R noRetryIfResult);

    ObRetriever<R> noRetryIfResult(R noRetryIfResult, int level);

    ObRetriever<R> noRetryIfResult(Predicate<R> noRetryIfState);

    ObRetriever<R> noRetryIfResult(Predicate<R> noRetryIfState, int level);

    ObRetriever<R> noRetryIfState(Supplier<Boolean> noRetryIfState);

    ObRetriever<R> noRetryIfState(Supplier<Boolean> noRetryIfState, int level);

    ObRetriever<R> noRetryIfException(Class<? extends Exception> exception);

    ObRetriever<R> noRetryIfException(Class<? extends Exception> e, int level);


    R run() throws Exception;

    // 设置如过不能执行，返回的结果
    ObRetriever<R> orReturn(R result);

    // 设置备注
    ObRetriever<R> remark(String remark);

    // 设置打印备注的方法
    ObRetriever<R> printRemark(BiConsumer<Integer, String> printRemarkMethod);

    // 设置执行方法
    ObRetriever<R> accept(ReFunMethod reFunObMethod);

    // 设置重试间隔
    ObRetriever<R> sleep(long sleepTime);

    // 设置重试次数
    ObRetriever<R> count(int retryCount);
}