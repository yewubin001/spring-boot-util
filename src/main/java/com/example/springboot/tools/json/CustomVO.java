package com.example.springboot.tools.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;

/**
 * @Auther: yewub
 * @Date: 2019/1/12 22:34
 * @Description:
 */
public class CustomVO {

    @JsonSerialize(
            using = CustomDoubleSerialize.class
    )
    @JsonProperty(value = "钱")
    private BigDecimal money;

    @JsonSerialize(using = CustomDateSerialize.class)
    @JsonProperty(value = "日期")
    private Date date;

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "CustomVO{" +
                "money=" + money +
                ", date='" + date + '\'' +
                '}';
    }
}
