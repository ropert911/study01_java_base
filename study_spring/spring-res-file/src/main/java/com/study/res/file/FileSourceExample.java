package com.study.res.file;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.WritableResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.FileCopyUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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
            // 以类路径的方式访问
            {
                Resource res2 = new ClassPathResource("conf/file1");
                InputStream ins2 = res2.getInputStream();
                System.out.println("res2 name=>" + res2.getFilename());
            }
            // 以类路径的方式访问
            {
                Resource res = new ClassPathResource("conf/file1");
                EncodedResource encRes = new EncodedResource(res, "UTF-8");
                String content = FileCopyUtils.copyToString(encRes.getReader());
                System.out.println(content);
            }

        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}
