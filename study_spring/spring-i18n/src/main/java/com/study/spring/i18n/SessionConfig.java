package com.study.spring.i18n;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

/**
 * Created by xq on 2017/12/5.
 */
@Configuration
public class SessionConfig {
//    @Bean
//    public LocaleResolver localeResolver() {    //设置session默认语言
//        SessionLocaleResolver slr = new SessionLocaleResolver();
//        //设置默认区域,
////        slr.setDefaultLocale(Locale.CHINA);
//        slr.setDefaultLocale(Locale.US);
//        return slr;
//    }

    @Bean
    public LocaleResolver localeResolver() {     //设置cookie
        CookieLocaleResolver slr =new CookieLocaleResolver();
        //设置默认区域,
        slr.setDefaultLocale(Locale.CHINA);
        slr.setCookieMaxAge(3600);//设置cookie有效期.
        return slr;
    }
}
