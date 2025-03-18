package com.lovehai.helloSecurity.config.database;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @author xiahaifeng
 */
@Configuration
@MapperScan(basePackages = "com.lovehai.helloSecurity.mapper", sqlSessionFactoryRef = "loveSeaSqlSessionFactory")
public class LocalDataSqlConfig {

    @Bean(name = "loveSeaSqlSessionFactory")
    public SqlSessionFactory loveSeaSqlSessionFactory(@Qualifier("loveSeaDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mappers/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "loveSeaSqlSessionTemplate")
    public SqlSessionTemplate loveSeaSqlSessionTemplate(@Qualifier("loveSeaSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
