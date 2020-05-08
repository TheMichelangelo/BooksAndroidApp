package com.univer.labs.books.model;

import java.io.Serializable;

public class Book implements Serializable {
    long bookId;
    String name;
    String datePublished;
    int pagesCount;
    double price;
    long authorId;

    public Book() {
        this.bookId = 0;
        this.name = "";
        this.datePublished = "";
        this.pagesCount = 0;
        this.price = 0;
        this.authorId = 0;
    }

    public Book(long bookId, String name, String datePublished, int pagesCount, double price, long authorId) {
        this.bookId = bookId;
        this.name = name;
        this.datePublished = datePublished;
        this.pagesCount = pagesCount;
        this.price = price;
        this.authorId = authorId;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(String datePublished) {
        this.datePublished = datePublished;
    }

    public int getPagesCount() {
        return pagesCount;
    }

    public void setPagesCount(int pagesCount) {
        this.pagesCount = pagesCount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }
}
