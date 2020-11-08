package com.example.springboot.design.strategy.risk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Auther: yewub
 * @Date: 2019/5/13 14:40
 * @Description: 贸e贷决策结果处理
 */
@Service("AT0007_RISK_AUDIT")
@Transactional
public class RiskAuditMaoeDaiService implements RiskAuditStrategy {

    private final Logger log = LoggerFactory.getLogger(RiskAuditMaoeDaiService.class);

    @Override
    public void riskMessageHandler(String jsonMsg) {
        log.info("湛泸决策返回结果 json={}", jsonMsg);
        //TODO
        log.info("湛泸决策json={}处理完成----->", jsonMsg);
    }


}
