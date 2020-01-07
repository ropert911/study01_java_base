package com.xq.study.patterns;

import com.xq.study.patterns.factory.RoleAdmin;
import com.xq.study.patterns.factory.RoleOp;
import com.xq.study.patterns.factory.RoleUser;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sk-qianxiao
 * @date 2020/1/7
 */
public class FactoryOfRole {
    static Map<String, RoleOp> roleOpMap = new HashMap<>();
    static {
        roleOpMap.put("admin",new RoleAdmin());
        roleOpMap.put("user",new RoleUser());
    }
    public static RoleOp getOp(String role){
        return roleOpMap.get(role);
    }

    public static void main(String[] args) {
        getOp("admin").handle();
    }
}
