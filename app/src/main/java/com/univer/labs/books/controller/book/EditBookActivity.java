package com.univer.labs.books.controller.book;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.univer.labs.books.R;
import com.univer.labs.books.model.Author;
import com.univer.labs.books.model.Book;
import com.univer.labs.books.service.AuthorService;
import com.univer.labs.books.service.BookService;

import java.util.Calendar;
import java.util.List;

public class EditBookActivity extends AppCompatActivity {
    BookService bookService;
    AuthorService authorService;

    private Spinner authorSpinner;
    private TextView datePublishedEdit;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private EditText priceEdit, pagesCountEdit, bookNameEdit;
    private String bookName,datePublished;
    private int pagesCount;
    private double price;
    private Long author_id;
    private Long bookId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book);
        bookService = new BookService(this);
        authorService = new AuthorService(this);
        bookNameEdit = findViewById(R.id.update_book_name_edit);
        pagesCountEdit = findViewById(R.id.update_book_pages_edit);
        priceEdit = findViewById(R.id.update_book_price_edit);
        datePublishedEdit = findViewById(R.id.update_book_date_edit);
        addAuthorItemsOnSpinner();
        getData();
        setData();
        datePublishedEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        EditBookActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
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

    private void getData()
    {
        if (getIntent().hasExtra("name") && getIntent().hasExtra("price")
                && getIntent().hasExtra("pages") && getIntent().hasExtra("date_publiched")
                && getIntent().hasExtra("bookId") && getIntent().hasExtra("authorId")) {
            bookName = getIntent().getStringExtra("name");
            datePublished = getIntent().getStringExtra("date_publiched");
            pagesCount = getIntent().getIntExtra("pages",0);
            price =getIntent().getDoubleExtra("price",0);
            bookId=getIntent().getLongExtra("bookId",0);
            author_id=getIntent().getLongExtra("authorId",0);
        } else {
            Toast.makeText(this,"No data",Toast.LENGTH_SHORT).show();
        }
    }

    private void setData()
    {
        bookNameEdit.setText(bookName);
        datePublishedEdit.setText(datePublished);
        pagesCountEdit.setText(String.valueOf(pagesCount));
        priceEdit.setText(String.valueOf(price));
        List<Author> list = authorService.getAllAuthors();
        Author a =authorService.getAuthorById(author_id);
        int pos = list.indexOf(a);
        authorSpinner.setSelection(pos);
    }

    public void onAddClick(View v) {
        String bookNameText = bookNameEdit.getText().toString();
        if (bookNameText.isEmpty()) {
            showAlert("Book name can't be empty", "Something go wrong");
            return;
        }
        String dateText = datePublishedEdit.getText().toString();
        String priceText = priceEdit.getText().toString();
        String pagesCountText = pagesCountEdit.getText().toString();
        if(priceText.isEmpty() || pagesCountText.isEmpty())
        {
            showAlert("Book price and pages count can't be empty", "Something go wrong");
            return;
        }
        Author author = (Author) authorSpinner.getSelectedItem();
        Book book = new Book(bookId,bookNameText,dateText,pagesCount,price,author.getAuthorId());

        boolean result = bookService.updateBook(book);
        if (result) {
            showAlert("Book updated", "Success");
            bookNameEdit.setText("");
            datePublishedEdit.setText("11/22/1963");
            priceEdit.setText("");
            pagesCountEdit.setText("");
        }
    }

    // add items into spinner dynamically
    public void addAuthorItemsOnSpinner() {
        authorSpinner = findViewById(R.id.update_book_author_edit);
        List<Author> list = authorService.getAllAuthors();
        ArrayAdapter<Author> dataAdapter = new ArrayAdapter<>(this,
                R.layout.dropdown_item_author_selected, list);
        dataAdapter.setDropDownViewResource(R.layout.dropdown_item_author);
        authorSpinner.setAdapter(dataAdapter);
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
