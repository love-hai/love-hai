package com.LoveSea.fengCore.retryable;

import lombok.Data;

import java.util.List;

/**
 * @author xiahaifeng
 */
@Data
public class RetryMethod {
    String className;
    String methodName;
    List<String> parameters;
    int maxRetries;
    long delay;
}
