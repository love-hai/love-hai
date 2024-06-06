package com.loveSea.springDubbo.mybatis;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.nio.file.Paths;

/**
 * mybatis plus代码生成器
 *
 * @author xiahaifeng
 */

public class CodeGenerator {
    static String url = "jdbc:mysql://127.0.0.1:3306/love_sea_base?useUnicode=true&characterEncoding=utf-8&useSSL=false";
    static String username = "root";
    static String password = "123456";

    static {
        System.setProperty("user.dir", "springDubbo-service");
    }

    public static void main(String[] args) {
        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> builder.author("xiahaifeng")
                        .outputDir(Paths.get(System.getProperty("user.dir")) + "\\src\\main\\java")
                        .commentDate("yyyy-MM-dd"))
                .packageConfig(builder -> builder.parent("com.loveSea.springDubbo")
                        .entity("entity")
                        .mapper("mapper")
                        .service("service")
                        .serviceImpl("service.impl")
                        .xml("mapper.xml"))
                .strategyConfig(builder -> builder.entityBuilder().enableLombok()).templateEngine(new FreemarkerTemplateEngine()).execute();
    }
}
