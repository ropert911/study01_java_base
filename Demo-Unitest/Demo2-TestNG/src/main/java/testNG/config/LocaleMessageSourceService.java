package testNG.config;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Locale;

/**
 * 国际化工具类
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


    public String getMessage(String code) {
        return getMessage(code, new Object[]{});
    }

    public String getMessage(String code, Object[] args) {
        return getMessage(code, args, "");
    }

    public String getMessage(String code, Object[] args, Locale locale) {
        return getMessage(code, args, "", locale);
    }


    public String getMessage(String code, Object[] args, String defaultMessage) {
        Locale locale = LocaleContextHolder.getLocale();
        return getMessage(code, args, defaultMessage, locale);
    }


    public String getMessage(String code, Object[] args, String defaultMessage, Locale locale) {
        return messageSource.getMessage(code, args, defaultMessage, locale);
    }
}