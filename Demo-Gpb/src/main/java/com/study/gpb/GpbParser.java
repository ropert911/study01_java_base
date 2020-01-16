package com.study.gpb;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author sk-ziconglu on 2017/5/3.
 */
@SpringBootApplication
public class GpbParser {

    public static void main(String[] args) {
        new SpringApplicationBuilder(GpbParser.class).web(false).run(args);
    }
}
