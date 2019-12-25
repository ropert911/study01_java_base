package com.xq.study.stream.model;

/**
 * @author sk-qianxiao
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

    public boolean isVegetarian() {
        return true;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "type=" + type +
                ", calories=" + calories +
                ", name='" + name + '\'' +
                '}';
    }
}
