package com.univer.labs.books.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.univer.labs.books.R;
import com.univer.labs.books.controller.author.AddAuthorActivity;
import com.univer.labs.books.controller.author.AllAuthorsActivity;
import com.univer.labs.books.controller.book.AddBookActivity;
import com.univer.labs.books.controller.book.AllBooksActivity;

public class MainBooksActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_books);
    }

    public void onClickAddBookActivity(View v) {
        Intent intent = new Intent(this, AddBookActivity.class);
        startActivity(intent);
    }

    public void onClickShowBooksActivity(View v) {
        Intent intent = new Intent(this, AllBooksActivity.class);
        startActivity(intent);
    }
}
