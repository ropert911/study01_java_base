package com.study.base.patterns.factory;

/**
 * @author sk-qianxiao
 * @date 2020/1/7
 */
public class RoleUser implements RoleOp {
    @Override
    public void handle() {
        System.out.println("user handle...");
    }
}
