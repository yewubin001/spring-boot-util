package com.example.springboot.imports;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @Auther: 59315
 * @Date: 2020/5/3 23:37
 * @Description: 
 */

@Import(value = {Circular.class, Square.class, MyImportSelector.class, MyImportBeanDefinitionRegistrar.class})
@Configuration
public class MainConfig {
}
