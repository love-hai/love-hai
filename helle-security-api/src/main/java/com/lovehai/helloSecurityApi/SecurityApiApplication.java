package com.lovehai.helloSecurityApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author xiahaifeng
 */
@SpringBootApplication
@EnableFeignClients(basePackages = "com.lovehai.helloSecurityApi.client")
@ComponentScan(basePackages = {"com.lovehai.helloSecurityApi.config"
        , "com.lovehai.helloSecurityApi.auth"
        , "com.lovehai.helloSecurityApi.service"})
public class SecurityApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(SecurityApiApplication.class, args);
    }
}