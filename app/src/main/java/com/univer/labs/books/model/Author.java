package com.univer.labs.books.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Author implements Serializable {
    long authorId;
    String surname;
    String name;
    String secondName;

    public Author() {
        this.authorId = 0;
        this.surname = "";
        this.name = "";
        this.secondName = "";
    }

    public Author(long authorId, String surname, String name, String secondName) {
        this.authorId = authorId;
        this.surname = surname;
        this.name = name;
        this.secondName = secondName;
    }

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    @NonNull
    @Override
    public String toString() {
        return this.surname+" "+this.name+" "+this.secondName;
    }
}
