package com.LoveSea.fengCore.retryable;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author xiahaifeng
 */

public class ObRetrieverImpl<R> extends AbstractRetriever implements ObRetriever<R> {

    ObRetrieverImpl() {
        super();
    }

    private ReFunObMethod<R> reFunMethod;
    private boolean setOrResulted = false;
    private R orReturn;
    // 出现这些结果需要重试
    private List<R> retryIfResults;
    // 符合这些条件需要重试
    private List<Predicate<R>> retryIfStateOfResults;

    private List<R> noRetryIfResults;

    private List<Predicate<R>> noRetryIfStateOfResults;

    @Override
    public ObRetriever<R> retryIfError() {
        return this.retryIfException(Exception.class);
    }

    @Override
    // 设置需要重试的结果
    public ObRetriever<R> retryIfResult(R retryIfResult) {
        return retryIfResult(retryIfResult, DefaultCommonLevel);
    }

    @Override
    public ObRetriever<R> retryIfResult(R retryIfResult, RetryLevel level) {
        if (retryIfResult == null) return this;
        if (null == retryIfResults) retryIfResults = new ArrayList<>();
        this.retryIfResults.add(retryIfResult);
        setRetryLevel(retryIfResult, level);
        return this;
    }

    // 设置需要重试的 结果符合的条件
    @Override
    public ObRetriever<R> retryIfResult(Predicate<R> retryIfState) {
        return retryIfResult(retryIfState, DefaultCommonLevel);
    }

    @Override
    public ObRetriever<R> retryIfResult(Predicate<R> retryIfState, RetryLevel level) {
        if (null == retryIfState) return this;
        if (null == retryIfStateOfResults) retryIfStateOfResults = new ArrayList<>();
        this.retryIfStateOfResults.add(retryIfState);
        setRetryLevel(retryIfState, level);
        return this;
    }

    @Override
    public ObRetriever<R> retryIfState(Supplier<Boolean> retryIfState) {
        super.setRetryIfState(retryIfState);
        return this;
    }

    @Override
    public ObRetriever<R> retryIfException(Class<? extends Exception> e, RetryLevel level) {
        super.setRetryIfException(e, level);
        return this;
    }

    @Override
    public ObRetriever<R> noRetryIfResult(R noRetryIfResult) {
        return noRetryIfResult(noRetryIfResult, DefaultCommonLevel);
    }

    @Override
    public ObRetriever<R> noRetryIfResult(R noRetryIfResult, RetryLevel level) {
        if (null == noRetryIfResult) return this;
        if (null == noRetryIfResults) noRetryIfResults = new ArrayList<>();
        noRetryIfResults.add(noRetryIfResult);
        setRetryLevel(noRetryIfResult, level);
        return this;
    }

    @Override
    public ObRetriever<R> noRetryIfResult(Predicate<R> noRetryIfState) {
        return noRetryIfResult(noRetryIfState, DefaultCommonLevel);
    }

    @Override
    public ObRetriever<R> noRetryIfResult(Predicate<R> noRetryIfState, RetryLevel level) {
        if (null == noRetryIfState) return this;
        if (null == noRetryIfStateOfResults) noRetryIfStateOfResults = new ArrayList<>();
        noRetryIfStateOfResults.add(noRetryIfState);
        setRetryLevel(noRetryIfState, level);
        return this;
    }

    @Override
    public ObRetriever<R> noRetryIfState(Supplier<Boolean> noRetryIfState) {
        super.setNoRetryIfState(noRetryIfState);
        return this;
    }

    @Override
    public ObRetriever<R> noRetryIfState(Supplier<Boolean> noRetryIfState, RetryLevel level) {
        super.setNoRetryIfState(noRetryIfState, level);
        return this;
    }

    @Override
    public ObRetriever<R> noRetryIfException(Class<? extends Exception> exception) {
        super.setNoRetryIfException(exception);
        return this;
    }

    @Override
    public ObRetriever<R> noRetryIfException(Class<? extends Exception> e, RetryLevel level) {
        super.setNoRetryIfException(e, level);
        return this;
    }

