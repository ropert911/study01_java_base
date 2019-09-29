package com.study.spring.utils.conditional;

import com.study.spring.utils.conditional.linux.LinuxCondition;
import com.study.spring.utils.conditional.linux.LinuxListService;
import com.study.spring.utils.conditional.windows.WindowsCondition;
import com.study.spring.utils.conditional.windows.WindowsListService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * Created by sang on 16-12-14.
 */
@Configuration
public class MyConfig {
    @Bean
    @Conditional(WindowsCondition.class)
    public ListService windowListService() {
        return new WindowsListService();
    }
    @Bean
    @Conditional(LinuxCondition.class)
    public ListService linuxListService() {
        return new LinuxListService();
    }
}
