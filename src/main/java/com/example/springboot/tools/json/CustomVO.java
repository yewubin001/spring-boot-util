package com.example.springboot.tools.json;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Auther: yewub
 * @Date: 2019/1/12 22:34
 * @Description:
 */
@ApiModel(description = "客户信息")
public class CustomVO {

    @JsonSerialize(using = BigDecimalSerialize.class)
    @JsonDeserialize(using = BigDecimalDeserialize.class)
    @ApiModelProperty(value = "金额")
    private BigDecimal money;

    @JsonSerialize(using = CustomDateSerialize.class)
    @ApiModelProperty(value = "日期")
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
