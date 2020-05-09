package com.univer.labs.books;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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

}
