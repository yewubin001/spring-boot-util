//package com.example.springboot.jarslink;
//
//imports com.alipay.jarslink.api.Module;
//imports com.alipay.jarslink.api.ModuleConfig;
//imports com.alipay.jarslink.api.ModuleLoader;
//imports com.alipay.jarslink.api.ModuleManager;
//imports com.alipay.jarslink.api.impl.AbstractModuleRefreshScheduler;
//imports com.google.common.base.Splitter;
//imports com.sun.javafx.PlatformUtil;
//imports org.apache.commons.io.FileUtils;
//imports org.slf4j.Logger;
//imports org.slf4j.LoggerFactory;
//imports org.springframework.core.env.ConfigurableEnvironment;
//imports org.springframework.stereotype.Component;
//
//imports java.io.File;
//imports java.io.IOException;
//imports java.net.URL;
//imports java.util.*;
//
///**
// * 加载jarslink模块
// *
// * @author 李少华
// */
//@Component
//public class ModuleRefreshSchedulerImpl extends AbstractModuleRefreshScheduler {
//
//    private static final Splitter NAMESPACE_SPLITTER = Splitter.on(",").omitEmptyStrings().trimResults();
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(ModuleRefreshSchedulerImpl.class);
//    public static final String PLUGIN_CONFIG_PREFIX = "application-";
//
//    private ModuleManager moduleManager;
//
//    private Map<String, String> modulesMap = new HashMap<>();
//
//
//    public ModuleRefreshSchedulerImpl(ModuleLoader moduleLoader, ModuleManager moduleManager, ConfigurableEnvironment environment) {
//        super.setModuleLoader(moduleLoader);
//        super.setModuleManager(moduleManager);
//        this.moduleManager = moduleManager;
//        this.setRefreshDelay(Integer.MAX_VALUE);
//    }
//
//    @Override
//    public List<ModuleConfig> queryModuleConfigs() {
//        return loadModuleConfigs();
//    }
//
//    private List<ModuleConfig> loadModuleConfigs() {
//        List<ModuleConfig> moduleConfigs = new ArrayList<>();
//        try {
//            for (File plugin : getPlugins()) {
//                String fileName = plugin.getName();
//                String absoluteFilePathName = plugin.getAbsolutePath();
//                String fileProtocol = getFileProtocol();
//                URL moduleUrl = new URL(fileProtocol + absoluteFilePathName);
//                String[] split = fileName.split("-");
//                String moduleName = split[0];
//                String version = split[1].replaceAll("\\.jar", "");
//                ModuleConfig moduleConfig = buildModuleConfig(moduleName, version, moduleUrl);
//                moduleConfigs.add(moduleConfig);
//                modulesMap.put(moduleName, version);
//            }
//        } catch (IOException e) {
//            LOGGER.error("构造模块错误，异常如下：", e);
//        }
//        return moduleConfigs;
//    }
//
//
//    private String getFileProtocol() {
//        String fileProtocol = "file://";
//        if (PlatformUtil.isWindows()) {
//            fileProtocol = "file:/";
//        }
//        return fileProtocol;
//    }
//
//    private Collection<File> getPlugins() throws IOException {
//        File packagePathDir = FileUtils.getFile("C:\\Users\\yewub");
//        return FileUtils.listFiles(packagePathDir, new String[]{"jar"}, false);
//    }
//
//
//    private ModuleConfig buildModuleConfig(String moduleName, String version, URL moduleUrl) {
//        return new ModuleConfig()
//                .withName(moduleName)
//                .withVersion(version)
//                .withNeedUnloadOldVersion(true)
//                .addModuleUrl(moduleUrl)
//                .addScanPackage("com.plugin." + moduleName);
//    }
//
//    @Override
//    public void run() {
//        List<Module> modules = moduleManager.getModules();
//        for (Module module : modules) {
//            moduleManager.remove(module.getName(), module.getVersion());
//            module.destroy();
//        }
//        super.run();
//        activeNewModules();
//    }
//
//    private void refreshModule(Module module) {
//        moduleManager.remove(module.getName(), module.getVersion());
//        module.destroy();
//        super.run();
//        activeNewModules();
//    }
//
//    private void activeNewModules() {
//        Set<String> keys = modulesMap.keySet();
//        for (String moduleName : keys) {
//            String version = modulesMap.get(moduleName);
//            this.moduleManager.activeVersion(moduleName, version);
//        }
//    }
//
//}
