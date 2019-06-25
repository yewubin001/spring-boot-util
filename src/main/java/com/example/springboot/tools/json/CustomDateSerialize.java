package com.example.springboot.tools.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间序列化工具,直接将时间类型的转为yyyy-MM-dd类型的数据
 * 使用方法:在实体的get方法上直接加@JsonSerialize(using = CustomDateSerialize.class)
 * @author Shine
 */
public class CustomDateSerialize extends JsonSerializer<Date> {

    /**
     * 定义时间格式
     */
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeString(sdf.format(date));
    }
}
