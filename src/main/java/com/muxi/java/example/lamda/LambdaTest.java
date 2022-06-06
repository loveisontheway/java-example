package com.muxi.java.example.lamda;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;

/**
 * Lambda Function
 *
 * @author jjl
 * @date 2022/6/6
 */
public class LambdaTest {

    private String fieldA;

    public static void main(String[] args) throws Exception {
        SerializedLambda serializedLambda = doSFunction(LambdaTest::getFieldA);
        System.out.println("方法名：" + serializedLambda.getImplMethodName());
        System.out.println("类名：" + serializedLambda.getImplClass());
        System.out.println("serializedLambda：" + JSONUtil.toJsonStr(serializedLambda));
    }

    private static <T, R> java.lang.invoke.SerializedLambda doSFunction(SFunction<T, R> func) throws Exception {
        // 直接调用writeReplace
        Method writeReplace = func.getClass().getDeclaredMethod("writeReplace");
        writeReplace.setAccessible(true);
        //反射调用
        Object sl = writeReplace.invoke(func);
        java.lang.invoke.SerializedLambda serializedLambda = (java.lang.invoke.SerializedLambda) sl;
        return serializedLambda;
    }

    public String getFieldA() {
        return fieldA;
    }

    public void setFieldA(String fieldA) {
        this.fieldA = fieldA;
    }
}


