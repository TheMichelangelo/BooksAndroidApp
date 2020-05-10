package com.univer.labs.books.controller.book;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.univer.labs.books.R;
import com.univer.labs.books.model.Book;
import com.univer.labs.books.service.BookService;

import java.util.List;

public class BooksUnderPriceActivity extends AppCompatActivity {
    private BookService bookService;
    private RecyclerView recyclerView;
    private BookListAdapter mAdapter;
    private List<Book> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_under_price);
        bookService = new BookService(this);
        list = bookService.getAllBooks();
        recyclerView = findViewById(R.id.under_price_books_list);
        EditText priceView = findViewById(R.id.under_price_edit);
        priceView.setText("0");
        // specify an adapter
        mAdapter = new BookListAdapter(this, list);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void onFindClick(View v)
    {
        EditText priceView = findViewById(R.id.under_price_edit);
        if(priceView.getText().toString().isEmpty())
        {
            showAlert("Book price can't be empty", "Something go wrong");
            return;
        }
        double price =Double.valueOf(priceView.getText().toString());
        list = bookService.getAllBooksUnderPrice(price);
        mAdapter.refreshData(list);
    }
    private void showAlert(String message, String title) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setTitle(title);
        builder1.setMessage(message);
        builder1.setCancelable(true);
        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
}
