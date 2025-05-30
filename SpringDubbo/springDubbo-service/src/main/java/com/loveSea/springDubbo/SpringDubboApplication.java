package com.loveSea.springDubbo;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
@MapperScan("com.loveSea.springDubbo.mybatis.mapper")
public class SpringDubboApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDubboApplication.class, args);
    }

}
