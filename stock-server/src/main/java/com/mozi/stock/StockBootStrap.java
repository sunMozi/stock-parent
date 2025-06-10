package com.mozi.stock;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zby
 * @created 2024-06-21 22:28
 * @description 引导类
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.mozi.stock.mapper"})
public class StockBootStrap {

    public static void main(String[] args) {
        SpringApplication.run(StockBootStrap.class, args);
    }

}