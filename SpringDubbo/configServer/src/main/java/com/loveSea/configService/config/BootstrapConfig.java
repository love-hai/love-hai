package com.loveSea.configService.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author xiahaifeng
 */

@Configuration
@PropertySource("classpath:bootstrap.yml")
public class BootstrapConfig {
    // 配置文件加载完成后，可以在这里定义一些相关的配置属性
}
