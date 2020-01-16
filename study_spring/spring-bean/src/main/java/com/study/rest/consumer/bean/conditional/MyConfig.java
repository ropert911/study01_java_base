package com.study.rest.consumer.bean.conditional;

import com.study.rest.consumer.bean.conditional.linux.LinuxListService;
import com.study.rest.consumer.bean.conditional.windows.WindowsCondition;
import com.study.rest.consumer.bean.conditional.windows.WindowsListService;
import com.study.rest.consumer.bean.conditional.linux.LinuxCondition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @author sk-qianxiao
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
