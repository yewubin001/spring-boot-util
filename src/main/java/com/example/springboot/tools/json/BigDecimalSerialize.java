package com.example.springboot.tools.json;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Objects;

/**
 * json金额序列化输出
 *
 * @author yewubin
 */
public class BigDecimalSerialize extends JsonSerializer<BigDecimal> {

    /**
     * 定义金额格式
     *  0和#都是占位符，但在不同的地方，作用不一样。
     *  0：  比实际数字的位数多，不足的地方用0补上。比实际数字的位数少：整数部分不改动，小数部分，四舍五入
     *  #：  比实际数字的位数多，不变。比实际数字的位数少：整数部分不改动，小数部分，四舍五入
     */
    private DecimalFormat format = new DecimalFormat("0.000000");

    @Override
    public void serialize(BigDecimal amount, JsonGenerator generator, SerializerProvider provider) throws IOException {
        // 元转万元
        amount = Objects.nonNull(amount) ?  amount.divide(new BigDecimal("10000"), 6, BigDecimal.ROUND_HALF_UP) : BigDecimal.ZERO;
        generator.writeString(format.format(amount));
    }
}
