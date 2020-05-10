package com.univer.labs.books.controller.book;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.univer.labs.books.R;
import com.univer.labs.books.model.Book;
import com.univer.labs.books.service.BookService;

import java.util.List;

public class BooksUnderPriceActivity extends AppCompatActivity {
    private BookService bookService;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private List<Book> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_under_price);
        bookService = new BookService(this);
        list = bookService.getAllBooks();
        recyclerView = findViewById(R.id.allBooksView);
        // specify an adapter
        mAdapter = new BookListAdapter(this, list);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void onFindClick(View v)
    {

    }
}
