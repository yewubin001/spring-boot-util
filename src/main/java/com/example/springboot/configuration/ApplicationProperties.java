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

    private final Credit flow = new Credit();

    public Credit getFlow() {
        return flow;
    }

    public static class Credit {
        private String bankBillUrl;

        public String getBankBillUrl() {
            return bankBillUrl;
        }

        public void setBankBillUrl(String bankBillUrl) {
            this.bankBillUrl = bankBillUrl;
        }
    }

}
