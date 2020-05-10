package com.univer.labs.books.controller.book;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.univer.labs.books.R;
import com.univer.labs.books.model.Author;
import com.univer.labs.books.service.AuthorService;
import com.univer.labs.books.service.BookService;

import java.util.ArrayList;
import java.util.List;

import static android.R.layout.simple_spinner_item;

public class AddBookActivity extends AppCompatActivity {
    BookService bookService;
    AuthorService authorService;

    private Spinner authorSpinner;
    private Button btnSubmit;
    EditText datePublishedEdit,priceEdit,pagesCountEdit,bookNameEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        bookService = new BookService(this);
        authorService = new AuthorService(this);
        bookNameEdit = findViewById(R.id.add_book_name_edit);
        pagesCountEdit = findViewById(R.id.add_book_pages_edit);
        priceEdit = findViewById(R.id.add_book_price_edit);
        datePublishedEdit = findViewById(R.id.add_book_date_edit);
        addAuthorItemsOnSpinner();

    }

    // add items into spinner dynamically
    public void addAuthorItemsOnSpinner() {
        authorSpinner = findViewById(R.id.add_book_author_edit);
        List<Author> list = authorService.getAllAuthors();
        ArrayAdapter<Author> dataAdapter = new ArrayAdapter<>(this,
                R.layout.dropdown_item_author_selected, list);
        dataAdapter.setDropDownViewResource(R.layout.dropdown_item_author);
        authorSpinner.setAdapter(dataAdapter);
    }

    public void onAddClick(View v)
    {
        String bookNameText =bookNameEdit.getText().toString();
        if(bookNameText.isEmpty())
        {
            showAlert("Book name can't be empty","Something go wrong");
            return;
        }
        Author author = (Author) authorSpinner.getSelectedItem();
        boolean result=bookService.insertBook(bookNameText);
        if(result)
        {
            showAlert("Book added","Success");
            ]bookNameEdit.setText("");
        }
    }

    private void showAlert(String message,String title)
    {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setTitle(title);
        builder1.setMessage(message);
        builder1.setCancelable(true);
        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

}
