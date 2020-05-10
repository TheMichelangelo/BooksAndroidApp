package com.univer.labs.books.controller.book;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import com.univer.labs.books.R;
import com.univer.labs.books.model.Book;
import com.univer.labs.books.service.BookService;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BooksDuplicateActivity extends AppCompatActivity {
    private BookService bookService;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private List<Book> list;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_duplicate);
        bookService = new BookService(this);
        list = bookService.getAllDuplications();
        Collections.sort(list, new Comparator<Book>() {
            @Override
            public int compare(Book d1, Book d2) {
                return d2.getName().compareTo(d1.getName());
            }
        });
        recyclerView = findViewById(R.id.duplicateBooksView);
        // specify an adapter
        mAdapter = new BookListAdapter(this, list);
        context=this;
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
