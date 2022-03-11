package com.example.springboot.tools.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.DecimalFormat;

/**
 * 用户将double类型的数据格式化成小数点后两位的字符串数据：如输出为“900.00”.
 *
 * @author Shine
 */

public class CustomDoubleSerialize extends JsonSerializer<BigDecimal> {

    /**
     * 定义金额格式
     */
    private DecimalFormat df = new DecimalFormat("##.00");

    public CustomDoubleSerialize() {

    }

    @Override
    public void serialize(BigDecimal value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
        jgen.writeString(this.df.format(value));
    }
}
