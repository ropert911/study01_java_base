package com.xq.study.patterns.factory;

/**
 * @author sk-qianxiao
 * @date 2020/1/7
 */
public class RoleAdmin implements RoleOp {
    @Override
    public void handle() {
        System.out.println("admin handle...");
    }
}
