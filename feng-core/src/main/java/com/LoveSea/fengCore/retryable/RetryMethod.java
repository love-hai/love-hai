package com.LoveSea.fengCore.retryable;

import lombok.Data;

import java.util.List;

/**
 * @author xiahaifeng
 */
@Data
public class RetryMethod {
    String methodName;
    String className;
    int maxRetries;
    long delay;
    List<String> parameterTypes;
}