package com.univer.labs.books;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.univer.labs.books.model.Author;
import com.univer.labs.books.service.AuthorService;

import java.io.Serializable;
import java.util.List;

public class AllAuthorsActivity extends AppCompatActivity {
    private AuthorService authorService;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_authors);
        authorService = new AuthorService(this);
        List<Author> list = authorService.getAllAuthors();
        recyclerView = findViewById(R.id.allAuthorsView);
        // specify an adapter
        mAdapter = new AuthorsListAdapter(this,list);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
