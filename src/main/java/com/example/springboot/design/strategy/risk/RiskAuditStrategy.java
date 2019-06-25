package com.example.springboot.design.strategy.risk;

/**
 * @Auther: yewub
 * @Date: 2019/5/13 14:17
 * @Description: 决策结果策略
 */
public interface RiskAuditStrategy {

    void riskMessageHandler(String jsonMsg);
}
