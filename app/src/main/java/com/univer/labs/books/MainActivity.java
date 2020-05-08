package com.univer.labs.books;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.univer.labs.books.service.AuthorService;
import com.univer.labs.books.service.BookService;

public class MainActivity extends AppCompatActivity {
    Button btnSuppliers, btnComputers, btnSupplierList;
    BookService bookService;
    AuthorService authorService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        authorService = new AuthorService(this);
    }

}
