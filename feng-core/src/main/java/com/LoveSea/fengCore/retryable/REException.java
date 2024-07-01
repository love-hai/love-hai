package com.LoveSea.fengCore.retryable;

/**
 * 重试异常
 * @author xiahaifeng
 */

public class REException extends RuntimeException{

    public REException(String message) {
        super(message);
    }

    public REException(String message, Throwable cause) {
        super(message, cause);
    }
}
