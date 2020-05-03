package com.study.rest.consumer.event.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2 {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.study.spring.bean.sang"))     //扫描API的包路径
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("肖迁", "http://vector4wang.tk/", "xxx@163.com");
        return new ApiInfoBuilder()
                .title("Performance Manager Data Monitor Api")      // 标题
                .description("Data Monitor Api Documentation")      // 描述
                .license("SKSPRUCE TECHNOLOGIES")
                .version("0.0.1")                                       // 版本号
                .termsOfServiceUrl("http://vector4wang.tk/") //网址
                .contact(contact)               //作者
                .build();
    }

}