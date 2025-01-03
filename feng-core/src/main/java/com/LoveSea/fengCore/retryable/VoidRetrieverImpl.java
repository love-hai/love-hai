package com.LoveSea.fengCore.retryable;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

/**
 * @author xiahaifeng
 */

public class VoidRetrieverImpl extends AbstractRetriever implements VoidRetriever {

    private ReFunVoidMethod reFunMethod;

    public VoidRetrieverImpl() {
        super();
    }

    @Override
    public void run() throws Exception {
        if (null == reFunMethod) {
            throw new IllegalArgumentException("reFunMethod不能为空");
        }
        try {
            RetryCount.pushRetryCount(0);
            int count = 0;
            Exception exception = null;
            while (count <= retryCount) {
                if (Thread.currentThread().isInterrupted()) {
                    throw new InterruptedException();
                }
                retryFlag.reset();
                // 是否需要重试
                try {
                    reFunMethod.run();
                    this.checkRetryIfState();
                    this.checkNoRetryIfState();
                    if (!retryFlag.isRetry()) {
                        return;
                    }
                } catch (Exception e) {
                    exception = e;
                    this.checkRetryIfException(e);
                    this.checkNoRetryIfException(e);
                    if (!retryFlag.isRetry()) {
                        break;
                    }
                }
                this.sleep();
                count++;
                RetryCount.addRetryCount();
                this.printRemark(count);
            }
            if (null != exception) {
                this.removeClassFromStackTrace(exception, this.getClass());
                throw exception;
            }
        } finally {
            RetryCount.popRetryCount();
        }
    }

    @Override
    public VoidRetriever remark(String remark) {
        super.setRemark(remark);
        return this;
    }

    @Override
    public VoidRetriever retryIfError() {
        return this.retryIfException(Exception.class);
    }

    @Override
    public VoidRetriever retryIfState(Supplier<Boolean> retryIfStates) {
        super.setRetryIfState(retryIfStates);
        return this;
    }

    @Override
    public VoidRetriever noRetryIfException(Class<? extends Exception> exception) {
        super.setNoRetryIfException(exception);
        return this;
    }

    @Override
    public VoidRetriever noRetryIfException(Class<? extends Exception> e, RetryLevel level) {
        super.setNoRetryIfException(e, level);
        return this;
    }

    @Override
    public VoidRetriever noRetryIfState(Supplier<Boolean> state, RetryLevel level) {
        super.setNoRetryIfState(state, level);
        return this;
    }

    @Override
    public VoidRetriever noRetryIfState(Supplier<Boolean> retryIfStates) {
        super.setNoRetryIfState(retryIfStates);
        return this;
    }

    // 设置打印备注的方法
    @Override
    public VoidRetriever printRemark(BiConsumer<Integer, String> printRemarkMethod) {
        super.setPrintRemark(printRemarkMethod);
        return this;
    }

    // 设置需要重试的异常
    @Override
    public final VoidRetriever retryIfException(Class<? extends Exception> exception) {
        super.setRetryIfException(exception);
        return this;
    }

    @Override
    public VoidRetriever retryIfException(Class<? extends Exception> e, RetryLevel level) {
        super.setRetryIfException(e, level);
        return this;
    }

    @Override
    public VoidRetriever retryIfState(Supplier<Boolean> state, RetryLevel level) {
        super.setRetryIfState(state, level);
        return this;
    }

    // 设置执行方法
    @Override
    public VoidRetriever accept(ReFunVoidMethod reFunObMethod) {
        this.reFunMethod = reFunObMethod;
        return this;
    }

    // 设置重试间隔
    @Override
    public VoidRetriever sleep(long sleepTime) {
        super.setSleepTime(sleepTime);
        return this;
    }

    // 设置重试次数
    @Override
    public VoidRetriever count(int retryCount) {
        super.setCount(retryCount);
        return this;
    }
}