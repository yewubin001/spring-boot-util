package com.example.springboot;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UTest {
    public static void main(String[] args) {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
//        System.out.println(sdf.format(new Date()));
//        UUID uuid = UUID.randomUUID();
//        System.out.println(uuid.toString().replace("-",""));
//
//        System.out.println("--------------------------");
//        double d = 0.178022995;
//        BigDecimal bd1 = BigDecimal.valueOf(d);
//        BigDecimal bd2 = new BigDecimal("0.178022995");
//        BigDecimal bd3 = new BigDecimal(0.178022995);//精度损失
//        System.out.println(bd1);
//        System.out.println(bd2);
//        System.out.println(bd3);


//        Map<String, Object> applyInfo = new HashMap<>();
//        applyInfo.put("idfa", "32BD9B39-BE34-4236-ACC3-1143FA85BDC2");
//        applyInfo.put("ip", "119.137.54.115");
//        applyInfo.put("point", "pp296001340475191296");
//        System.out.println(JSONObject.toJSONString(applyInfo));
//
//        System.out.println(Base64.encodeBase64String("111哈哈哈wwwww%-_+ ,,,'".getBytes()));
//


        System.out.println(System.getProperty("user.home"));
    }
}