package com.study.rest.consumer.el;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;

import java.io.IOException;

/**
 * EL 表达式  使用t.properties文件
 *
 * @author sk-qianxiao
 */
@Configuration
@PropertySource(value = "t.properties", encoding = "UTF-8")
public class ELConfig {
    /**
     * 使用系统属性
     */
    @Value("#{systemProperties['os.name']}")
    private String osName;
    /**
     * 使用环境变量
     */
    @Value("#{systemEnvironment['os.arch']}")
    private String osArch;
    /**
     * 调用参数
     */
    @Value("#{T(java.lang.Math).random()*100}")
    private double randomNumber;


    /**
     * 使用属性文件中的值
     */
    @Value("${sang.username}")
    private String su;
    @Value("${sang.password}")
    private String sp;
    @Value("张二娃")
    private String sn;
    @Autowired
    private Environment environment;


    /**
     * 使用t.txt文件资源
     */
    @Value("t.txt")
    private Resource testFile;
    /**
     * url当资源
     */
    @Value("http://www.baidu.com")
    private Resource testUrl;

    public void output() {
        try {
            System.out.println(osName);
            System.out.println(osArch);
            System.out.println(randomNumber);
            System.out.println(su);
            System.out.println(sp);
            System.out.println(sn);
            System.out.println(environment.getProperty("sang.password"));

            System.out.println(IOUtils.toString(testFile.getInputStream(), "UTF-8"));
            System.out.println(IOUtils.toString(testUrl.getInputStream(), "UTF-8"));
            System.out.println("testUrl.getURL():" + testUrl.getURL());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
