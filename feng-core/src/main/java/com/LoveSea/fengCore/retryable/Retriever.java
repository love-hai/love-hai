package com.LoveSea.fengCore.retryable;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

public interface Retriever {
    int defaultRetryCount = 3;
    long defaultSleepTime = 1000;

    // 设置备注
    void setRemark(String remark);
    // 设置打印备注的方法
    void setPrintRemark(BiConsumer<Integer, String> printRemarkMethod);

    // 设置需要重试的异常
    void setRetryIfException(Class<? extends Exception> e);

    void setRetryIfState(Supplier<Boolean> state);

    void setRetryIfException(Class<? extends Exception> e, RetryLevel level);

    void setRetryIfState(Supplier<Boolean> state, RetryLevel level);

    // 设置重试间隔
    void setSleepTime(long sleepTime);

    // 设置重试次数
    void setCount(int retryCount);
}
