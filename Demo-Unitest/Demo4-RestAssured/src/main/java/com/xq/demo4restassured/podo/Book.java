package com.xq.demo4restassured.podo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by sang on 2017/9/9.
 */
public class Book implements Serializable{
    private String name;
    private int price;
    private List<Author> authors;
    private Publisher publisher;
    private List<String> tags;

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
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



    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Book(String name, int price, List<Author> authors, Publisher publisher, List<String> tags) {
        this.name = name;
        this.price = price;
        this.authors = authors;
        this.publisher = publisher;
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", authors=" + authors +
                ", publisher=" + publisher +
                ", tags=" + tags +
                '}';
    }
}
