package com.LoveSea.SpringDubbo;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
@MapperScan("com.LoveSea.SpringDubbo.mybatis.mapper")
public class SpringDubboApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDubboApplication.class, args);
    }

}
