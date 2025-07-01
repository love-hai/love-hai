package com.lovehai.swagger.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xiahaifeng
 */

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API 文档")           // ✅ 默认标题
                        .version("v1.0.0")                  // ✅ 默认版本
                        .description("用于生成接口文档")  // ✅ 描述
                        .contact(new Contact()
                                .name("love-hai")
                                .email("xiahaifeng2000@gmail.com")
                        )
                )
                .addServersItem(new Server()
                        .url("http://127.0.0.1:8080")       // ✅ 默认服务器地址
                        .description("测试环境")
                )
                .addServersItem(new Server()
                        .url("http://116.62.151.13:8080")       // ✅ 默认服务器地址
                        .description("生产环境")
                )
                .components(new Components().addSecuritySchemes("Authorization",
                        new SecurityScheme()
                                .name("Authorization")
                                .type(SecurityScheme.Type.APIKEY)
                                .in(SecurityScheme.In.HEADER)
                ))
                .addSecurityItem(new SecurityRequirement().addList("Authorization"));  // ✅ 默认 Token 头
    }
}
