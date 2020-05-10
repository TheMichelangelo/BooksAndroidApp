package com.univer.labs.books.controller.book;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.R.style;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.univer.labs.books.R;
import com.univer.labs.books.model.Author;
import com.univer.labs.books.service.AuthorService;
import com.univer.labs.books.service.BookService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static android.R.layout.simple_spinner_item;

public class AddBookActivity extends AppCompatActivity {
    BookService bookService;
    AuthorService authorService;

    private Spinner authorSpinner;
    private TextView datePublishedEdit;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    EditText priceEdit, pagesCountEdit, bookNameEdit;

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
        datePublishedEdit.setText("11/22/1963");
        addAuthorItemsOnSpinner();

        datePublishedEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        AddBookActivity.this,
                        style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = month + "/" + day + "/" + year;
                datePublishedEdit.setText(date);
            }
        };

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

    public void onAddClick(View v) {
        String bookNameText = bookNameEdit.getText().toString();
        if (bookNameText.isEmpty()) {
            showAlert("Book name can't be empty", "Something go wrong");
            return;
        }
        String date = datePublishedEdit.getText().toString();
        String priceText = priceEdit.getText().toString();
        String pagesCount = pagesCountEdit.getText().toString();
        if(priceText.isEmpty() || pagesCount.isEmpty())
        {
            showAlert("Book price and pages count can't be empty", "Something go wrong");
            return;
        }
        Author author = (Author) authorSpinner.getSelectedItem();

        boolean result = bookService.insertBook(bookNameText, date, Integer.valueOf(pagesCount), Double.valueOf(priceText), author.getAuthorId());
        if (result) {
            showAlert("Book added", "Success");
            bookNameEdit.setText("");
            datePublishedEdit.setText("11/22/1963");
            priceEdit.setText("");
            pagesCountEdit.setText("");
        }
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
