package com.univer.labs.books.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.univer.labs.books.R;
import com.univer.labs.books.controller.author.AddAuthorActivity;
import com.univer.labs.books.controller.author.AllAuthorsActivity;
import com.univer.labs.books.controller.author.AuthorWithoutBooksActivity;
import com.univer.labs.books.controller.author.BooksByAuthorActivity;
import com.univer.labs.books.controller.author.BooksByAuthorsPieChartActivity;

public class MainAuthorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_authors);
    }

    public void onClickAddAuthorActivity(View v){
        Intent intent = new Intent(this, AddAuthorActivity.class);
        startActivity(intent);
    }

    public void onClickShowAuthorsActivity(View v){
        Intent intent = new Intent(this, AllAuthorsActivity.class);
        startActivity(intent);
    }

    public void onClickShowAuthorsWithoutBooksActivity(View v){
        Intent intent = new Intent(this, AuthorWithoutBooksActivity.class);
        startActivity(intent);
    }

    public void onClickBooksByAuthorsActivity(View v){
        Intent intent = new Intent(this, BooksByAuthorActivity.class);
        startActivity(intent);
    }

    public void onClickBooksByAuthorsChartActivity(View v){
        Intent intent = new Intent(this, BooksByAuthorsPieChartActivity.class);
        startActivity(intent);
    }

}
