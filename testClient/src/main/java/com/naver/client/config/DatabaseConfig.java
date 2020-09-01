package com.naver.client.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/*
 * repo를 Bean에다가 등록한다.
 * Database 암호화를 위해서 Spring Security의 PasswordEncolder Bean을 등록한다.
 */

@Configuration
@MapperScan(basePackages = "com.naver.client.repo")
public class DatabaseConfig {


}
