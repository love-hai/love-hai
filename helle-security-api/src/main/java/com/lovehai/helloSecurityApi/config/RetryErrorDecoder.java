package com.lovehai.helloSecurityApi.config;

import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xiahaifeng
 */
@Slf4j
public class RetryErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        int status = response.status();
        if ((100 <= status && status < 200) || (300 <= status && status <= 600)) {
            log.error("RetryErrorDecoder: status={}, reason={}", response.status(), response.reason());
            return new RetryableException(response.status(), response.reason(), response.request().httpMethod(), (Long) null, response.request());
        }
        return null;
    }
}