package com.univer.labs.books.controller.author;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;

import com.univer.labs.books.R;
import com.univer.labs.books.model.Author;
import com.univer.labs.books.model.GroupedBooks;
import com.univer.labs.books.service.AuthorService;
import com.univer.labs.books.service.BookService;

import java.util.List;

public class BooksByAuthorActivity extends AppCompatActivity {
    private AuthorService authorService;
    private BookService bookService;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_by_author);
        authorService = new AuthorService(this);
        bookService = new BookService(this);
        List<GroupedBooks> list = bookService.getAllBooksByAuthors();
        recyclerView = findViewById(R.id.booksByAuthorView);
        // specify an adapter
        mAdapter = new AuthorsListQuerryAdapter2(this,list);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
