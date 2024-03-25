package com.zeng.conf.data;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.zeng.framework.constant.data.DataSourceConstant;
import com.zeng.framework.sotre.StoreAllDataSource;
import com.zeng.framework.sotre.StoreDynamicDataSource;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
// 排除內部，加入自己的一個 操作
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
public class DataSourceConf {

    // Bean 进行命名
    // ConfigurationProperties 进行将 master 配置自动注入到 new DataSource() 对象中
    @Bean(DataSourceConstant.MASTER)
    @ConfigurationProperties("spring.datasource.dynamic.datasource.master")
    public DataSource master() { return DruidDataSourceBuilder.create().build(); }
    // Bean 进行命名
    // ConfigurationProperties 进行将 one 配置自动注入到 new DataSource() 对象中
    @Bean(DataSourceConstant.MULTI_DB_1)
    @ConfigurationProperties("spring.datasource.dynamic.datasource.multi-one")
    public DataSource db_1() { return DruidDataSourceBuilder.create().build(); }
    // Bean 进行命名
    // ConfigurationProperties 进行将 two 配置自动注入到 new DataSource() 对象中
    @Bean(DataSourceConstant.MULTI_DB_2)
    @ConfigurationProperties("spring.datasource.dynamic.datasource.multi-two")
    public DataSource db_2() { return DruidDataSourceBuilder.create().build(); }

    @Bean
    @Primary
    public StoreAllDataSource targetDataSource() {
        StoreAllDataSource source = new StoreAllDataSource();
        source.setBean(DataSourceConstant.MASTER, master());
        source.setBean(DataSourceConstant.MULTI_DB_1, db_1());
        source.setBean(DataSourceConstant.MULTI_DB_2, db_2());
        return source;
    }

    @Bean
    @Primary
    public StoreDynamicDataSource dataSource(StoreAllDataSource allDataSource) {
        StoreDynamicDataSource source = new StoreDynamicDataSource();
        source.setTargetDataSources(allDataSource.dataSourceMap);
        return source;
    }

}
