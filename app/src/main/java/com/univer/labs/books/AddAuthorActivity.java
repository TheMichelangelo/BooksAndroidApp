package com.univer.labs.books;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.univer.labs.books.service.AuthorService;

public class AddAuthorActivity extends AppCompatActivity {

    AuthorService authorService;
    EditText authorName,authorSurname,authorSecondName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_author);
        authorService = new AuthorService(this);
        authorName = findViewById(R.id.authorName);
        authorSurname = findViewById(R.id.authorSurname);
        authorSecondName = findViewById(R.id.authorSecondName);
    }

    public void onAddClick(View v)
    {
        String authorNameText =authorName.getText().toString();
        String authorSurnameText =authorSurname.getText().toString();
        String authorSecondNameText =authorSecondName.getText().toString();

        if(authorNameText.isEmpty() || authorSurnameText.isEmpty())
        {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setTitle("Something go wrong");
            builder1.setMessage("Author name and Surname can't be empty!!!");
            builder1.setCancelable(true);
            AlertDialog alert11 = builder1.create();
            alert11.show();
            return;
        }

        boolean result=authorService.insertAuthor(authorNameText,authorSurnameText,authorSecondNameText);
        if(result)
        {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setTitle("Successs");
            builder1.setMessage("Author was added)");
            builder1.setCancelable(true);
            AlertDialog alert11 = builder1.create();
            alert11.show();
            authorName.setText("");
            authorSurname.setText("");
            authorSecondName.setText("");
        }
    }
}
