package com.example.springboot.design.strategy.risk;

import org.junit.Test;

/**
 * @Auther: yewub
 * @Date: 2019/5/13 17:20
 * @Description:
 */
public class Consumer {

    private RiskAuditContext riskAuditContext;

    @Test
    public void riskHandler() {
        //获取对应资产方的处理策略方法
        RiskAuditStrategy strategy = riskAuditContext.getStrategy("AT0007");
        strategy.riskMessageHandler("");
    }
}
