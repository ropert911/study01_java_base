package com.xq.study.anocation.fruitanocation;

import org.springframework.stereotype.Component;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import java.util.Set;

/**
 * Created by sk-qianxiao on 2017/10/24.
 */
@SupportedAnnotationTypes({"com.xq.study.aop.fruitanocation.FruitName"})
//@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class Myprocessor extends AbstractProcessor {
    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        System.out.println("aaaaaaaaaaaaaaaaaaa");
    }

    //指明支持哪些注解
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return null;
    }

    //指定java版本
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        System.out.println("cccccccccccccccccccccccccccccccccccccccccccccccccccccc");
        return true;
    }
}