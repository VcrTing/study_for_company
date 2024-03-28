package com.zeng.studing;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * APP
 *
 */
@SpringBootApplication(scanBasePackages = "com.zeng")
@MapperScan(basePackages = "com.zeng")
@EnableTransactionManagement
@EnableScheduling
public class App
{
    public static void main( String[] args )
    {

        SpringApplication.run(App.class);
    }
}