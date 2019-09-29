package com.xq.study.anocation.fruitanocation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

/**
 * Created by xq on 2017/10/24.
 */
public class FruitInfoUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(FruitInfoUtil.class);

    public static void getFruitInfo(Class<?> clazz, Apple apple) {


        String strFruitName = " 水果名称：";
        String strFruitColor = " 水果颜色：";
        String strFruitProvicer = "供应商信息：";

        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            try {
                if (field.isAnnotationPresent(FruitName.class)) {
                    FruitName fruitName = (FruitName) field.getAnnotation(FruitName.class);
                    strFruitName = strFruitName + fruitName.value();

                    field.setAccessible(true);
                    field.set(apple, fruitName.value());
                } else if (field.isAnnotationPresent(FruitColor.class)) {
                    FruitColor fruitColor = (FruitColor) field.getAnnotation(FruitColor.class);
                    strFruitColor = strFruitColor + fruitColor.fruitColor().toString();

                    field.setAccessible(true);
                    field.set(apple, fruitColor.fruitColor().toString());
                } else if (field.isAnnotationPresent(FruitProvider.class)) {
                    FruitProvider fruitProvider = (FruitProvider) field.getAnnotation(FruitProvider.class);
                    strFruitProvicer = strFruitProvicer + " 供应商编号：" + fruitProvider.id() + " 供应商名称：" + fruitProvider.name() + " 供应商地址：" + fruitProvider.address();
                    field.setAccessible(true);
                    field.set(apple, strFruitProvicer);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

//        Method[] methods = clazz.getMethods();
//        for (Method method : methods) {
//            if (method.isAnnotationPresent(MethodInfo.class)) {
//                try {
//                    for (Annotation anno : method.getDeclaredAnnotations()) {
//                        LOGGER.error("方法{}中声明了的注解 {}", method, anno);
//                    }
//                    MethodInfo methodAnno = method.getAnnotation(MethodInfo.class);
//                    if (methodAnno.revision() == 1) {
//                        LOGGER.error("方法{}版本号为1 ", method);
//                    }
//
//                } catch (Throwable ex) {
//                    ex.printStackTrace();
//                }
//            }
//        }
    }
}