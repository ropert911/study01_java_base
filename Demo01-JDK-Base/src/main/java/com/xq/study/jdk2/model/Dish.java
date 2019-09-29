package com.xq.study.jdk2.model;

/**
 * Created by xq on 2019/2/24.
 */
public class Dish {
    private int type;
    private int calories;
    private String name;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isVegetarian(){
        return true;
    }
}
