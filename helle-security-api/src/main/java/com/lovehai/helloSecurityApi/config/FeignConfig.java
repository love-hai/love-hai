package com.lovehai.helloSecurityApi.config;

import com.lalang.lalangapi.config.ResponseErrorRetryer;
import feign.Request;
import feign.RequestInterceptor;
import feign.Retryer;
import feign.codec.Decoder;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * @author xiahaifeng
 */
@Configuration
public class FeignConfig {


    @Bean
    public RequestInterceptor requestInterceptor() {
        return new SecurityRequestInterceptor();
    }

    @Bean
    public Decoder bizDataDecoder() {
        return new DataDecoder();
    }

    @Bean
    public Retryer retryer() {
        return new ResponseErrorRetryer();
    }

    @Bean
    public ErrorDecoder retryErrorDecoder() {
        return new RetryErrorDecoder();
    }

    @Bean
    public Request.Options options() {
        return new Request.Options(10L, TimeUnit.SECONDS, 60L, TimeUnit.SECONDS, true);
    }
}