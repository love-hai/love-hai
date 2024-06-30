package com.LoveSea.fengCore.retryable;

/**
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
