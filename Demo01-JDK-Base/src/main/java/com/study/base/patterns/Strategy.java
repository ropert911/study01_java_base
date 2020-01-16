package com.study.base.patterns;

import com.study.base.patterns.factory.RoleAdmin;
import com.study.base.patterns.factory.RoleOp;

/**
 * 策略模式通过注入不同的执行策略
 *
 * @author sk-qianxiao
 * @date 2020/1/7
 */
public class Strategy {
    private RoleOp op;

    public Strategy(RoleOp op) {
        this.op = op;
    }

    void excute() {
        op.handle();
    }

    public static void main(String[] args) {
        Strategy strategy = new Strategy(new RoleAdmin());
        strategy.excute();
    }
}
