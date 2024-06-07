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
    static String userName = "xiahaifeng";

    static String parentModule = "springDubbo\\";
    static String ServiceDir = parentModule+"springDubbo-service";
    // 父包名
    static String RelationSrcDir = "\\src\\main\\java";



    static String PackageName = "com.loveSea";

    static String RelationEntityDir = PackageName+".springDubbo.model.entity";

    static String RelationMapperDir = PackageName+".springDubbo.mybatis.mapper";

    static String RelationServiceDir = PackageName+".springDubbo.api";

    static String RelationServiceImplDir = PackageName+".springDubbo.service";

    static String RelationMapperXmlDir =PackageName+".springDubbo.xml.mappers";

    static String RelationControllerDir = PackageName+".springDubbo.controller";




    public static void main(String[] args) {
        // 在api模块生成代码
        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> builder.author(userName)
                        .outputDir(Paths.get(ServiceDir) + RelationSrcDir)
                        .commentDate("yyyy-MM-dd"))
                .packageConfig(builder -> builder.parent("")
                        .entity(RelationEntityDir)
                        .mapper(RelationMapperDir)
                        .service(RelationServiceDir)
                        .serviceImpl(RelationServiceImplDir)
                        .controller(RelationControllerDir)
                        .xml(RelationMapperXmlDir))

                .strategyConfig(builder -> builder.entityBuilder().enableLombok()).templateEngine(new FreemarkerTemplateEngine()).execute();
    }
}
