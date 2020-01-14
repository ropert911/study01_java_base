package com.study.spring.res;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.WritableResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.FileCopyUtils;

import java.io.*;
import java.nio.file.Files;
import java.util.List;

/**
 * @author sk-qianxiao
 */
public class FileSourceExample {
    public static void main(String[] args) {
        try {
            //使用系统路径的方式加载文件
            {
                String filePath = "C:/Users/sk-qianxiao/Desktop/abc.txt";
                WritableResource res1 = new PathResource(filePath);

                //写文件
                OutputStream stream1 = res1.getOutputStream();
                stream1.write("欢迎光临论坛".getBytes());
                stream1.close();
                //读
                InputStream ins1 = res1.getInputStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                int i;
                while ((i = ins1.read()) != -1) {
                    baos.write(i);
                }
                System.out.println(baos.toString());

                System.out.println("res1 name=>" + res1.getFilename());
            }

            Resource resource = new ClassPathResource("conf/file1");
            System.out.println("文件名:" + resource.getFilename());
            System.out.println("描述:" + resource.getDescription());
            System.out.println("正文长度:" + resource.contentLength());


            {
                System.out.println("以classpath方式访问----使用inputstream读取=======");
                InputStream inputStream = resource.getInputStream();
                int contentLen = inputStream.available();
                byte[] st = new byte[contentLen];
                inputStream.read(st);
                System.out.println(new String(st));
            }
            {
                System.out.println("以classpath方式访问----使用inputstream 按行读读取=======");
                InputStream inputStream = resource.getInputStream();
                BufferedReader in2 = new BufferedReader(new InputStreamReader(inputStream));
                String y = null;
                while ((y = in2.readLine()) != null) {
                    System.out.println(y);
                }
            }
            {
                System.out.println("以类路径的方式访问---使用FileCopyUtils读取=======");
                EncodedResource encRes = new EncodedResource(resource, "UTF-8");
                String content = FileCopyUtils.copyToString(encRes.getReader());
                System.out.println(content);
            }
            {
                System.out.println("使用Files.readAllLines方式读取=======");
                File file = resource.getFile();
                List<String> list = Files.readAllLines(file.toPath());
                for (String string : list) {
                    System.out.println(string);
                }
            }
            {
                System.out.println("使用FileReader进行读取=======");
                File file = resource.getFile();
                BufferedReader br = new BufferedReader(new FileReader(file));
                String str = "";
                while ((str = br.readLine()) != null) {
                    System.out.println(str);
                }
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}
