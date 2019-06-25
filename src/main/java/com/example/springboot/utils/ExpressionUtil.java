package com.example.springboot.utils;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.Map;

/**
 * SPEL的工具类
 *
 * @author yewubin
 */
public class ExpressionUtil {
    /**
     * 以map对象为上下文变量解析SPEL表达式
     *
     * @param map     表达式执行的上下文变量map
     * @param express SPEL表达式
     * @return 表达式执行结果
     */
    public static Object parseExpression(Map<String, Object> map, String express) {
        ExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression(express);
        StandardEvaluationContext ctx = new StandardEvaluationContext();
        ctx.setVariables(map);
        Object value = expression.getValue(ctx);
        return value;
    }

    public static Object parseExpression(String valueKey, Object valueObj, String express) {
        ExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression(express);
        StandardEvaluationContext ctx = new StandardEvaluationContext();
        ctx.setVariable(valueKey, valueObj);
        return expression.getValue(ctx);
    }

    public static void modifyValue(String valueKey, Object valueObj, String express, Object newValue) {
        ExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression(express);
        StandardEvaluationContext ctx = new StandardEvaluationContext();
        ctx.setVariable(valueKey, valueObj);
        boolean writable = expression.isWritable(ctx);
        if (writable) {
            expression.setValue(ctx, valueObj, newValue);
        }
    }


}
