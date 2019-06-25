//package com.example.springboot.jarslink;
//
//import com.alipay.jarslink.api.ModuleLoader;
//import com.alipay.jarslink.api.ModuleManager;
//import com.alipay.jarslink.api.ModuleService;
//import com.alipay.jarslink.api.impl.AbstractModuleRefreshScheduler;
//import com.alipay.jarslink.api.impl.ModuleLoaderImpl;
//import com.alipay.jarslink.api.impl.ModuleManagerImpl;
//import com.alipay.jarslink.api.impl.ModuleServiceImpl;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.DependsOn;
//import org.springframework.core.env.ConfigurableEnvironment;
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
