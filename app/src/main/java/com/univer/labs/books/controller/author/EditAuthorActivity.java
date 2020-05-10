package com.univer.labs.books.controller.author;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.univer.labs.books.R;
import com.univer.labs.books.model.Author;
import com.univer.labs.books.service.AuthorService;

public class EditAuthorActivity extends AppCompatActivity {
    AuthorService authorService;
    TextView nameTextView, surnameTextView, secondNameTextView;
    String name, surname, secondName;
    Long author_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_author);
        authorService = new AuthorService(this);
        nameTextView = findViewById(R.id.edit_author_name);
        surnameTextView = findViewById(R.id.edit_author_surname);
        secondNameTextView = findViewById(R.id.edit_author_secondname);
        getData();
        setData();
    }

    private void getData() {
        if (getIntent().hasExtra("name") && getIntent().hasExtra("surname")
                && getIntent().hasExtra("secondName") && getIntent().hasExtra("id")) {
            name = getIntent().getStringExtra("name");
            surname = getIntent().getStringExtra("surname");
            secondName = getIntent().getStringExtra("secondName");
            author_id = getIntent().getLongExtra("id",0);
        } else {
            Toast.makeText(this,"No data",Toast.LENGTH_SHORT).show();
        }
    }

    private void setData()
    {
        nameTextView.setText(name);
        surnameTextView.setText(surname);
        secondNameTextView.setText(secondName);
    }


    public void updateAuthor(View v) {
        String authorNameText =nameTextView.getText().toString();
        String authorSurnameText =surnameTextView.getText().toString();
        String authorSecondNameText =secondNameTextView.getText().toString();

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
        Author author = new Author(author_id,authorSurnameText,authorNameText,authorSecondNameText);

        boolean result=authorService.updateAuthor(author);
        if(result)
        {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setTitle("Successs");
            builder1.setMessage("Author was updated)");
            builder1.setCancelable(true);
            AlertDialog alert11 = builder1.create();
            alert11.show();
        }
    }
}
