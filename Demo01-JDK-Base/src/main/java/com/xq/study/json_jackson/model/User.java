package com.xq.study.json_jackson.model;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author sk-qianxiao
 * Description: 用户，测试模型类
 */
public class User {
    /**
     * 标识
     */
    private String id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 年龄
     */
    private int age;
    /**
     * 工资
     */
    private double pay;
    /**
     * 是否有效
     */
    private boolean valid;
    /**
     * 一个字符
     */
    private char one;
    /**
     * 生日
     */
    private Date birthday;

    /**
     * 联系方式，自定义
     */
    private Link link;

    /**
     * 测试用
     */
    private Map map;
    /**
     * 测试用
     */
    private List list;
    /**
     * 测试用
     */
    private Set set;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getPay() {
        return pay;
    }

    public void setPay(double pay) {
        this.pay = pay;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public char getOne() {
        return one;
    }

    public void setOne(char one) {
        this.one = one;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public Set getSet() {
        return set;
    }

    public void setSet(Set set) {
        this.set = set;
    }
}