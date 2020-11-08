package com.example.springboot.imports;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @Auther: 59315
 * @Date: 2020/5/3 23:41
 * @Description: 
 */
public class MyImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{"com.example.springboot.imports.Triangle"};
    }
}
