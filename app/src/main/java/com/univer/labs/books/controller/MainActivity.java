package com.univer.labs.books.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.univer.labs.books.R;
import com.univer.labs.books.service.AuthorService;
import com.univer.labs.books.service.BookService;

public class MainActivity extends AppCompatActivity {
    Button buttonAuthors;
    BookService bookService;
    AuthorService authorService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        authorService = new AuthorService(this);
        bookService = new BookService(this);
    }

    public void onClickOpenAllAuthorsActivity(View v){
        Intent intent = new Intent(this, MainAuthorsActivity.class);
        startActivity(intent);
    }

    public void onClickOpenAllBooksActivity(View v){
        Intent intent = new Intent(this, MainBooksActivity.class);
        startActivity(intent);
    }

}
