package com.lovehai.helloSecurityApi.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;

import java.util.Base64;

/**
 * erp 发送请求前的拦截器
 *
 * @author xiahaifeng
 */
@Slf4j
public class SecurityRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        // 添加 basic Auth
        // 设置基本认证用户名和密码
        String username = "xiahaifeng";
        String password = "xhf383818";

        // 创建 basic auth 字符串并进行 Base64 编码
        String auth = username + ":" + password;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());

        // 添加 Authorization 头
        template.header("Authorization", "Basic " + encodedAuth);
    }
}