package com.example.springboot.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author yewub
 * 使@ConfigurationProperties注解生效
 * 在配置类中或启动类加@EnableConfigurationProperties(ApplicationProperties.class)
 * 和@Component
 */

@Component
@ConfigurationProperties(prefix = "application")
public class ApplicationProperties {

    private String bankBillUrl;

    private final TaskThreadPool taskThreadPool = new TaskThreadPool();

    public String getBankBillUrl() {
        return bankBillUrl;
    }

    public void setBankBillUrl(String bankBillUrl) {
        this.bankBillUrl = bankBillUrl;
    }

    public TaskThreadPool getTaskThreadPool() {
        return taskThreadPool;
    }

    /**
     *  线程池配置类
     */
    public static class TaskThreadPool {
        private int corePoolSize;

        private int maxPoolSize;

        private int keepAliveSeconds;

        public int getCorePoolSize() {
            return corePoolSize;
        }

        public void setCorePoolSize(int corePoolSize) {
            this.corePoolSize = corePoolSize;
        }

        public int getMaxPoolSize() {
            return maxPoolSize;
        }

        public void setMaxPoolSize(int maxPoolSize) {
            this.maxPoolSize = maxPoolSize;
        }

        public int getKeepAliveSeconds() {
            return keepAliveSeconds;
        }

        public void setKeepAliveSeconds(int keepAliveSeconds) {
            this.keepAliveSeconds = keepAliveSeconds;
        }
    }

}
