package com.xq.study.anocation.scan;

import com.xq.study.anocation.scan.scanner.Scanner;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;


/**
 * @author xiaoqian
 * Created by sk-qianxiao on 2017/10/24.
 */
@Component
public  class BeanScannerConfigurer implements BeanFactoryPostProcessor, ApplicationContextAware {
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("==========BeanScannerConfigurer::setApplicationContext");
        this.applicationContext = applicationContext;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("==========BeanScannerConfigurer::postProcessBeanFactory");

        Scanner scanner = new Scanner((BeanDefinitionRegistry) beanFactory);
        scanner.setResourceLoader(this.applicationContext);
        scanner.scan("com.xq.study.anocation.scan.scanner");
    }
}
