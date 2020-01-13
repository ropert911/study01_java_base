package com.xq.study.json.jackson;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.xq.study.json.gson.model.Family;
import com.xq.study.json.jackson.model.Link;
import com.xq.study.json.jackson.model.Son;
import com.xq.study.json.jackson.model.User;
import org.codehaus.jackson.JsonProcessingException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * @author sk-qianxiao
 * @date 2019/12/25
 */
public class Main {
    public static void main(String[] args) throws Exception{
        test1();
        test2();
        test3();
    }

    public static void test1() {
        Son son = new Son();
        son.setSonName("SonName");
        son.setfName("FamilyName");
        System.out.println(son);

        try {
            ObjectMapper objectMapper = JsonUtils.getObjectMapper();
            String jsonDataString = objectMapper.writeValueAsString(son);
            System.out.println("Mapper序列化对象");
            System.out.println(jsonDataString);
            System.out.println("==================");

            Son son1 = JsonUtils.deserializeJson(objectMapper, jsonDataString, Son.class);
            Family family1 = JsonUtils.deserializeJson(objectMapper, jsonDataString, Family.class);
            System.out.println("Mapper反序列化出对象");
            System.out.println(son1);
            System.out.println(family1);
            System.out.println("==================");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void test2() {
        try {
            String tmp = "aaaaaaaaaaa";
            ObjectMapper objectMapper = JsonUtils.getObjectMapper();
            JsonNode node = objectMapper.readTree(tmp);
            ObjectNode newNode = objectMapper.createObjectNode();
            node.fields().forEachRemaining(entry -> {
                String fieldName = entry.getKey();      //获取键
                JsonNode field = entry.getValue();      //获取值
                String text = field.asText();           //值转string
                String newText = "aaa";
                newNode.set(fieldName, newNode.textNode(newText));  //设置新值
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void test3() {
        User user = new User();
        user.setId("01");
        user.setName("张三");
        user.setAge(33);
        user.setPay(6666.88);
        user.setValid(true);
        user.setOne('E');
        user.setBirthday(new Date(20L * 366 * 24 * 3600 * 1000)); //1990年

        Link link = new Link();
        link.setAddress("河南省济源市");
        link.setPhone("13899995555");
        link.setQq("123456");
        user.setLink(link);

        Map map = new HashMap();
        map.put("aa", "this is aa");
        map.put("bb", "this is bb");
        map.put("cc", "this is cc");
        user.setMap(map);

        List list = new ArrayList() {
        };
        list.add("普洱");
        list.add("大红袍");
        user.setList(list);

        Set set = new HashSet();
        set.add("篮球");
        set.add("足球");
        set.add("乒乓球");
        user.setSet(set);

        try {
            org.codehaus.jackson.map.ObjectMapper mapper = new org.codehaus.jackson.map.ObjectMapper(); //转换器

            //测试01：
            String json = null;
            json = mapper.writeValueAsString(user);
            System.out.println("======对象转Json结果=====");
            System.out.println(json);

            //测试02：
            Map m = null; //json转换成map
            m = mapper.readValue(json, Map.class);
            System.out.println("======Jaso转Map结果=====");
            System.out.println("pay：" + m.get("pay").getClass().getName() + " value=" + m.get("pay"));         //java.lang.Double
            System.out.println("valid：" + m.get("valid").getClass().getName());     //java.lang.Boolean
            System.out.println("birthday：" + m.get("birthday").getClass().getName()); //java.lang.Long
            System.out.println("link：" + m.get("link").getClass().getName());       //java.util.LinkedHashMap
            System.out.println("map：" + m.get("map").getClass().getName());         //java.util.LinkedHashMap
            System.out.println("list：" + m.get("list").getClass().getName());       //java.util.ArrayList
            System.out.println("set：" + m.get("set").getClass().getName()); //java.util.ArrayList

            //测试03：
            json = mapper.writeValueAsString(m); //map转json
            System.out.println("======Map转Jaso结果=====");
            System.out.println(json); //与之前格式完全相同，说明经过map转换后，信息没有丢失

            //测试04：json--对象
            User u = mapper.readValue(json, User.class); //json转java对象。经测，转成对象后，一切恢复正常
            System.out.println("======Jason转对象结果=====");
            System.out.println("pay：" + u.getPay());
            System.out.println("valid：" + u.isValid());
            System.out.println("birthday：" + u.getBirthday());
            System.out.println("link：" + u.getLink());
            System.out.println("map：" + u.getMap());
            System.out.println("list：" + u.getList());
            System.out.println("set：" + u.getSet());

            //测试05：其他转换
            byte[] data = mapper.writeValueAsBytes(u); //对象转成字符串数组
            System.out.println("======对象转字符串数组=====");
            System.out.println(new String(data));

            //写到不到的输出上
            mapper.writeValue(new File("D:\\abcdefg.txt"), user);
            readFileByBytes("D:\\abcdefg.txt");
            deleteFile("D:\\abcdefg.txt");
            mapper.writeValue(System.out, user);
            System.out.flush();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                System.out.println("删除单个文件" + fileName + "成功！");
                return true;
            } else {
                System.out.println("删除单个文件" + fileName + "失败！");
                return false;
            }
        } else {
            System.out.println("删除单个文件失败：" + fileName + "不存在！");
            return false;
        }
    }

    public static void readFileByBytes(String fileName) {
        File file = new File(fileName);
        InputStream in = null;
        try {
            System.out.println("以字节为单位读取文件内容，一次读一个字节：");
            // 一次读一个字节
            in = new FileInputStream(file);
            int tempbyte;
            while ((tempbyte = in.read()) != -1) {
                System.out.write(tempbyte);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        try {
            System.out.println("以字节为单位读取文件内容，一次读多个字节：");
            // 一次读多个字节
            byte[] tempbytes = new byte[100];
            int byteread = 0;
            in = new FileInputStream(fileName);
            showAvailableBytes(in);
            // 读入多个字节到字节数组中，byteread为一次读入的字节数
            while ((byteread = in.read(tempbytes)) != -1) {
                System.out.write(tempbytes, 0, byteread);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e1) {
                }
            }
        }
    }

    private static void showAvailableBytes(InputStream in) {
        try {
            System.out.println("当前字节输入流中的字节数为:" + in.available());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
