package com.study.base.patterns;

import java.io.*;

/**
 * @author sk-qianxiao
 * @date 2020/1/7
 */
public class DCLSingleton implements Serializable {

    private static DCLSingleton mInstance;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //处理反射问题
    private DCLSingleton() {
        if (mInstance!=null) {
            throw new RuntimeException("想反射我，没门！");
        }
    }

    //DCL 双重检查锁定(DCL, Double Check Lock)
    public static DCLSingleton getInstance() {
        if (mInstance == null) {
            synchronized (DCLSingleton.class) {
                if (mInstance == null) {
                    mInstance = new DCLSingleton();
                }
            }
        }
        return mInstance;
    }

    /**
     * 防序列化
     * @return
     */
    protected Object readResolve() {
        return getInstance();
    }

    @Override
    public String toString() {
        return "DCLSingleton{" +
                "name='" + name + '\'' +
                '}';
    }

    public static void main(String[] args) {
        // 初始化
        DCLSingleton instance = DCLSingleton.getInstance();
        instance.setName("XX");
        System.out.println(instance);

        // 把对象写到文件中
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("chenmo"));){
            oos.writeObject(instance);
        } catch (IOException e) {
            e.printStackTrace();
        }


        // 从文件中读出对象，可以看到反序列化失败
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("chenmo")));){
            instance.setName("YY");
            DCLSingleton instance2 = (DCLSingleton) ois.readObject();
            System.out.println(instance2);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}

