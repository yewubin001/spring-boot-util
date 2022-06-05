package com.example.springboot.configuration;

import io.swagger.annotations.ApiOperation;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @ConditionalOnProperty 注解声明了当 springfox.documentation.enabled为true时启用配置，
 * 而且默认值就是true。这非常有用，Swagger仅仅建议在开发阶段使用，这个正好是个开关。
 * 另外有时候我们自定义配置的时候最好把这个开关也加上：
 *
 * 自定义swagger3文档信息
 */
@Configuration
@ConditionalOnProperty(value = "springfox.documentation.enabled", havingValue = "true", matchIfMissing = true)
public class Swagger3Config {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Swagger3接口文档")
                .description("更多请咨询felord.cn")
                .contact(new Contact("码农小胖哥", "https://felord.cn", "dax@felord.cn"))
                .version("1.0.0")
                .build();
    }
}