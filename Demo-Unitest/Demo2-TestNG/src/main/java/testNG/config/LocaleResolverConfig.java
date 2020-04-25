package testNG.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

/**
 * 这里被声明的实例就是用来做语言解析的，MVC默认使用AcceptHeaderLocaleResolver
 * 也可以通过拦截器进行不同优先级的处理,然后设置到默认的解析器上
 * Created by xq on 2017/12/5.
 */
@Configuration
public class LocaleResolverConfig {
    /**
     * 设置CookieLocaleResolver为默认语言处理器
     */
//    @Bean
//    public LocaleResolver localeResolver() {
//        CookieLocaleResolver slr = new CookieLocaleResolver();
//        //设置默认区域,
//        slr.setDefaultLocale(Locale.US);
//        //设置cookie有效期.
//        slr.setCookieMaxAge(3600);
//        return slr;
//    }

    /**
     * 设置SessionLocaleResolver为默认语言处理器
     */
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        //设置默认区域,
        slr.setDefaultLocale(Locale.US);
        return slr;
    }

    /**
     * 固定语言的解析器
     *
     * @return
     */
//    @Bean
//    public LocaleResolver localeResolver() {
//        FixedLocaleResolver slr = new FixedLocaleResolver();
//        //设置默认区域,
//        slr.setDefaultLocale(Locale.US);
//        return slr;
//    }

}
