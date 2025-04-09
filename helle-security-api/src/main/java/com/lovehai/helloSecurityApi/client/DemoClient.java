package com.lovehai.helloSecurityApi.client;

import com.lovehai.helloSecurityApi.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author xiahaifeng
 */
@FeignClient(name = "demoClient", url = "${helloSecurity.gateway.url}",
        path = "/demo", configuration = FeignConfig.class)
public interface DemoClient {
    @GetMapping("/continuous/date")
    String continuousDate(@RequestParam("name") String name);
}