package com.study.spring.res;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.WritableResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.FileCopyUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author sk-qianxiao
 */
public class PattenResolverExample {
    public static void main(String[] args) {
        try {
            ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            //加载包及子包下所有xml文件 classpath*: 是资源地址表达式，用来确认用什么方式进行解析读取 支持 classpath,file,http:// ftp://等
            Resource[] resources = resolver.getResources("classpath*:com/smart/**/*.xml");

            for (Resource resource : resources) {
                System.out.println(resource.getDescription());
            }

        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}
