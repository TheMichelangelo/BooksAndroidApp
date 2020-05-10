package com.univer.labs.books.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.univer.labs.books.R;
import com.univer.labs.books.controller.author.AddAuthorActivity;
import com.univer.labs.books.controller.author.AllAuthorsActivity;

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

}
