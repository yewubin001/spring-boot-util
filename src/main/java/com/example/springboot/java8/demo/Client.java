package com.example.springboot.java8.demo;

/**
 * @version : 1.0
 * @Description: TODO
 * @Auther: ywb
 * @Date: 2020/11/24 14:53
 */
public class Client {

    public static void main(String[] args) {
        //这里我们模拟接收到的数据，其渠道为为TOUTIAO，来自头条的数据
        String sign = "TOUTIAO";
        GeneralChannelRule rule = null ;
        //根据对应渠道获取对应的具体规则实现类
        if (ChannelRuleEnum.TENCENT.getCode().equals(sign)) {
            rule = new TencentChannelRule();
        } else if (ChannelRuleEnum.TOUTIAO.getCode().equals(sign)) {
            rule = new ToutiaoChannelRule();
        } else {
            //匹配不到
        }
        //执行
        rule.process();


        System.out.println("CTO优化过后的代码---------减少if else");
        GeneralChannelRule match = ChannelRuleEnum.match(sign);
        match.process();
    }
}
