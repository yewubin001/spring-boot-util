package com.example.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot项目的核心注解,主要是开启自动配置
 *ThreadPoolConfiguration
 * @author yewub
 * @SpringBootApplication 已经实现了扫描该包下的所有注解，不需要再@ComponentScan扫描注解
 */
@SpringBootApplication
public class SpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }

}
