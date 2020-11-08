//package com.example.springboot.jarslink;
//
//imports com.alipay.jarslink.api.ModuleLoader;
//imports com.alipay.jarslink.api.ModuleManager;
//imports com.alipay.jarslink.api.ModuleService;
//imports com.alipay.jarslink.api.impl.AbstractModuleRefreshScheduler;
//imports com.alipay.jarslink.api.impl.ModuleLoaderImpl;
//imports com.alipay.jarslink.api.impl.ModuleManagerImpl;
//imports com.alipay.jarslink.api.impl.ModuleServiceImpl;
//imports org.springframework.context.annotation.Bean;
//imports org.springframework.context.annotation.Configuration;
//imports org.springframework.context.annotation.DependsOn;
//imports org.springframework.core.env.ConfigurableEnvironment;
//
//@Configuration
//public class JarslinkConfig {
//
//    @Bean
//    public ModuleLoader moduleLoader() {
//        return new ModuleLoaderImpl();
//    }
//
//    @Bean
//    public ModuleManager moduleManager() {
//        return new ModuleManagerImpl();
//    }
//
//    @Bean
//    @DependsOn(value = { "moduleLoader", "moduleManager" })
//    public ModuleService moduleService(ModuleLoader moduleLoader, ModuleManager moduleManager) {
//        ModuleServiceImpl moduleService = new ModuleServiceImpl();
//        moduleService.setModuleLoader(moduleLoader);
//        moduleService.setModuleManager(moduleManager);
//        return moduleService;
//    }
//
//    @Bean
//    @DependsOn(value = { "moduleService" })
//    public AbstractModuleRefreshScheduler moduleRefreshSchedulerImpl(ModuleLoader moduleLoader, ModuleManager moduleManager, ConfigurableEnvironment environment) {
//        return new ModuleRefreshSchedulerImpl(moduleLoader, moduleManager, environment);
//    }
//
//}
