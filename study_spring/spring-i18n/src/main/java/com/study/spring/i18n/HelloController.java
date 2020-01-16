package com.study.spring.i18n;

import com.study.spring.i18n.service.LocaleMessageSourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * @author sk-qianxiao
 */
@Controller
public class HelloController {
    private final static Logger logger = LoggerFactory.getLogger(HelloController.class);
    @Autowired
    private MessageSource messageSource;

    @Resource
    private LocaleMessageSourceService localeMessageSourceService;

    @RequestMapping("/hello")
    public String hello(HttpServletRequest request) {
        /**AcceptHeaderLocaleResolver 是MVC默认的解析器, 是通过浏览器头部的语言信息来进行多语言选择，默认是不需要进行什么操作的*/
        /** 这个头部是由用户的web浏览器根据底层操作系统的区域设置进行设定*/
        /** 修改request accept-language可以在浏览器的设置里进行修改*/
        logger.info("request.header中的Accept-Language={}", RequestContextUtils.getLocale(request));

        logger.info("cookie 中的语言 {}", RequestContextUtils.getLocaleResolver(request).resolveLocale(request));

//        //系统区域
//        logger.info("本地语言 = {}", LocaleContextHolder.getLocale());

        /**sesstion语言如果要生效，需要声明 Bean；并在必要的地方进行语言的设定*/
        logger.info("session语言 = {}", request.getSession().getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME));


        logger.info("得到信息{}", localeMessageSourceService.getMessage("welcome"));

        return "/hello";
    }

    /**
     * 设置会话语言
     *
     * @param request
     * @param lang
     * @return
     */
    @RequestMapping("/changeSessionLanauage")
    public String changeSessionLanauage(HttpServletRequest request, String lang) {
        Locale locale = Locale.CHINA;
        if ("zh".equals(lang)) {
            locale = Locale.CHINA;
        } else if ("en".equals(lang)) {
            locale = Locale.ENGLISH;
        }

        logger.info("==========================修改session语言 {}", locale);
        request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, locale);
        return "redirect:/hello";
    }

    /**
     * 改cookie的设定
     *
     * @param request
     * @param response
     * @param lang
     * @return
     */
    @RequestMapping("/changecookeLanguage")
    public String changeSessionLanauage2(HttpServletRequest request, HttpServletResponse response, String lang) {
        Locale locale = Locale.CHINA;
        if ("zh".equals(lang)) {
            locale = Locale.CHINA;
        } else if ("en".equals(lang)) {
            locale = Locale.ENGLISH;
        }

        logger.info("=============设置cookies语言 {}", locale);
        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
        if (localeResolver instanceof CookieLocaleResolver) {
            localeResolver.setLocale(request, response, locale);
        }

        return "redirect:/hello";
    }
}

