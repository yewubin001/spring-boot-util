package com.example.springboot.tools.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.math.BigDecimal;
/**
 * json金额反序列化
 *
 * @author yewubin
 */
public class BigDecimalDeserialize extends JsonDeserializer<BigDecimal> {

    @Override
    public BigDecimal deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException {
        if (jsonParser == null || jsonParser.getText() == null) {
            return null;
        }
        String s = jsonParser.getText();
        BigDecimal amount = new BigDecimal(StringUtils.isBlank(s) ? "0" : s);
        // 万元转元
        return amount.multiply(new BigDecimal("10000")).setScale(2, BigDecimal.ROUND_HALF_UP);
    }
}
