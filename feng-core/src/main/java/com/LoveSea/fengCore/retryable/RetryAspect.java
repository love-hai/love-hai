package com.LoveSea.fengCore.retryable;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * @author xiahaifeng
 */

@Aspect
@Slf4j

public class RetryAspect {
    private RetryThreadLocal retryThreadLocal;

    @Around("@annotation(retryable)")
    public Object retry(ProceedingJoinPoint joinPoint, Retryable retryable) throws Throwable {
        // 最大重试次数
        int maxRetries = retryable.maxRetries();
        // 重试间隔时间
        long delay = retryable.delay();
        // 返回结果
        Object result = null;
        // 放入重试次数
        retryThreadLocal.pushRetryCount(-1);
        int retryCount = -1;
        REException reException;
        try {
            do {
                reException = null;
                // 重试次数+1
                retryThreadLocal.addRetryCount();
                retryCount++;
                // 执行方法
                try {
                    result = joinPoint.proceed();
                    break;
                } catch (REException e) {
                    log.info((0 == retryCount ? "" : "重试：" + retryCount) + e.getMessage());
                    reException = e;
                }
                // 等待时间
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    log.error("Thread sleep error", e);
                }
            } while (retryCount < maxRetries);
            if (null != reException) {
                // 如果重试次数用完，抛出异常，但是不能抛出重试异常
                throw new Exception(reException.getMessage(), reException.getCause());
            }
        } finally {
            // 弹出重试次数
            retryThreadLocal.popRetryCount();
        }
        return result;
    }
}
