package com.study.spring.i18n.service;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Locale;

/**
 * 国际化工具类
 *
 * @author Angel--守护天使
 * @version v.0.1
 * @date 2016年8月5日下午2:44:03
 */
@Component
public class LocaleMessageSourceService {

    @Resource
    private MessageSource messageSource;



    public String getMessage(String code, String defaultMessage) {
        return getMessage(code, null, defaultMessage);
    }

    public String getMessage(String code, String defaultMessage, Locale locale) {
        return getMessage(code, null, defaultMessage, locale);
    }

    public String getMessage(String code, Locale locale) {
        return getMessage(code, null, "", locale);
    }

    /**
     * @param code：对应messages配置的key.
     * @return
     */
    public String getMessage(String code) {
        return getMessage(code, new Object[]{});
    }
    /**
     * @param code：对应messages配置的key.
     * @param args                   :数组参数.
     * @return
     */
    public String getMessage(String code, Object[] args) {
        return getMessage(code, args, "");
    }

    public String getMessage(String code, Object[] args, Locale locale) {
        return getMessage(code, args, "", locale);
    }


    /**
     * @param code：对应messages配置的key.
     * @param args                   :数组参数.
     * @param defaultMessage         :没有设置key的时候的默认值.
     * @return
     */
    public String getMessage(String code, Object[] args, String defaultMessage) {
        //这里使用比较方便的方法，不依赖request.
        Locale locale = LocaleContextHolder.getLocale();
        return getMessage(code, args, defaultMessage, locale);
    }

    /**
     * 指定语言.
     *
     * @param code
     * @param args
     * @param defaultMessage
     * @param locale
     * @return
     */
    public String getMessage(String code, Object[] args, String defaultMessage, Locale locale) {
        return messageSource.getMessage(code, args, defaultMessage, locale);
    }
}