    @Override
    public ObRetriever<R> retryIfState(Supplier<Boolean> state, RetryLevel level) {
        super.setRetryIfState(state, level);
        return this;
    }

    // 执行
    @Override
    public R run() throws Exception {
        if (null == reFunMethod) {
            throw new IllegalArgumentException("reFunMethod不能为空");
        }
        try {
            RetryCount.pushRetryCount(0);
            if (retryCount <= 0) {
                return reFunMethod.run();
            }
            int count = 0;
            Exception exception = null;
            R result = null;
            while (count < retryCount) {
                if (Thread.currentThread().isInterrupted()) {
                    throw new InterruptedException();
                }
                // 是否需要重试
                retryFlag.reset();
                result = null;
                exception = null;
                try {
                    result = reFunMethod.run();
                    checkRetryIfResults(result);
                    checkRetryIfStateOfResults(result);
                    checkRetryIfState();
                    checkNoRetryIfResults(result);
                    checkNoRetryIfStateOfResults(result);
                    checkNoRetryIfState();
                    if (!retryFlag.isRetry()) {
                        return result;
                    }
                } catch (Exception e) {
                    exception = e;
                    checkRetryIfException(e);
                    checkNoRetryIfException(e);
                    if (!retryFlag.isRetry()) {
                        break;
                    }
                }
                this.sleep();
                count++;
                RetryCount.addRetryCount();
                this.printRemark(count);
            }
            if (setOrResulted) {
                return orReturn;
            }
            if (null != exception) {
                this.removeClassFromStackTrace(exception, this.getClass());
                throw exception;
            }
            return result;
        } finally {
            RetryCount.popRetryCount();
        }
    }

    // 设置如过不能执行，返回的结果
    @Override
    public ObRetriever<R> orReturn(R result) {
        setOrResulted = true;
        this.orReturn = result;
        return this;
    }

    // 设置备注
    @Override
    public ObRetriever<R> remark(String remark) {
        super.setRemark(remark);
        return this;
    }

    // 设置打印备注的方法
    @Override
    public ObRetriever<R> printRemark(BiConsumer<Integer, String> printRemarkMethod) {
        super.setPrintRemark(printRemarkMethod);
        return this;
    }

    @Override
    // 设置需要重试的异常
    public ObRetriever<R> retryIfException(Class<? extends Exception> exception) {
        super.setRetryIfException(exception);
        return this;
    }

    // 设置执行方法
    @Override
    public ObRetriever<R> accept(ReFunObMethod<R> reFunObMethod) {
        this.reFunMethod = reFunObMethod;
        return this;
    }

    // 设置重试间隔
    @Override
    public ObRetriever<R> sleep(long sleepTime) {
        super.setSleepTime(sleepTime);
        return this;
    }

    // 设置重试次数
    @Override
    public ObRetriever<R> count(int retryCount) {
        super.setCount(retryCount);
        return this;
    }

    // 检查是否需要重试
    private void checkRetryIfResults(R result) {
        if (CollectionUtils.isEmpty(retryIfResults)) return;
        for (R r : retryIfResults) {
            if (r.equals(result)) {
                retryFlag.setRetry(getRetryLevel(r));
            }
        }
    }

    private void checkRetryIfStateOfResults(R result) {
        if (CollectionUtils.isEmpty(retryIfStateOfResults)) return;
        for (Predicate<R> predicate : retryIfStateOfResults) {
            if (predicate.test(result)) {
                retryFlag.setRetry(getRetryLevel(predicate));
            }
        }
    }

    private void checkNoRetryIfResults(R result) {
        if (CollectionUtils.isEmpty(noRetryIfResults)) return;
        for (R r : retryIfResults) {
            if (r.equals(result)) {
                retryFlag.setNoRetry(getRetryLevel(r));
            }
        }
    }

    private void checkNoRetryIfStateOfResults(R result) {
        if (CollectionUtils.isEmpty(noRetryIfStateOfResults)) return;
        for (Predicate<R> predicate : noRetryIfStateOfResults) {
            if (predicate.test(result)) {
                retryFlag.setNoRetry(getRetryLevel(predicate));
            }
        }
    }
}