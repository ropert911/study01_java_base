package com.xq.study.anocation.scan.scanner;

import com.xq.study.anocation.scan.anocation.CustomizeComponent;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.util.Set;

/**
 * Created by sk-qianxiao on 2017/10/24.
 */
public final class Scanner extends ClassPathBeanDefinitionScanner {
    public Scanner(BeanDefinitionRegistry registry) {
        super(registry);
    }

    @Override
    public void registerDefaultFilters() {
        this.addIncludeFilter(new AnnotationTypeFilter(CustomizeComponent.class));
    }

    @Override
    public Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set<BeanDefinitionHolder> beanDefinitions = super.doScan(basePackages);
        for (BeanDefinitionHolder holder : beanDefinitions) {
            GenericBeanDefinition definition = (GenericBeanDefinition) holder.getBeanDefinition();
            definition.setBeanClass(FactoryBeanTest.class);
            definition.getPropertyValues().add("innerClassName", definition.getBeanClassName());
        }
        return beanDefinitions;
    }

    @Override
    public boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        return super.isCandidateComponent(beanDefinition) && beanDefinition.getMetadata()
                .hasAnnotation(CustomizeComponent.class.getName());
    }
}