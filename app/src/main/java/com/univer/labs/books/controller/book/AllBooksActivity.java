package com.univer.labs.books.controller.book;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import com.univer.labs.books.R;
import com.univer.labs.books.model.Author;
import com.univer.labs.books.service.AuthorService;
import com.univer.labs.books.service.BookService;

import java.util.List;

public class AllBooksActivity extends AppCompatActivity {
    private BookService bookService;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private List<Author> list;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_books);
    }
}
