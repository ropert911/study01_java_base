package com.study.unittest.apitest2.emoapitest2.podo;

import java.io.Serializable;

/**
 * Created by sang on 2017/9/9.
 */
public class IMSUser implements Serializable{
    private String name;
    private int price;
    private String author;
    private String publisher;

    public IMSUser() {
    }

    @Override
    public String toString() {
        return "IMSUser{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public IMSUser(String name, int price, String author, String publisher) {
        this.name = name;
        this.price = price;
        this.author = author;
        this.publisher = publisher;
    }
}
