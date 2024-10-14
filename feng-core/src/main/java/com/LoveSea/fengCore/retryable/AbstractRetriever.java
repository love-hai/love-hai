package com.LoveSea.fengCore.retryable;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

/**
 * @author xiahaifeng
 */
@Slf4j
public abstract class AbstractRetriever implements Retriever {

    protected int CommonLevel = 0;

    RetryFlag retryFlag = new RetryFlag();
    //    重试等级
    private Map<Object, Integer> retryLevel;
    // 出现这些错误需要重试
    private List<Class<? extends Exception>> retryExceptions;
    private List<Supplier<Boolean>> retryIfStates;

    private List<Class<? extends Exception>> noRetryExceptions;
    private List<Supplier<Boolean>> noRetryIfStates;

    protected void setNoRetryIfState(Supplier<Boolean> state, int level) {
        if (null == state) return;
        if (null == noRetryIfStates) noRetryIfStates = new ArrayList<>();
        noRetryIfStates.add(state);
        setRetryLevel(state, level);
    }

    protected void setNoRetryIfState(Supplier<Boolean> state) {
        setNoRetryIfState(state, 0);
    }

    protected void setNoRetryIfException(Class<? extends Exception> e, int level) {
        if (e == null) return;
        if (null == noRetryExceptions) noRetryExceptions = new ArrayList<>();
        addException(noRetryExceptions, e);
        setRetryLevel(e, level);
    }

    protected void setNoRetryIfException(Class<? extends Exception> e) {
        setNoRetryIfException(e, 0);
    }

    static void addException(List<Class<? extends Exception>> exceptions, Class<? extends Exception> e) {
        if (null == e) return;
        if (null == exceptions) return;
        for (int i = 0; i < exceptions.size(); i++) {
            Class<? extends Exception> noRetryException = exceptions.get(i);
            // 如果 e extends noRetryException, 则不添加
            if (noRetryException.isAssignableFrom(e)) {
                break;
            }
            // 如果 noRetryException extends e, 则替换
            if (e.isAssignableFrom(noRetryException)) {
                exceptions.set(i, e);
                break;
            }
        }
        exceptions.add(e);
    }


    protected void setRetryLevel(Object key, Integer level) {
        if (null == retryLevel) retryLevel = new ConcurrentHashMap<>();
        retryLevel.put(key, level);
    }

    protected Integer getRetryLevel(Object key) {
        if (null == retryLevel) return -1;
        Integer level = retryLevel.get(key);
        return null == level ? -1 : level;
    }

    // 重试次数
    int retryCount = 3;
    // 重试间隔
    Long sleepTime = 1000L;
    // 打印备注的方法
    private BiConsumer<Integer, String> printRemarkMethod;
    // 备注
    private String remark;
    ReFunMethod reFunMethod;

    AbstractRetriever() {
        // 默认，重试时打印的方法
        printRemarkMethod = (count, remark) -> log.info("{} 准备第{}次重试", null == remark ? "" : "，" + remark, count);
    }

    // 设置备注
    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    // 设置打印备注的方法
    public void setPrintRemark(BiConsumer<Integer, String> printRemarkMethod) {
        this.printRemarkMethod = printRemarkMethod;
    }

    // 设置需要重试的异常
    @Override
    public void setRetryIfException(Class<? extends Exception> e) {
        setRetryIfException(e, CommonLevel);
    }

    @Override
    public void setRetryIfException(Class<? extends Exception> e, int level) {
        if (e == null) return;
        if (null == retryExceptions) retryExceptions = new ArrayList<>();
        addException(retryExceptions, e);
        setRetryLevel(e, level);
    }

    @Override
    public void setRetryIfState(Supplier<Boolean> state, int level) {
        if (null == state) return;
        if (null == retryIfStates) retryIfStates = new ArrayList<>();
        retryIfStates.add(state);
        setRetryLevel(state, level);
    }

    @Override
    public void setRetryIfState(Supplier<Boolean> state) {
        setRetryIfState(state, CommonLevel);
    }


    @Override
    // 设置执行方法
    public void setReFunMethod(ReFunMethod reFunObMethod) {
        this.reFunMethod = reFunObMethod;
    }

    @Override
    // 设置重试间隔
    public void setSleepTime(long sleepTime) {
        this.sleepTime = sleepTime;
    }

    @Override
    // 设置重试次数
    public void setCount(int retryCount) {
        this.retryCount = retryCount;
    }

    // 重试时打印的方法
    protected void printRemark(int count) {
        if (null == printRemarkMethod) {
            return;
        }
        printRemarkMethod.accept(count, remark);
    }

    // 线程休眠时间，重试间隔
    protected void sleep() {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            log.error("线程休眠失败", e);
            throw new RuntimeException("线程休眠失败");
        }
    }

    void checkRetryIfException(Exception e) {
        if (CollectionUtils.isEmpty(retryExceptions)) return;
        for (Class<? extends Exception> retryException : retryExceptions) {
            if (retryException.isAssignableFrom(e.getClass())) {
                retryFlag.setRetry(getRetryLevel(retryException));
            }
        }
    }

    void checkNoRetryIfException(Exception e) {
        if (CollectionUtils.isEmpty(noRetryExceptions)) return;
        for (Class<? extends Exception> noRetryException : noRetryExceptions) {
            if (noRetryException.isAssignableFrom(e.getClass())) {
                retryFlag.setNoRetry(getRetryLevel(noRetryException));
            }
        }
    }

    void checkRetryIfState() {
        if (CollectionUtils.isEmpty(retryIfStates)) return;
        for (Supplier<Boolean> retryIfState : retryIfStates) {
            if (retryIfState.get()) {
                retryFlag.setRetry(getRetryLevel(retryIfState));
            }
        }
    }

    void checkNoRetryIfState() {
        if (CollectionUtils.isEmpty(noRetryIfStates)) return;
        for (Supplier<Boolean> noRetryIfState : noRetryIfStates) {
            if (noRetryIfState.get()) {
                retryFlag.setNoRetry(getRetryLevel(noRetryIfState));
            }
        }
    }


    public static class RetryFlag {
        private Boolean retry = false;
        private int level = Integer.MIN_VALUE;

        public boolean isRetry() {
            return retry;
        }

        public void reset() {
            retry = false;
            level = Integer.MIN_VALUE;
        }

        public void setNoRetry(int level) {
            if (level >= this.level) {
                this.level = level;
                this.retry = false;
            }
        }

        public void setRetry(int level) {
            if (level > this.level) {
                this.level = level;
                this.retry = true;
            }
        }
    }
}