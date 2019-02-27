package com.xq.study.jdk.java_lang_reflect;


import java.lang.reflect.*;

public class Test {
    public void setName(String name) {
        System.out.println(name);
    }

    public static void invokeSetter(Object obj, String filed, Object value) {
        String methodName = "set" + filed.substring(0, 1).toUpperCase() + filed.substring(1);

        try {
            Class<?> clazz = obj.getClass();
            Method method = clazz.getMethod(methodName, value.getClass()); //通知类的方法名和参数名获取类里的函数
            method.invoke(obj, value);      //参数函数进行调用

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static void useVarargsConstructor() {
        try {
            Constructor<VarargsConstructor> constructor = null;
            constructor = VarargsConstructor.class.getDeclaredConstructor(String[].class);
            constructor.newInstance((Object) new String[]{"A", "B", "C"});
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static void useNestedClassConstructor() {
        try {
            Constructor<StaticNestedClass> sncc = StaticNestedClass.class.getDeclaredConstructor(String.class);
            StaticNestedClass staticNestedClass = sncc.newInstance("Alex");
            Constructor<StaticNestedClass.NestedClass> ncc = StaticNestedClass.NestedClass.class.getDeclaredConstructor(StaticNestedClass.class, int.class);
            StaticNestedClass.NestedClass ic = ncc.newInstance(staticNestedClass, 3);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static void useFiled() {
        Field fieldCount = null;
        try {
            fieldCount = FieldContainer.class.getDeclaredField("count");
            fieldCount.set(null, 3);    //静态filed

            Field fieldName = FieldContainer.class.getDeclaredField("name");
            FieldContainer fieldContainer = new FieldContainer();
            fieldName.set(fieldContainer, "xq");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void useMethod() {
        try {
            MethodContainer mc = new MethodContainer();
            Method puMethod = null;

            puMethod = MethodContainer.class.getDeclaredMethod("publicMethod");
            puMethod.invoke(mc);


            Method prMethod = MethodContainer.class.getDeclaredMethod("privatMethod");
            prMethod.setAccessible(true);
            prMethod.invoke(mc);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        String name = "xiaoqian";

        //参考反射调用类的public方法
        invokeSetter(test, "name", name);

        //类构造函数
        useVarargsConstructor();

        //嵌套类函数
        useNestedClassConstructor();

        //使用类中的filed
        useFiled();

        //类中的函数使用
        useMethod();
    }
}
