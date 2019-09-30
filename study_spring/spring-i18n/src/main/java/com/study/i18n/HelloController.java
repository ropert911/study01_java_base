package com.study.i18n;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.LocaleResolver;
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
    @Autowired
    private MessageSource messageSource;

    @Resource
    private LocaleMessageSourceService localeMessageSourceService;

    @RequestMapping("/hello")
    public String hello(HttpServletRequest request) {
        //系统区域
        Locale locale1 = LocaleContextHolder.getLocale();
        String msg1 = messageSource.getMessage("welcome", null, locale1);
        System.out.println("（系统区域）-得到msg=" + msg1);

        /**这里的国际化用的是AcceptHeaderLocaleResolver*/
        /** 这个头部是由用户的web浏览器根据底层操作系统的区域设置进行设定。请注意，这个区域解析器无法改变用户的区域，因为它无法修改用户操作系统的区域设置*/
        Locale locale2 = RequestContextUtils.getLocale(request);
        String msg2 = messageSource.getMessage("welcome", null, locale2);
        System.out.println("（request的Accept-Language）-msg=" + msg2);

        String msg3 = localeMessageSourceService.getMessage("welcome");
        System.out.println("（封装过的）--得到msg=" + msg3);

        String msg4 = messageSource.getMessage("welcome", null, Locale.US);
        System.out.println("（指定英文） msg=" + msg4);

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
        System.out.println(lang);
        if ("zh".equals(lang)) {
            /**代码中即可通过以下方法进行语言设置*/
            request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, new Locale("zh", "CN"));
        } else if ("en".equals(lang)) {
            /**代码中即可通过以下方法进行语言设置*/
            request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, new Locale("en", "US"));
        }
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
    @RequestMapping("/changeSessionLanauage")
    public String changeSessionLanauage2(HttpServletRequest request, HttpServletResponse response, String lang) {
        System.out.println(lang);
        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
        if ("zh".equals(lang)) {
            localeResolver.setLocale(request, response, new Locale("zh", "CN"));
        } else if ("en".equals(lang)) {
            localeResolver.setLocale(request, response, new Locale("en", "US"));
        }
        return "redirect:/hello";
    }
}

