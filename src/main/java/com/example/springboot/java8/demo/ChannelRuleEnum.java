package com.example.springboot.java8.demo;

import java.util.function.Function;

public enum ChannelRuleEnum {

    TOUTIAO("TOUTIAO", new ToutiaoChannelRule()),
    TENCENT("TENCENT", new TencentChannelRule());

    public String code;

    public GeneralChannelRule rule;

    ChannelRuleEnum(String code, GeneralChannelRule rule) {
        this.code = code;
        this.rule = rule;
        Function<String, Integer> fun = Integer::parseInt;
    }

    public String getCode() {
        return code;
    }

    public GeneralChannelRule getRule() {
        return rule;
    }

    public static GeneralChannelRule match(String code) {
        for(ChannelRuleEnum rule : ChannelRuleEnum.values()) {
            if(code.equals(rule.code)) {
                return rule.getRule();
            }
        }
       return null;
    }

}
