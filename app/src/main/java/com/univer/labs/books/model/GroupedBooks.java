package com.univer.labs.books.model;

public class GroupedBooks {
    double maxPrice;
    double avgPrice;
    int count;
    long authorId;

    public GroupedBooks() {
        count=0;
        maxPrice=0;
        avgPrice=0;
    }

    public GroupedBooks(double maxPrice, double avgPrice, int count, long authorId) {
        this.maxPrice = maxPrice;
        this.avgPrice = avgPrice;
        this.count = count;
        this.authorId = authorId;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public double getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(double avgPrice) {
        this.avgPrice = avgPrice;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }
}
