package com.example.park.repository.config;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(value = { "com.example.park.repository.mysql.dao"})
public class MybatisPlusConfig {
}
