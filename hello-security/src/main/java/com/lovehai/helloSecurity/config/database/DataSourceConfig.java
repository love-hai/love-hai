package com.lovehai.helloSecurity.config.database;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @author xiahaifeng
 */

@Configuration
public class DataSourceConfig {

    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.lovesea")
    @Bean(name = "loveSeaDataSource")
    public DataSource loveSeaDataSource() {
        return DataSourceBuilder.create().build();
    }
}
