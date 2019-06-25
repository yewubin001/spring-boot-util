package com.example.springboot.design.strategy.risk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Auther: yewub
 * @Date: 2019/5/13 14:35
 * @Description:
 */
@Component
public class RiskAuditContext {

    @Autowired
    private Map<String, RiskAuditStrategy> strategy = new ConcurrentHashMap<>();

    public RiskAuditStrategy getStrategy(String type) {
        return this.strategy.get(type + "_RISK_AUDIT");
    }
}
